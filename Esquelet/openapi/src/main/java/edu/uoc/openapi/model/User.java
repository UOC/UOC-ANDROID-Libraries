package edu.uoc.openapi.model;

import edu.uoc.openapi.Constants;
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
		Gson gson = new Gson();
		User obj = gson.fromJson(userJSON, User.class);
		return obj;
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
     */
    public static User getUser(String token) {
        String u = RESTMethod.Get(
                Constants.BASEURI +"user",
                token);

        return JSONToUser(u);
    }

}
