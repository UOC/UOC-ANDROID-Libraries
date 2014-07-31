package edu.uoc.openapi.uoclogin.interactors;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.path.android.jobqueue.JobManager;

import net.smartam.leeloo.common.message.types.GrantType;

import org.apache.http.auth.AuthenticationException;

import de.greenrobot.event.EventBus;
import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;
import edu.uoc.openapi.uoclogin.controllers.UocAccount;
import edu.uoc.openapi.uoclogin.interactors.events.ServerTokenAcquiredEvent;
import edu.uoc.openapi.uoclogin.interactors.interfaces.ServerAuthenticator;
import edu.uoc.openapi.uoclogin.interactors.jobs.GetServerTokenJob;
import edu.uoc.openapi.uoclogin.views.AbstractLoginActivity;

public class UocServerAuthenticator implements ServerAuthenticator {

    public static ConstantsInterface constants;
    public Context context;
    public AccountManager accountManager;
    public JobManager jobManager;
    public Account account;

    private String AUTHENTICATION_EXCPETION_MESSAGE = "You aren't authenticated on server";

    public UocServerAuthenticator(Context context, AccountManager accountManager,
        JobManager jobManager, ConstantsInterface constants) {

        EventBus.getDefault().register(this);

        this.constants = constants;
        this.context = context;
        this.jobManager = jobManager;
        this.accountManager = accountManager;
    }

    @Override public void authenticate() {
        Account[] accounts = accountManager.getAccountsByType(UocAccount.ACCOUNT_TYPE);
        if (accounts != null && accounts.length != 0) {
            account = accounts[0];
            getValidToken();
        } else {
            authenticateOnServer();
        }
    }


   private void getValidToken(){
       try {
           String token = getToken();
           if (token != null && !token.equals("")) {
               if (!isTokenValid()) {
                   refreshToken();
               }
           }
       }catch (AuthenticationException e){
            e.printStackTrace();
       }
    }

    @Override public String getToken() throws AuthenticationException{
        String token =  accountManager.peekAuthToken(account,UocAccount.AUTHTOKEN_TYPE_FULL_ACCESS);
        if(token == null) throw new AuthenticationException(AUTHENTICATION_EXCPETION_MESSAGE);
        return token;
    }

    @Override public boolean isTokenValid() {
        Long expiresIn =
            Long.valueOf(accountManager.getUserData(account, AbstractLoginActivity.ARG_EXPIRES_IN));
        Long issuedTime = Long.valueOf(
            accountManager.getUserData(account, AbstractLoginActivity.ARG_TOKEN_ISSUED_TIME));

        return (issuedTime + expiresIn) > (System.currentTimeMillis()
            / ServerTokenAcquiredEvent.secondsConversion);
    }

    @Override public void refreshToken() {
        if(accountManager.getUserData(account, constants.getAppName() + "_Client") != null) {
            String refreshToken =
                    accountManager.getUserData(account, AbstractLoginActivity.ARG_REFRESH_TOKEN);
            jobManager.addJobInBackground(
                    new GetServerTokenJob(constants, GrantType.REFRESH_TOKEN, refreshToken,
                            accountManager.getUserData(account, constants.getAppName() + "_Client"),
                            accountManager.getUserData(account, constants.getAppName() + "_Secret")));
        } else {
            String refreshToken =
                    accountManager.getUserData(account, AbstractLoginActivity.ARG_REFRESH_TOKEN);
            jobManager.addJobInBackground(
                    new GetServerTokenJob(constants, GrantType.REFRESH_TOKEN, refreshToken));
        }
    }

    @Override public void authenticateOnServer() {

        accountManager.getAuthTokenByFeatures(UocAccount.ACCOUNT_TYPE,
            UocAccount.AUTHTOKEN_TYPE_FULL_ACCESS, null, (Activity) context, null, null, null,
            null);
    }

     @Override public void getServerToken(String authorizationCode) {

        jobManager.addJobInBackground(
            new GetServerTokenJob(authorizationCode, constants, GrantType.AUTHORIZATION_CODE));
    }

    @Override public Account getAccount() throws AuthenticationException {
        if (account == null) {
            throw new AuthenticationException(AUTHENTICATION_EXCPETION_MESSAGE);
        }
        return account;
    }

    public void onEventMainThread(ServerTokenAcquiredEvent event) throws Exception {
        if (account != null) {
            accountManager.setUserData(account, AbstractLoginActivity.ARG_EXPIRES_IN,
                event.getExpiresIn());
            accountManager.setUserData(account, AbstractLoginActivity.ARG_REFRESH_TOKEN,
                event.getRefreshToken());
            accountManager.setUserData(account, AbstractLoginActivity.ARG_TOKEN_ISSUED_TIME,
                event.getTokenIsuedTime());
        }
    }
}