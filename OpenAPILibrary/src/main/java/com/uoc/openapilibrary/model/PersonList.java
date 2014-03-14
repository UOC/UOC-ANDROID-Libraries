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
	
	public static PersonList getPeople(String token, PeopleSearchOptions so) {
		String pl = RESTMethod.Get(
				Constants.BASEURI +"people",
				token, so.toUrlParameter());

		return JSONToPersonList(pl);
	}
}
