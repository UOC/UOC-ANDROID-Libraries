package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

public class PersonList {
	private ArrayList<Person> people;

    public PersonList(){

    }
	public ArrayList<Person> getPeople() {
		return people;
	}
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	
	private static PersonList JSONToPersonList(String personList) {
		return new Gson().fromJson(personList, PersonList.class);
	}

    /*
    * Search users.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - so:             PeopleSearchOptions object with the options needed to filter your search
    *
    * Response:
    *   PersonList
    *
     */
	public static PersonList getPeople(String token, PeopleSearchOptions so) {
		String pl = RESTMethod.Get(
				Constants.BASEURI +"people",
				token, so.toUrlParameter());

		return JSONToPersonList(pl);
	}
}
