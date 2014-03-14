package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import android.util.Log;

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

	public static UserList getUserTutors(String token) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"user/tutors",
						token);

		return JSONtoUserList(usl);
	}
	
	public static UserList getClassroomsIdPeople(String token, String id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/people",
						token);
		Log.v("USERUSER", usl);
		return JSONtoUserList(usl);
	}
	
	public static UserList getClassroomsIdPeopleStudents(String token, String id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/people/students",
						token);

		return JSONtoUserList(usl);
	}
	
	public static UserList getClassroomsIdPeopleTeachers(String token, String id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"classrooms/"+id+"/people/teachers",
						token);

		return JSONtoUserList(usl);
	}
	
	public static UserList getPeopleIdTutors(String token, String person_id) {
		String usl = RESTMethod
				.Get(Constants.BASEURI +"people/"+person_id+"/tutors",
						token);

		return JSONtoUserList(usl);
	}


}