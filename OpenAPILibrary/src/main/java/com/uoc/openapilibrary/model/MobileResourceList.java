package com.uoc.openapilibrary.model;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.uoc.openapilibrary.Constants;
import com.uoc.openapilibrary.RESTMethod;

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

    public static MobileResourceList getMobileResources(String token) {
        String appl = RESTMethod.Get(
                Constants.BASEURI +"mobileresources",
                token);
        return JSONtoAppList(appl);
    }

    public static MobileResourceList getMobileResourcesByType(String token, String type) {
        String appl = RESTMethod.Get(
                Constants.BASEURI +"mobileresources/bytype/"+type,
                token);

        return JSONtoAppList(appl);
    }
}