package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.RESTMethod;

public class SettingList {
	private ArrayList<Setting> settings;

	public SettingList() {
		// Default constructor
	}
	public ArrayList<Setting> getSettings() {
		return settings;
	}
	public void setSettings(ArrayList<Setting> settings) {
		this.settings = settings;
	}

    private static SettingList JSONtoSettingList(String settingList) {
        return new Gson().fromJson(settingList, SettingList.class);
    }

    /*
    * Get the settings of the user that is using the application.
    * The set of settings depend on the current profile of the user.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   SettingList
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
	public SettingList getUserSettings(String token, String baseUri) throws Exception{

        return JSONtoSettingList(RESTMethod.Get(
                baseUri +"user/settings",
                token));
	}
}

