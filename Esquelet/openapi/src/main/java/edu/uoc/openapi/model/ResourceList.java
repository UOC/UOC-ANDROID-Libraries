package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.RESTMethod;

public class ResourceList {

	private ArrayList<Resource> resources;

	public ResourceList() {
	}
	public ArrayList<Resource> getResources() {
		return resources;
	}
	public void setResources(ArrayList<Resource> resourcesList) {
		this.resources = resourcesList;
	}

    private static ResourceList JSONtoResourceList(String resourceList) {
        return new Gson().fromJson(resourceList, ResourceList.class);
    }

    /*
    * Get the resource's list of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier.
    *
    * Response:
    *   ResourceList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ResourceList getClassroomsIdResources(String token, String id, String baseUri)
            throws Exception{

        return JSONtoResourceList(RESTMethod
                .Get(baseUri +"classrooms/"+id+"/resources",
                        token));
	}

    /*
    * Get the resource's list of the subject.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - subject_id:     Subject's identifier.
    *
    * Response:
    *   ResourceList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ResourceList getSubjectsIdResources(String token, String subject_id, String baseUri)
            throws Exception{

        return JSONtoResourceList(RESTMethod
                .Get(baseUri +"subjects/"+subject_id+"/resources",
                        token));
	}
}