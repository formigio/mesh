package com.gentics.mesh.core.field.node;

import static com.gentics.mesh.test.TestDataProvider.PROJECT_NAME;
import static com.gentics.mesh.test.TestSize.FULL;
import static com.gentics.mesh.test.context.MeshTestHelper.call;
import static com.gentics.mesh.util.MeshAssert.assertSuccess;
import static com.gentics.mesh.util.MeshAssert.latchFor;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.gentics.mesh.core.data.node.Node;
import com.gentics.mesh.core.rest.node.NodeCreateRequest;
import com.gentics.mesh.core.rest.node.NodeResponse;
import com.gentics.mesh.core.rest.node.NodeUpdateRequest;
import com.gentics.mesh.core.rest.node.VersionReference;
import com.gentics.mesh.core.rest.node.field.impl.HtmlFieldImpl;
import com.gentics.mesh.core.rest.schema.HtmlFieldSchema;
import com.gentics.mesh.core.rest.schema.SchemaModel;
import com.gentics.mesh.core.rest.schema.SchemaReference;
import com.gentics.mesh.core.rest.schema.impl.HtmlFieldSchemaImpl;
import com.gentics.mesh.graphdb.NoTx;
import com.gentics.mesh.parameter.impl.NodeParametersImpl;
import com.gentics.mesh.rest.client.MeshResponse;
import com.gentics.mesh.test.context.AbstractMeshTest;
import com.gentics.mesh.test.context.MeshTestSetting;

@MeshTestSetting(useElasticsearch = false, testSize = FULL, startServer = true)
public class BasicNodeFieldEndpointTest extends AbstractMeshTest {

	@Test
	public void testUpdateNodeAndOmitRequiredField() throws IOException {
		try (NoTx noTx = db().noTx()) {
			// 1. create required field
			SchemaModel schema = schemaContainer("folder").getLatestVersion().getSchema();
			HtmlFieldSchema htmlFieldSchema = new HtmlFieldSchemaImpl();
			htmlFieldSchema.setName("htmlField");
			htmlFieldSchema.setLabel("Some label");
			htmlFieldSchema.setRequired(true);
			schema.addField(htmlFieldSchema);
			schemaContainer("folder").getLatestVersion().setSchema(schema);

			// 2. Create new node with required field value
			Node parentNode = folder("2015");
			NodeCreateRequest nodeCreateRequest = new NodeCreateRequest();
			nodeCreateRequest.setParentNodeUuid(parentNode.getUuid());
			nodeCreateRequest.setSchema(new SchemaReference().setName("folder"));
			nodeCreateRequest.setLanguage("en");
			nodeCreateRequest.getFields().put("htmlField", new HtmlFieldImpl().setHTML("Some<b>html"));

			NodeResponse response = call(() -> client().createNode(PROJECT_NAME, nodeCreateRequest, new NodeParametersImpl().setLanguages("en")));
			assertNotNull("The response could not be found in the result of the future.", response);
			assertNotNull("The field was not included in the response.", response.getFields().getHtmlField("htmlField"));

			// 3. Update node
			NodeUpdateRequest nodeUpdateRequest = new NodeUpdateRequest();
			nodeUpdateRequest.setLanguage("en");
			nodeUpdateRequest.setVersion(new VersionReference().setNumber("0.1"));

			MeshResponse<NodeResponse> updateFuture = client()
					.updateNode(PROJECT_NAME, response.getUuid(), nodeUpdateRequest, new NodeParametersImpl().setLanguages("en")).invoke();
			latchFor(updateFuture);
			assertSuccess(updateFuture);
			assertNotNull("The response could not be found in the result of the future.", updateFuture.result());
		}
	}
}
