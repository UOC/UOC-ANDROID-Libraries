package com.uoc.openapilibrary.model;

import java.util.ArrayList;
import com.uoc.openapilibrary.Constants;
import com.google.gson.Gson;
import com.uoc.openapilibrary.RESTMethod;

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
     */
	public static ProfileList getUserProfiles(String token) {
		String pfl = RESTMethod
				.Get(Constants.BASEURI +"user/profiles",
						token);

		return JSONtoProfileList(pfl);
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
     */
	public static ProfileList getPeopleIdProfiles(String token, String person_id) {
		String pfl = RESTMethod
				.Get(Constants.BASEURI +"people/"+person_id+"/profiles",
						token);

		return JSONtoProfileList(pfl);
	}

}
