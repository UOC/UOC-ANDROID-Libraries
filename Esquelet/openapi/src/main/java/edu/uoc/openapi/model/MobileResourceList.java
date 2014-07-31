package edu.uoc.openapi.model;

import java.util.ArrayList;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class MobileResourceList {
    private ArrayList<MobileResource> mobileResources;

    public MobileResourceList() {
        //Default constructor
    }

    public MobileResourceList(ArrayList<MobileResource> mobileResources) {
        this.mobileResources = mobileResources;
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
    *   If Get call to the server fails, it throws an exception
    *
     */
    public MobileResourceList getMobileResources(String token, String baseUri) throws Exception{

        return JSONtoAppList(RESTMethod.Get(
                baseUri +"mobileresources",
                token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
    public MobileResourceList getMobileResourcesByType(String token, String type, String baseUri)
            throws Exception{

        return JSONtoAppList(RESTMethod.Get(
                baseUri +"mobileresources/bytype/"+type,
                token));
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
    *   If Get call to the server fails, it throws an exception
    *
     */
    public MobileResourceList getClassroomsIdResourcesApps(String token, String domain_id,
                                                           String baseUri)
            throws Exception{

        return JSONtoAppList(RESTMethod.Get(
                baseUri +"classrooms/"+domain_id+"/resources/apps",
                token));
    }
}