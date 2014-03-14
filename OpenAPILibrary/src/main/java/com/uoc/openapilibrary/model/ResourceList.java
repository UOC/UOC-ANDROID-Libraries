package com.uoc.openapilibrary.model;

import java.util.ArrayList;
import com.uoc.openapilibrary.Constants;
import com.google.gson.Gson;
import com.uoc.openapilibrary.RESTMethod;

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

	public static ResourceList getClassroomsIdResources(String token, String id) {
		String rl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/resources",
						token);

		return JSONtoResourceList(rl);
	}
	
	public static ResourceList getSubjectsIdResources(String token, String subject_id) {
		String rl = RESTMethod
				.Get(Constants.BASEURI +"subjects/"+subject_id+"/resources",
						token);

		return JSONtoResourceList(rl);
	}
}