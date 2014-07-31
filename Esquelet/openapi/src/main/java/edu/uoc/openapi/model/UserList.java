package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.RESTMethod;

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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public UserList getUserTutors(String token, String baseUri) throws Exception{

        return JSONtoUserList(RESTMethod
                .Get(baseUri +"user/tutors",
                        token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public UserList getClassroomsIdPeople(String token, String id, String baseUri) throws Exception{

        return JSONtoUserList(RESTMethod
                .Get(baseUri +"classrooms/"+id+"/people",
                        token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public UserList getClassroomsIdPeopleStudents(String token, String id, String baseUri)
            throws Exception{

        return JSONtoUserList(RESTMethod
                .Get(baseUri +"classrooms/"+id+"/people/students",
                        token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public UserList getClassroomsIdPeopleTeachers(String token, String id, String baseUri)
            throws Exception{

        return JSONtoUserList(RESTMethod
                .Get(baseUri +"classrooms/"+id+"/people/teachers",
                        token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public UserList getPeopleIdTutors(String token, String person_id, String baseUri)
            throws Exception{

        return JSONtoUserList(RESTMethod
                .Get(baseUri +"people/"+person_id+"/tutors",
                        token));
	}


}