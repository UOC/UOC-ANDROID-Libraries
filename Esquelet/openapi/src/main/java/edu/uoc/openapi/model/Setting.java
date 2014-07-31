package edu.uoc.openapi.model;

public class Setting {
	private String id;
	private String title;
	private String description;
	private String section;
	private String url;
	private String value;
	
	public Setting() {
		//Default constructor
	}

    public Setting(String id, String title, String description, String section, String url,
                   String value) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.section = section;
        this.url = url;
        this.value = value;
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
