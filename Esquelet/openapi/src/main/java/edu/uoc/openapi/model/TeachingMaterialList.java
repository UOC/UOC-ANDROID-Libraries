package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.Constants;
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
     */
	public static TeachingMaterialList getClassroomsIdMaterials(String token, String id) {
		String tml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+id+"/materials",
				token);

		return JSONtoTeachingMaterialList(tml);
	}
}