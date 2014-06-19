package edu.uoc.skeleton;


import edu.uoc.openapi.AbstractConstants;

public class Constants extends AbstractConstants {

    static String CLIENT = "EUI3WhtdZMjwOLxwFyzUcLUUXeE0ACmB6See1HWRjoCSIgGJ1If8EI8EPorzSWFRRnw1fxkOvIqkBI1d91GGKKG6nFnD35OY6VOEx4LcFLQIW3Z2jGsTaYey4bUcbR4K";
    static String REDIRECT_URI = "uoc://oauthresponse";
    static String ACCES_TOKEN_URI = "http://54.75.227.139:8080/uocapi/oauth/token";
    static String AUTHORIZE_URI = "http://54.75.227.139:8080/uocapi/oauth/authorize";
    static String APP_URI =  "http://54.75.227.139:8080/uocapi/api/v1/app";
    static String SECRET = "scR6rJl1nssYW3I2gYaUslSsBKSMeMOp8EoyEuLgJ61MmQgtaTSBAgIknrqBqO4L26wMXWVkz5Z7WJtCyZ9huK33Z1JU95vqNbKRDRv0CoAa5z6WPdSBe5c75N5tRf2R";
    static String BASEURI = "http://54.75.227.139:8080/uocapi/api/v1/";
    static String CONFIG_FILE = "config";

    public Constants() {

    }

    @Override
    public String getClient() {
        return CLIENT;
    }

    @Override
    public String getRedirectUri() {
        return REDIRECT_URI;
    }

    @Override
    public String getAccesTokenUri() {
        return ACCES_TOKEN_URI;
    }

    @Override
    public String getAuthorizeUri() {
        return AUTHORIZE_URI;
    }

    @Override
    public String getAPPUri() {
        return APP_URI;
    }

    @Override
    public String getSecret() {
        return SECRET;
    }

    @Override
    public String getBaseUri() {
        return BASEURI;
    }

    @Override
    public String getConfigFile() {
        return CONFIG_FILE;
    }
}