package com.uoc.openapilibrary.model;

import com.google.gson.Gson;

import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;
import com.uoc.openapilibrary.Utils;



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

	public static Profile getUserProfilesCurrent(String token) {
		return JSONtoProfile(RESTMethod
				.Get(Constants.BASEURI +"user/profiles/current",
						token));
	}

	public static Profile putUserProfilesCurrent(String token, Profile newProfile) {
		return JSONtoProfile(RESTMethod
				.Put(Constants.BASEURI +"user/profiles/current",
						token, Utils.toJSON(newProfile)));
	}
	
	public static Profile getPeopleIdProfilesCurrent(String token, String person_id) {
		return JSONtoProfile(RESTMethod
				.Get(Constants.BASEURI +"people/"+person_id+"/profiles/current",
						token));
	}
}
