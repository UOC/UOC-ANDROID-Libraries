package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class Person {
	private String id;
	private String username;
	private String name;
	private String surname1;
	private String surname2;
	private String email;
	private ArrayList<Profile> profiles;
	private String userNumber;
	private String hobbies;
	private String skills;
	private String about;
	private String NGOes;
	private String languages;
	private String secondaryEmail;
	private String blog;
	private String personalSite;
	private String lastUpdate;

    public Person(){

    }

    public Person(String id, String username, String surname1, String name, String surname2,
                  String email, ArrayList<Profile> profiles, String userNumber, String hobbies,
                  String NGOes, String about, String skills, String languages, String secondaryEmail,
                  String blog, String lastUpdate, String personalSite){
        this.id = id;
        this.username = username;
        this.surname1 = surname1;
        this.name = name;
        this.surname2 = surname2;
        this.email = email;
        this.profiles = profiles;
        this.userNumber = userNumber;
        this.hobbies = hobbies;
        this.NGOes = NGOes;
        this.about = about;
        this.skills = skills;
        this.languages = languages;
        this.secondaryEmail = secondaryEmail;
        this.blog = blog;
        this.lastUpdate = lastUpdate;
        this.personalSite = personalSite;
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname1() {
		return surname1;
	}
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	public String getSurname2() {
		return surname2;
	}
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(ArrayList<Profile> profiles) {
		this.profiles = profiles;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getNGOes() {
		return NGOes;
	}
	public void setNGOes(String nGOes) {
		NGOes = nGOes;
	}
	public String getLanguages() {
		return languages;
	}
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	public String getSecondaryEmail() {
		return secondaryEmail;
	}
	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}
	public String getBlog() {
		return blog;
	}
	public void setBlog(String blog) {
		this.blog = blog;
	}
	public String getPersonalSite() {
		return personalSite;
	}
	public void setPersonalSite(String personalSite) {
		this.personalSite = personalSite;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	private static Person JSONToPerson(String personJSON) {
		return new Gson().fromJson(personJSON, Person.class);
	}

    /*
    * Get the public data of one person.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - person_id:      Person identifier.
    *
    * Response:
    *   Person
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Person getPeopleId(String token, String person_id, String baseUri) throws Exception{

        return JSONToPerson(RESTMethod.Get(
                baseUri +"people/"+person_id,
                token));
	}
}