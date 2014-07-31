package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

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

    /*
    * Get the classrooms which the user that is using the application is enrolled in.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token: Oauth access token.
    *
    * Response:
    *   ClassroomList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ClassroomList getClassrooms(String token, String baseUri) throws Exception{
		return JSONtoClassroomList(RESTMethod.Get(
                baseUri +"classrooms",
                token));
	}

    /*
    * Get all the workgroups of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier
    *
    * Response:
    *   ClassroomList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ClassroomList getClassroomsIdGroups(String token, String id, String baseUri) throws Exception{
		return JSONtoClassroomList(RESTMethod.Get(
                baseUri +"classrooms/"+id+"/groups",
                token));
	}

    /*
    * Get the subjects which the user that is using the application is enrolled in.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * Response:
    *   ClassroomList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ClassroomList getSubjects(String token, String baseUri) throws Exception{
        return JSONtoClassroomList(RESTMethod.Get(
                baseUri +"subjects",
                token));
	}
}