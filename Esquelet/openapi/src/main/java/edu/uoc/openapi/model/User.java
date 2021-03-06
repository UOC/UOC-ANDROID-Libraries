package edu.uoc.openapi.model;

import com.google.gson.Gson;
import edu.uoc.openapi.RESTMethod;

public class User {
	private String fullName;
	private String id;
	private String language;
	private String name;
	private String photoUrl;
	private String username;
    private String number;
    private String sessionId;
    private String email;

	public User(){
		//Default constructor
	}

    public User(String fullName, String id, String language, String name, String photoUrl,
                String username, String number, String sessionId, String email) {
        this.fullName = fullName;
        this.id = id;
        this.language = language;
        this.name = name;
        this.photoUrl = photoUrl;
        this.username = username;
        this.number = number;
        this.sessionId = sessionId;
        this.email = email;
    }

    public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getId() {
		return id;
	}
	public String getLanguage() {
		return language;
	}
	public void setLenguage(String language) {
		this.language = language;
	}
	public String getName() {
		return name;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getUsername() {
		return username;
	}
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

	private static User JSONToUser(String userJSON) {
		return new Gson().fromJson(userJSON, User.class);
	}

    /*
    * Get personal data about the user (the user that is using the application).
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   User
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
    public User getUser(String token, String baseUri) throws Exception{

        return JSONToUser(RESTMethod.Get(
                baseUri +"user",
                token));
    }

}
