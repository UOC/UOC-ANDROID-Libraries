package com.uoc.openapilibrary.model;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class TeachingMaterial {
	private String id;
	private String type;
	private String title;
	private String url;
	
	public TeachingMaterial() {
		//Default constructor
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private static String toJSON(TeachingMaterial newTMaterial) {
		return new Gson().toJson(newTMaterial);
	}
	
	private static TeachingMaterial JSONToTeachingMaterial(String tMaterialJSON) {
		Gson gson = new Gson();
		TeachingMaterial obj = gson.fromJson(tMaterialJSON, TeachingMaterial.class);
		return obj;
	}

    /*
    * Create a new learning material inside the classroom. The user must be a lecturer of the classroom to be
    * able to create the learning material.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier.
    *
    * - newTMaterial: The TeachingMaterial that you wish to create.
    *
    * Response:
    *   TeachingMaterial
    *
     */
	public static TeachingMaterial postClassroomsIdMaterials(String token, String id, TeachingMaterial newTMaterial) {
		String tml = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+id+"/materials",
				token, toJSON(newTMaterial));

		return JSONToTeachingMaterial(tml);
	}

    /*
    * Get a learning material from the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - tmaterial_id:   Learning material's identifier.
    *
    * Response:
    *   TeachingMaterial
    *
     */
	public static TeachingMaterial getClassroomsIdMaterialsId(String token, String domain_id, String tmaterial_id) {
		String tm = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/materials/"+tmaterial_id,
				token);

		return JSONToTeachingMaterial(tm);
	}

}