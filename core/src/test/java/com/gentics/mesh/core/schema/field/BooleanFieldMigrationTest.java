package com.gentics.mesh.core.schema.field;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;

public class BooleanFieldMigrationTest extends AbstractFieldMigrationTest {
	private static final DataProvider FILLTRUE = (container, name) -> container.createBoolean(name).setBoolean(true);
	private static final DataProvider FILLFALSE = (container, name) -> container.createBoolean(name).setBoolean(false);

	private static final FieldFetcher FETCH = (container, name) -> container.getBoolean(name);

	@Override
	@Test
	public void testRemove() throws IOException {
		removeField(CREATEBOOLEAN, FILLTRUE, FETCH);
	}

	@Override
	@Test
	public void testRename() throws IOException {
		renameField(CREATEBOOLEAN, FILLTRUE, FETCH, (container, name) -> {
			assertThat(container.getBoolean(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBoolean(name).getBoolean()).as(NEWFIELDVALUE).isEqualTo(true);
		});

		renameField(CREATEBOOLEAN, FILLFALSE, FETCH, (container, name) -> {
			assertThat(container.getBoolean(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBoolean(name).getBoolean()).as(NEWFIELDVALUE).isEqualTo(false);
		});
	}

	@Override
	@Test
	public void testChangeToBinary() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEBINARY, (container, name) -> {
			assertThat(container.getBinary(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEBINARY, (container, name) -> {
			assertThat(container.getBinary(name)).as(NEWFIELD).isNull();
		});

	}

	@Override
	@Test
	public void testChangeToBoolean() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEBOOLEAN, (container, name) -> {
			assertThat(container.getBoolean(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBoolean(name).getBoolean()).as(NEWFIELDVALUE).isEqualTo(true);
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEBOOLEAN, (container, name) -> {
			assertThat(container.getBoolean(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBoolean(name).getBoolean()).as(NEWFIELDVALUE).isEqualTo(false);
		});
	}

	@Override
	@Test
	public void testChangeToBooleanList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEBOOLEANLIST, (container, name) -> {
			assertThat(container.getBooleanList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBooleanList(name).getValues()).as(NEWFIELD).containsExactly(true);
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEBOOLEANLIST, (container, name) -> {
			assertThat(container.getBooleanList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getBooleanList(name).getValues()).as(NEWFIELD).containsExactly(false);
		});
	}

	@Override
	@Test
	public void testChangeToDate() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEDATE, (container, name) -> {
			assertThat(container.getDate(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEDATE, (container, name) -> {
			assertThat(container.getDate(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToDateList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEDATELIST, (container, name) -> {
			assertThat(container.getDateList(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEDATELIST, (container, name) -> {
			assertThat(container.getDateList(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToHtml() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEHTML, (container, name) -> {
			assertThat(container.getHtml(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getHtml(name).getHTML()).as(NEWFIELDVALUE).isEqualTo("true");
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEHTML, (container, name) -> {
			assertThat(container.getHtml(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getHtml(name).getHTML()).as(NEWFIELDVALUE).isEqualTo("false");
		});
	}

	@Override
	@Test
	public void testChangeToHtmlList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEHTMLLIST, (container, name) -> {
			assertThat(container.getHTMLList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getHTMLList(name).getValues()).as(NEWFIELDVALUE).containsExactly("true");
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEHTMLLIST, (container, name) -> {
			assertThat(container.getHTMLList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getHTMLList(name).getValues()).as(NEWFIELDVALUE).containsExactly("false");
		});
	}

	@Override
	@Test
	public void testChangeToMicronode() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEMICRONODE, (container, name) -> {
			assertThat(container.getMicronode(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEMICRONODE, (container, name) -> {
			assertThat(container.getMicronode(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToMicronodeList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATEMICRONODELIST, (container, name) -> {
			assertThat(container.getMicronodeList(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATEMICRONODELIST, (container, name) -> {
			assertThat(container.getMicronodeList(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToNode() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATENODE, (container, name) -> {
			assertThat(container.getNode(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATENODE, (container, name) -> {
			assertThat(container.getNode(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToNodeList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATENODELIST, (container, name) -> {
			assertThat(container.getNodeList(name)).as(NEWFIELD).isNull();
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATENODELIST, (container, name) -> {
			assertThat(container.getNodeList(name)).as(NEWFIELD).isNull();
		});
	}

	@Override
	@Test
	public void testChangeToNumber() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATENUMBER, (container, name) -> {
			assertThat(container.getNumber(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumber(name).getNumber()).as(NEWFIELDVALUE).isEqualTo(1L);
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATENUMBER, (container, name) -> {
			assertThat(container.getNumber(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumber(name).getNumber()).as(NEWFIELDVALUE).isEqualTo(0L);
		});
	}

	@Override
	@Test
	public void testChangeToNumberList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATENUMBERLIST, (container, name) -> {
			assertThat(container.getNumberList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumberList(name).getValues()).as(NEWFIELDVALUE).containsExactly(1L);
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATENUMBERLIST, (container, name) -> {
			assertThat(container.getNumberList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getNumberList(name).getValues()).as(NEWFIELDVALUE).containsExactly(0L);
		});
	}

	@Override
	@Test
	public void testChangeToString() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATESTRING, (container, name) -> {
			assertThat(container.getString(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getString(name).getString()).as(NEWFIELDVALUE).isEqualTo("true");
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATESTRING, (container, name) -> {
			assertThat(container.getString(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getString(name).getString()).as(NEWFIELDVALUE).isEqualTo("false");
		});
	}

	@Override
	@Test
	public void testChangeToStringList() throws IOException {
		changeType(CREATEBOOLEAN, FILLTRUE, FETCH, CREATESTRINGLIST, (container, name) -> {
			assertThat(container.getStringList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getStringList(name).getValues()).as(NEWFIELDVALUE).containsExactly("true");
		});

		changeType(CREATEBOOLEAN, FILLFALSE, FETCH, CREATESTRINGLIST, (container, name) -> {
			assertThat(container.getStringList(name)).as(NEWFIELD).isNotNull();
			assertThat(container.getStringList(name).getValues()).as(NEWFIELDVALUE).containsExactly("false");
		});
	}
}
