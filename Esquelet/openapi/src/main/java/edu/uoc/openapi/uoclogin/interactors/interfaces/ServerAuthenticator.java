package edu.uoc.openapi.uoclogin.interactors.interfaces;

import android.accounts.Account;

import org.apache.http.auth.AuthenticationException;

/**
 * Created by bgonzalezde_p on 08/07/2014.
 */
public interface ServerAuthenticator {

    public void authenticateOnServer();

    String getToken() throws AuthenticationException;

    public boolean isTokenValid();

    public void refreshToken();

    public void authenticate();

    void getServerToken(String authorizationCode);

    public Account getAccount() throws AuthenticationException;
}
