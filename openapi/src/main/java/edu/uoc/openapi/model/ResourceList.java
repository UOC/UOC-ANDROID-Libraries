package edu.uoc.openapi.model;

import java.util.ArrayList;
import edu.uoc.openapi.Constants;
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
     */
	public static ResourceList getClassroomsIdResources(String token, String id) {
		String rl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/resources",
						token);

		return JSONtoResourceList(rl);
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
     */
	public static ResourceList getSubjectsIdResources(String token, String subject_id) {
		String rl = RESTMethod
				.Get(Constants.BASEURI +"subjects/"+subject_id+"/resources",
						token);

		return JSONtoResourceList(rl);
	}
}