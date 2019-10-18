package com.planon.mdb.dto;

public class ConfluenceDTO {
	private String tag;
	private String name;
	private String description;
	private String version;
	private String hierarchy;
	private String type;

	public ConfluenceDTO(String aTag, String aName, String aDescription, String aVersion, String aHierarchy,
			String aType) {
		this.tag = aTag;
		this.name = aName;
		this.description = aDescription;
		this.version = aVersion;
		this.hierarchy = aHierarchy;
		this.type = aType;
	}

	@Override
	public String toString() {
		return "ConfluenceDTO [tag=" + tag + ", name=" + name + ", description=" + description + ", version=" + version
				+ ", hierarchy=" + hierarchy + ", type=" + type + "]";
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String aTag) {
		this.tag = aTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		this.name = aName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String aDescription) {
		this.description = aDescription;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String aVersion) {
		this.version = aVersion;
	}

	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String aHierarchy) {
		this.hierarchy = aHierarchy;
	}

	public String getType() {
		return type;
	}

	public void setType(String aType) {
		this.type = aType;
	}

}
