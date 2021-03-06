package com.gentics.mesh.core.rest.microschema.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.gentics.mesh.core.rest.schema.FieldSchema;
import com.gentics.mesh.core.rest.schema.Microschema;

public class MicroschemaCreateRequest implements Microschema {

	@JsonPropertyDescription("Description of the microschema")
	private String description;

	@JsonPropertyDescription("Name of the microschema")
	private String name;

	@JsonPropertyDescription("List of microschema fields")
	private List<FieldSchema> fields = new ArrayList<>();


	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public MicroschemaCreateRequest setDescription(String description) {
		this.description = description;
		return this;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public MicroschemaCreateRequest setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public List<FieldSchema> getFields() {
		return fields;
	}

	@Override
	public MicroschemaCreateRequest setFields(List<FieldSchema> fields) {
		this.fields = fields;
		return this;
	}

	@Override
	public String toString() {
		String fields = getFields().stream().map(field -> field.getName()).collect(Collectors.joining(","));
		return getName() + " fields: {" + fields + "}";
	}

}
