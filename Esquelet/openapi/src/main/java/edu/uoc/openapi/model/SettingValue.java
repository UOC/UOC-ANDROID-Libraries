package edu.uoc.openapi.model;

public class SettingValue {
	private String description;
	private String value;
	
	public SettingValue(){
		//Default constructor
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
