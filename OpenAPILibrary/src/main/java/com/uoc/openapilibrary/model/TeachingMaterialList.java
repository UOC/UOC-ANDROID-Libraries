package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

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
	
	public static TeachingMaterialList getClassroomsIdMaterials(String token, String id) {
		String tml = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+id+"/materials",
				token);

		return JSONtoTeachingMaterialList(tml);
	}
}