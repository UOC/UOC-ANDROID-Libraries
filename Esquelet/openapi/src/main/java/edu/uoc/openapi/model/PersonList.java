package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

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
    *   If Get call to the server fails, it throws an exception
    *
     */
	public PersonList getPeople(String token, PeopleSearchOptions so, String baseUri)
            throws Exception{

        return JSONToPersonList(RESTMethod.Get(
                baseUri +"people",
                token, so.toUrlParameter()));
	}
}
