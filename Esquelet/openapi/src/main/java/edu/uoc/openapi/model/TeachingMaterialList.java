package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class TeachingMaterialList {
	private ArrayList<TeachingMaterial> materials;

    public TeachingMaterialList() {
        //Default constructor
    }
	public ArrayList<TeachingMaterial> getTeachingMaterials() {
		return materials;
	}
	public void setTeachingMaterials(ArrayList<TeachingMaterial> materials) {
		this.materials = materials;
	}

    private static TeachingMaterialList JSONtoTeachingMaterialList(String teachingMateriallist) {
        return new Gson().fromJson(teachingMateriallist, TeachingMaterialList.class);
    }

    /*
    * Get the list of learning materials of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier
    *
    * Response:
    *   TeachingMaterialList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public TeachingMaterialList getClassroomsIdMaterials(String token, String id, String baseUri)
            throws Exception{

        return JSONtoTeachingMaterialList(RESTMethod.Get(
                baseUri +"classrooms/"+id+"/materials",
                token));
	}
}