package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class ClassroomList {
	private ArrayList<Classroom> classrooms;
	private ArrayList<Classroom> subjects;

    public ClassroomList() {
        //Default constructor
    }
	public ArrayList<Classroom> getClassrooms() {
		return classrooms;
	}
	public ArrayList<Classroom> getSubjects() {
		return subjects;
	}
	public void setClassroomList(ArrayList<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
	public void setSubjetcList(ArrayList<Classroom> classrooms) {
		this.subjects = classrooms;
	}

    private static ClassroomList JSONtoClassroomList(String classroomlist) {
        return new Gson().fromJson(classroomlist, ClassroomList.class);
    }

	public static ClassroomList getClassrooms(String token) {
		String crl = RESTMethod.Get(
				Constants.BASEURI +"classrooms",
				token);

		return JSONtoClassroomList(crl);
	}
	
	public static ClassroomList getClassroomsIdGroups(String token, String id) {
		String crl = RESTMethod.Get(
				Constants.BASEURI +"classrooms/"+id+"/groups",
				token);

		return JSONtoClassroomList(crl);
	}
	
	public static ClassroomList getSubjects(String token) {
		String crl = RESTMethod.Get(
				Constants.BASEURI +"subjects",
				token);

		return JSONtoClassroomList(crl);
	}
}