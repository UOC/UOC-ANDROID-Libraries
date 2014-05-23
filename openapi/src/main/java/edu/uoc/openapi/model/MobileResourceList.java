package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import edu.uoc.openapi.Constants;
import edu.uoc.openapi.RESTMethod;

public class MobileResourceList {
    private ArrayList<MobileResource> mobileResources;

    public MobileResourceList() {
        //Default constructor
    }
    public ArrayList<MobileResource> getApps() {
        return mobileResources;
    }
    public void setApps(ArrayList<MobileResource> applist) {
        this.mobileResources = applist;
    }

    private static MobileResourceList JSONtoAppList(String applist) {
        return new Gson().fromJson(applist, MobileResourceList.class);
    }

    /*
    * Get the list of mobile resources.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * Response:
    *   MobileResourceList
    *
     */
    public static MobileResourceList getMobileResources(String token) {
        String appl = RESTMethod.Get(
                Constants.BASEURI +"mobileresources",
                token);
        return JSONtoAppList(appl);
    }

    /*
    * Get the list of mobile resources filtered by type.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - type:           Resource type of the resources you want to find. In lowercase.
    *
    * Response:
    *   MobileResourceList
    *
     */
    public static MobileResourceList getMobileResourcesByType(String token, String type) {
        String appl = RESTMethod.Get(
                Constants.BASEURI +"mobileresources/bytype/"+type,
                token);

        return JSONtoAppList(appl);
    }

    /*
    * Get the list of mobile resources of a certain classroom.
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - domain_id:      Classroom's identifier.
    *
    * Response:
    *   MobileResourceList
    *
     */
    public static MobileResourceList getClassroomsIdResourcesApps(String token, String domain_id) {
        String appl = RESTMethod.Get(
                Constants.BASEURI +"classrooms/"+domain_id+"/resources/apps",
                token);
        return JSONtoAppList(appl);
    }
}