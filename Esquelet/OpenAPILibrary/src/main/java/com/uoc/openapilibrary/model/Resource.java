package com.uoc.openapilibrary.model;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class Resource {
	private String id;
	private String type;
	private String subtype;
	private String title;
	private String code;
	private String domainId;
	
	public Resource() {
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
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

    private static String toJSON(Resource newResource) {
        return new Gson().toJson(newResource);
    }

    private static Resource JSONToResource(String resourceJSON) {
        Gson gson = new Gson();
        Resource obj = gson.fromJson(resourceJSON, Resource.class);
        return obj;
    }

    /*
    * Create a new resource inside the classroom. The user must be a lecturer of the classroom to be able to
    * create the resource.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - id:             Clasroom's identifier.
    *
    * - newResource:    The Resource you wish to create
    *
    * Response:
    *   Resource
    *
     */
	public static Resource postClassroomsIdResources(String token, String id, Resource newResource) {
		String r = RESTMethod.Post(
				Constants.BASEURI +"classrooms/"+id+"/resources",
				token, toJSON(newResource));

		return JSONToResource(r);
	}

    /*
    * Get the data of a resource of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Clasroom's identifier.
    *
    * - resource_id:    Resource's identifier.
    *
    * Response:
    *   Resource
    *
     */
	public static Resource getClassroomsIdResourcesId(String token, String domain_id, String resource_id) {
		String r = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+domain_id+"/resources/"+resource_id,
				token);

		return JSONToResource(r);
	}

    /*
    * Get the data of a resource of the subject.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * - resource_id:    Resource's identifier.
    *
    * Response:
    *   Resource
    *
     */
	public static Resource getSubjectsIdResourcesId(String token, String subject_id, String resource_id) {
		String r = RESTMethod.Get(
				Constants.BASEURI +"subjects/"+subject_id+"/resources/"+resource_id,
				token);

		return JSONToResource(r);
	}
}