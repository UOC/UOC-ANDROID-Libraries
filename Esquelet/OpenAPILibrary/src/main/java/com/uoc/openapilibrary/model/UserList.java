package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.uoc.openapilibrary.Constants;
import com.google.gson.Gson;
import com.uoc.openapilibrary.RESTMethod;

public class UserList {
	private ArrayList<User> users;

	public UserList() {
	}
	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> userList) {
		this.users = userList;
	}

    private static UserList JSONtoUserList(String userList) {
        return new Gson().fromJson(userList, UserList.class);
    }

    /*
    * Get the tutors of the user that is using the application
    * There is a tutor for each degree the user is enrolled in.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   UserList
    *
     */
	public static UserList getUserTutors(String token) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"user/tutors",
						token);

		return JSONtoUserList(usl);
	}

    /*
    * Get the list of members of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:  Oauth access token.
    *
    * - id:     Clasroom's identifier.
    *
    * Response:
    *   UserList
    *
     */
	public static UserList getClassroomsIdPeople(String token, String id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/people",
						token);
		return JSONtoUserList(usl);
	}

    /*
    * Get the list of students of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:   Oauth access token.
    *
    * - id:      Clasroom's identifier.
    *
    * Response:
    *   UserList
    *
     */
	public static UserList getClassroomsIdPeopleStudents(String token, String id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/people/students",
						token);

		return JSONtoUserList(usl);
	}

    /*
    * Get the list of the lecturers of the classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:   Oauth access token.
    *
    * - id:      Clasroom's identifier.
    *
    * Response:
    *   UserList
    *
     */
	public static UserList getClassroomsIdPeopleTeachers(String token, String id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/people/teachers",
						token);

		return JSONtoUserList(usl);
	}

    /*
    * Get the tutors of the person.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - person_id:      Person identifier.
    *
    * Response:
    *   UserList
    *
     */
	public static UserList getPeopleIdTutors(String token, String person_id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"people/"+person_id+"/tutors",
						token);

		return JSONtoUserList(usl);
	}


}