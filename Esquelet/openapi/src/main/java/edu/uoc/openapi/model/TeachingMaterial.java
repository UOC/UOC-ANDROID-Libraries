package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

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
		return new Gson().fromJson(tMaterialJSON, TeachingMaterial.class);
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public TeachingMaterial postClassroomsIdMaterials(String token, String id,
                                                             TeachingMaterial newTMaterial,
                                                             String baseUri)
            throws Exception{

        return JSONToTeachingMaterial(RESTMethod.Post(
                baseUri +"classrooms/"+id+"/materials",
                token, newTMaterial));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public TeachingMaterial getClassroomsIdMaterialsId(String token, String domain_id,
                                                              String tmaterial_id, String baseUri)
            throws Exception{

        return JSONToTeachingMaterial(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/materials/"+tmaterial_id,
                token));
	}

}