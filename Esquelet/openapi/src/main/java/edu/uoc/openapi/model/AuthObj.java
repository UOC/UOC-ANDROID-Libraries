package edu.uoc.openapi.model;


import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.OAuthJSONAccessTokenResponse;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import net.smartam.leeloo.common.message.types.GrantType;


public class AuthObj {
    private String authrefresh;
    private String authtoken;
    private String authclient;
    private String authsecret;
    private String tokentime;
    private String authname;

    private static AuthObj instance = null;

    protected AuthObj() {
        //Default constructor
    }

    public static AuthObj getInstance() {
        if(instance == null) {
            instance = new AuthObj();
        }
        return instance;
    }

    public static AuthObj reset() {
        instance = new AuthObj();
        return instance;
    }

    public String getAuthrefresh() {
        return authrefresh;
    }

    public void setAuthrefresh(String authrefresh) {
        this.authrefresh = authrefresh;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getAuthclient() {
        return authclient;
    }

    public void setAuthclient(String authclient) {
        this.authclient = authclient;
    }

    public String getTokentime() {
        return tokentime;
    }

    public void setTokentime(String tokentime) {
        this.tokentime = correctTime(tokentime);
    }

    public String getAuthsecret() {
        return authsecret;
    }

    public void setAuthsecret(String authsecret) {
        this.authsecret = authsecret;
    }

    public String getAuthname() {
        return authname;
    }

    public void setAuthname(String authname) {
        this.authname = authname;
    }

    public void refreshAuthtoken(String accesTokenUri, String redirectUri) {
        OAuthClientRequest request = null;

        try {
            request = OAuthClientRequest
                    .tokenLocation(accesTokenUri)
                    .setGrantType(GrantType.REFRESH_TOKEN)
                    .setClientId(authclient).setClientSecret(authsecret)
                    .setRedirectURI(redirectUri).setRefreshToken(authrefresh)
                    .buildBodyMessage();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse response;
            response = oAuthClient.accessToken(request);
            if (response.getAccessToken() != null) {
                tokentime = correctTime(response.getExpiresIn());
                authrefresh = response.getRefreshToken();
                authtoken = response.getAccessToken();
            }

        } catch (OAuthProblemException e) {
            e.printStackTrace();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }

    }

    protected static String correctTime(String seconds){
        if(seconds != null) {
            Long time = System.currentTimeMillis()/1000;
            time = time + Long.parseLong(seconds);
            return time.toString();
        }else return "never";
    }

    public Boolean validToken() {
        return tokentime != null && Long.parseLong(tokentime) > ((System.currentTimeMillis() / 1000)
                + 60);
    }
}
