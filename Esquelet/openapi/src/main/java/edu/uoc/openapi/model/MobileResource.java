package edu.uoc.openapi.model;

import com.google.gson.Gson;

import edu.uoc.openapi.RESTMethod;

public class MobileResource {
    private String id;
    private String type;
    private String urlIOS;
    private String nameIOS;
    private String logoIOS;
    private String descriptionIOS;
    private String urlMarketIOS;
    private String urlAndroid;
    private String nameAndroid;
    private String logoAndroid;
    private String descriptionAndroid;
    private String urlMarketAndroid;

    public MobileResource() {
        //Default constructor
    }

    public MobileResource(String id, String type, String urlIOS, String nameIOS, String logoIOS,
                          String descriptionIOS, String urlMarketIOS, String urlAndroid,
                          String nameAndroid, String logoAndroid, String urlMarketAndroid,
                          String descriptionAndroid) {
        this.id = id;
        this.type = type;
        this.urlIOS = urlIOS;
        this.nameIOS = nameIOS;
        this.logoIOS = logoIOS;
        this.descriptionIOS = descriptionIOS;
        this.urlMarketIOS = urlMarketIOS;
        this.urlAndroid = urlAndroid;
        this.nameAndroid = nameAndroid;
        this.logoAndroid = logoAndroid;
        this.urlMarketAndroid = urlMarketAndroid;
        this.descriptionAndroid = descriptionAndroid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrlIOS() {
        return urlIOS;
    }

    public void setUrlIOS(String urlIOS) {
        this.urlIOS = urlIOS;
    }

    public String getNameIOS() {
        return nameIOS;
    }

    public void setNameIOS(String nameIOS) {
        this.nameIOS = nameIOS;
    }

    public String getLogoIOS() {
        return logoIOS;
    }

    public void setLogoIOS(String logoIOS) {
        this.logoIOS = logoIOS;
    }

    public String getDescriptionIOS() {
        return descriptionIOS;
    }

    public void setDescriptionIOS(String descriptionIOS) {
        this.descriptionIOS = descriptionIOS;
    }

    public String getUrlMarketIOS() {
        return urlMarketIOS;
    }

    public void setUrlMarketIOS(String urlMarketIOS) {
        this.urlMarketIOS = urlMarketIOS;
    }

    public String getUrlAndroid() {
        return urlAndroid;
    }

    public void setUrlAndroid(String urlAndroid) {
        this.urlAndroid = urlAndroid;
    }

    public String getNameAndroid() {
        return nameAndroid;
    }

    public void setNameAndroid(String nameAndroid) {
        this.nameAndroid = nameAndroid;
    }

    public String getLogoAndroid() {
        return logoAndroid;
    }

    public void setLogoAndroid(String logoAndroid) {
        this.logoAndroid = logoAndroid;
    }

    public String getDescriptionAndroid() {
        return descriptionAndroid;
    }

    public void setDescriptionAndroid(String descriptionAndroid) {
        this.descriptionAndroid = descriptionAndroid;
    }

    public String getUrlMarketAndroid() {
        return urlMarketAndroid;
    }

    public void setUrlMarketAndroid(String urlMarketAndroid) {
        this.urlMarketAndroid = urlMarketAndroid;
    }

    private static MobileResource JSONToApp(String appJSON) {
        return new Gson().fromJson(appJSON, MobileResource.class);
    }

    /*
    * Get the mobile resource with identifier "resource_id".
    * The user must have given the application the grant READ to use these operation.
    *
    * Parameters:
    *
    * - token:          Oauth access token.
    *
    * - resource_id:    Identifier of the mobile resource.
    *
    * Response:
    *   MobileResource
    *
    *   If Get call to the server fails, it throws an exception
    *
     */
    public MobileResource getClassroomsIdResourcesAppsMail(String token, String resource_id,
                                                           String baseUri)
            throws Exception{

        return JSONToApp(RESTMethod.Get(
                baseUri +"mobileresources/"+resource_id,
                token));
    }

}
