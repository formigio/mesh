package com.gentics.mesh.core.data.schema;

import java.util.List;
import java.util.Objects;

import com.gentics.mesh.core.data.IndexableElement;
import com.gentics.mesh.core.data.node.Node;
import com.gentics.mesh.core.rest.schema.Schema;
import com.gentics.mesh.core.rest.schema.SchemaReference;

/**
 * A schema container is a graph element which stores the JSON schema data.
 */
public interface SchemaContainer
		extends GraphFieldSchemaContainer<Schema, SchemaReference, SchemaContainer, SchemaContainerVersion>, IndexableElement {

	/**
	 * Type Value: {@value #TYPE}
	 */
	static final String TYPE = "schemaContainer";

	/**
	 * Compose the index name for the schema index.
	 * 
	 * @return
	 */
	static String composeIndexName() {
		return TYPE.toLowerCase();
	}

	/**
	 * Compose the index type for the schema index.
	 * 
	 * @return
	 */
	static String composeIndexType() {
		return TYPE.toLowerCase();
	}

	/**
	 * Compose the documentId for schema index documents.
	 * 
	 * @param schemaContainerUuid
	 * @return
	 */
	static String composeDocumentId(String schemaContainerUuid) {
		Objects.requireNonNull(schemaContainerUuid, "A schemaContainerUuid must be provided.");
		return schemaContainerUuid;
	}

	@Override
	default String getType() {
		return TYPE;
	}

	/**
	 * Return the list of nodes which are referencing the schema container.
	 * 
	 * @return
	 */
	List<? extends Node> getNodes();

}