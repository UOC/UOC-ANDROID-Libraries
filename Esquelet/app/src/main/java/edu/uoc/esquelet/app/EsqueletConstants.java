package edu.uoc.esquelet.app;


import java.io.Serializable;

import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;

public class EsqueletConstants implements ConstantsInterface, Serializable {

    static String CLIENT = "EUI3WhtdZMjwOLxwFyzUcLUUXeE0ACmB6See1HWRjoCSIgGJ1If8EI8EPorzSWFRRnw1fxkOvIqkBI1d91GGKKG6nFnD35OY6VOEx4LcFLQIW3Z2jGsTaYey4bUcbR4K";
    //static String REDIRECT_URI = "uoc://oauthresponse";
    static String REDIRECT_URI = "http://google.es";
    static String ACCES_TOKEN_URI = "http://oslo.uoc.es:8080/webapps/uocapi/oauth/token";
    static String AUTHORIZE_URI = "http://oslo.uoc.es:8080/webapps/uocapi/oauth/authorize";
    static String APP_URI =  "http://oslo.uoc.es:8080/webapps/uocapi/api/v1/app";
    static String SECRET = "scR6rJl1nssYW3I2gYaUslSsBKSMeMOp8EoyEuLgJ61MmQgtaTSBAgIknrqBqO4L26wMXWVkz5Z7WJtCyZ9huK33Z1JU95vqNbKRDRv0CoAa5z6WPdSBe5c75N5tRf2R";
    static String BASEURI = "http://oslo.uoc.es:8080/webapps/uocapi/api/v1/";
    static String CONFIG_FILE = "config";
    static String APP_NAME = "UOCEsquelet";

    public EsqueletConstants() {

    }

    @Override
    public String getClient() {
        return CLIENT;
    }

    @Override
    public String getAuthorize() {
        return AUTHORIZE_URI;
    }

    @Override
    public String getParamDevice() {
        return "?device=";
    }

    @Override
    public String getParamClient() {
        return "&client_id=";
    }

    @Override
    public String getParamRedirectUri() {
        return "&redirect_uri=";
    }

    @Override
    public String getRedirectUri() {
        return REDIRECT_URI;
    }

    @Override
    public String getParamResponseType() {
        return "&response_type=";
    }

    @Override
    public String getResponseType() {
        return "code";
    }

    @Override
    public String getToken() {
        return ACCES_TOKEN_URI;
    }

    @Override
    public String getSecret() {
        return SECRET;
    }

    @Override
    public String getAPPUri() {
        return APP_URI;
    }

    @Override
    public String getBaseUri() {
        return BASEURI;
    }

    @Override
    public String getConfigFile() {
        return CONFIG_FILE;
    }

    @Override
    public String getAppName() { return APP_NAME;}
}
