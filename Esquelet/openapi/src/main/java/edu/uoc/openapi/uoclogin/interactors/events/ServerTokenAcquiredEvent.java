package edu.uoc.openapi.uoclogin.interactors.events;

/**
 * Created by bgonzalezde_p on 07/07/2014.
 */
public class ServerTokenAcquiredEvent {

    public static final Long secondsConversion = 1000L;
    private String token;
    private String refreshToken;
    private String tokenIsuedTime;
    private String expiresIn;
    private String client;
    private String secret;

    public ServerTokenAcquiredEvent(String token, String expiresIn, String refreshToken, String client, String secret) {

        tokenIsuedTime = String.valueOf(System.currentTimeMillis() / secondsConversion);
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.client = client;
        this.secret = secret;
    }

    public ServerTokenAcquiredEvent(String token, String expiresIn, String refreshToken) {

        tokenIsuedTime = String.valueOf(System.currentTimeMillis() / secondsConversion);
        this.token = token;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }

    public String getToken() {

        return token;
    }

    public String getExpiresIn() {

        return expiresIn;
    }

    public String getRefreshToken() {

        return refreshToken;
    }

    public String getClient() {
        return client;
    }

    public String getSecret() {
        return secret;
    }

    public String getTokenIsuedTime() {
        return tokenIsuedTime;
    }
}
