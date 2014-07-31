package edu.uoc.openapi.model;


import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.RESTMethod;

public class ProfileList {
	ArrayList<Profile> profiles;

	public ProfileList() {
	}
	public ArrayList<Profile> getProfiles() {
		return profiles;
	}
	public void setProfiles(ArrayList<Profile> profileList) {
		this.profiles = profileList;
	}

    private static ProfileList JSONtoProfileList(String profileList) {
        return new Gson().fromJson(profileList, ProfileList.class);
    }

    /*
    * Get the profiles of the user that is using the application
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   ProfileList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ProfileList getUserProfiles(String token, String baseUri) throws Exception{

        return JSONtoProfileList(RESTMethod
                .Get(baseUri +"user/profiles",
                        token));
	}

    /*
    * Get the profiles of the person.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - person_id:      Person identifier.
    *
    * Response:
    *   ProfileList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public ProfileList getPeopleIdProfiles(String token, String person_id, String baseUri)
            throws Exception{

        return JSONtoProfileList(RESTMethod
                .Get(baseUri +"people/"+person_id+"/profiles",
                        token));
	}

}
