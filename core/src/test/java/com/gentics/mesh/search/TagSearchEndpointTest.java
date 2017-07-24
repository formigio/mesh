package com.gentics.mesh.search;

import static com.gentics.mesh.test.TestDataProvider.PROJECT_NAME;
import static org.junit.Assert.assertEquals;

import org.codehaus.jettison.json.JSONException;
import org.junit.Test;

import com.gentics.ferma.Tx;
import com.gentics.mesh.core.rest.tag.TagListResponse;
import com.gentics.mesh.test.context.AbstractMeshTest;
import com.gentics.mesh.test.context.MeshTestSetting;
import com.gentics.mesh.test.definition.BasicSearchCrudTestcases;
import static com.gentics.mesh.test.TestSize.FULL;
import static com.gentics.mesh.test.ClientHelper.call;
import static com.gentics.mesh.test.context.MeshTestHelper.getSimpleTermQuery;

@MeshTestSetting(useElasticsearch = true, startServer = true, testSize = FULL)
public class TagSearchEndpointTest extends AbstractMeshTest implements BasicSearchCrudTestcases {

	@Test
	@Override
	public void testDocumentCreation() throws InterruptedException, JSONException {
		String tagName = "newtag";
		try (Tx tx = tx()) {
			createTag(PROJECT_NAME, tagFamily("colors").getUuid(), tagName);
		}

		TagListResponse list = call(() -> client().searchTags(getSimpleTermQuery("name.raw", tagName)));
		assertEquals(1, list.getData().size());
	}

	@Test
	@Override
	public void testDocumentUpdate() throws InterruptedException, JSONException {
		String uuid = db().tx(() -> tag("red").getUuid());
		String parentTagFamilyUuid = db().tx(() -> tagFamily("colors").getUuid());

		String newName = "redish";
		updateTag(PROJECT_NAME, parentTagFamilyUuid, uuid, newName);
		updateTag(PROJECT_NAME, parentTagFamilyUuid, uuid, newName + "2");

		try (Tx tx = tx()) {
			assertEquals("The tag name was not updated as expected.", newName + "2", tag("red").getName());
		}

		TagListResponse list = call(() -> client().searchTags(getSimpleTermQuery("name.raw", newName + "2")));
		assertEquals(1, list.getData().size());
	}

	@Test
	@Override
	public void testDocumentDeletion() throws Exception {
		try (Tx tx = tx()) {
			recreateIndices();
		}

		String name = db().tx(() -> tag("red").getName());
		String uuid = db().tx(() -> tag("red").getUuid());
		String parentTagFamilyUuid = db().tx(() -> tagFamily("colors").getUuid());

		// 1. Verify that the tag is indexed
		TagListResponse list = call(() -> client().searchTags(getSimpleTermQuery("name.raw", name)));
		assertEquals("The tag with name {" + name + "} and uuid {" + uuid + "} could not be found in the search index.", 1, list.getData().size());

		// 2. Delete the tag
		call(() -> client().deleteTag(PROJECT_NAME, parentTagFamilyUuid, uuid));

		// 3. Search again and verify that the document was removed from the index
		list = call(() -> client().searchTags(getSimpleTermQuery("fields.name", name)));
		assertEquals(0, list.getData().size());
	}

}
