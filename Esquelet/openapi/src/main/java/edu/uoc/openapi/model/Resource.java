package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

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

    public Resource(String id, String type, String subtype, String title, String code,
                    String domainId) {
        this.id = id;
        this.type = type;
        this.subtype = subtype;
        this.title = title;
        this.code = code;
        this.domainId = domainId;
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
        return new Gson().fromJson(resourceJSON, Resource.class);
    }

    /*
    * Create a new resource inside the classroom. The user must be a lecturer of the classroom to be
    * able to create the resource.
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
    *   If Post call to the server fails, it throws an exception
    *
     */
	public Resource postClassroomsIdResources(String token, String id, Resource newResource,
                                              String baseUri)
            throws Exception{

        return JSONToResource(RESTMethod.Post(
                baseUri +"classrooms/"+id+"/resources",
                token, newResource));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Resource getClassroomsIdResourcesId(String token, String domain_id,
                                                      String resource_id, String baseUri)
            throws Exception{

        return JSONToResource(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/resources/"+resource_id,
                token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Resource getSubjectsIdResourcesId(String token, String subject_id,
                                                    String resource_id, String baseUri)
            throws Exception{

        return JSONToResource(RESTMethod.Get(
                baseUri +"subjects/"+subject_id+"/resources/"+resource_id,
                token));
	}
}