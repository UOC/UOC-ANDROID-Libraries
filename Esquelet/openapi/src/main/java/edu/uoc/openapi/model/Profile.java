package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;


public class Profile {
	private String appId;
	private String app;
	private String id;
	private String userSubtypeId;
	private String userType;
	private String usertypeId;
	private String userSubtype;
	private String language;
	
	public Profile(){
		//Default constructor
	}

    public Profile(String appId, String app, String id, String userSubtypeId, String userType,
                   String usertypeId, String userSubtype, String language) {
        this.appId = appId;
        this.app = app;
        this.id = id;
        this.userSubtypeId = userSubtypeId;
        this.userType = userType;
        this.usertypeId = usertypeId;
        this.userSubtype = userSubtype;
        this.language = language;
    }

    public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserSubtypeId() {
		return userSubtypeId;
	}
	public void setUserSubtypeId(String userSubtypeId) {
		this.userSubtypeId = userSubtypeId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsertypeId() {
		return usertypeId;
	}
	public void setUsertypeId(String usertypeId) {
		this.usertypeId = usertypeId;
	}
	public String getUserSubtype() {
		return userSubtype;
	}
	public void setUserSubtype(String userSubtype) {
		this.userSubtype = userSubtype;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

    private static Profile JSONtoProfile(String profileJSON) {
        return new Gson().fromJson(profileJSON, Profile.class);
    }

    private static String toJSON(Profile newProfile) {
        return new Gson().toJson(newProfile);
    }

    /*
    * Get the current profile of the user that is usgin the application
    * The current profile is the profile the user is connected to the Virtual Campus with.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   Profile
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Profile getUserProfilesCurrent(String token, String baseUri) throws Exception{
		return JSONtoProfile(RESTMethod
				.Get(baseUri +"user/profiles/current",
						token));
	}

    /*
    * Update the ccurrent profile of the user that is using the application
    * The current profile is the profile the user is connected to the Virtual Campus with.
    * The user must have given the application the grant WRITE to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - newProfile:     The Profile object that you wish to create.
    *
    * Response:
    *   Profile
    *
    *   If Put call to the server fails, it throws an exception
    *
     */
	public Profile putUserProfilesCurrent(String token, Profile newProfile, String baseUri)
            throws Exception{

        return JSONtoProfile(RESTMethod
				.Put(baseUri +"user/profiles/current",
						token, newProfile));
	}

    /*
    * Get the current profile of the person
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - person_id:     Message identifier
    *
    * Response:
    *   Profile
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public Profile getPeopleIdProfilesCurrent(String token, String person_id, String baseUri)
            throws Exception{

        return JSONtoProfile(RESTMethod
				.Get(baseUri +"people/"+person_id+"/profiles/current",
						token));
	}
}
