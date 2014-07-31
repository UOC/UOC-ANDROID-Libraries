package edu.uoc.openapi.uoclogin.controllers;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import edu.uoc.openapi.uoclogin.views.AbstractLoginActivity;
import javax.inject.Inject;

public class UocAuthenticator extends AbstractAccountAuthenticator {

    private final Context mContext;

    @Inject
    public UocAuthenticator(Context context) {

        super(context);
        mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType,
        String authTokenType, String[] requiredFeatures, Bundle options)
        throws NetworkErrorException {

        Log.v("UocLogin", "AddAccount");
        Intent intent = new Intent(mContext, AbstractLoginActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra(AbstractLoginActivity.ARG_ACCOUNT_TYPE, accountType);
        intent.putExtra(AbstractLoginActivity.ARG_AUTH_TYPE, authTokenType);
        intent.putExtra(AbstractLoginActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);

        return accountManagerIntentBundle(intent);
    }

    private Bundle accountManagerIntentBundle(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account,
        Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account,
        String authTokenType, Bundle options) throws NetworkErrorException {

        Log.v("UocLogin", "LogIn new");

        Intent intent = new Intent(mContext, AbstractLoginActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra(AbstractLoginActivity.ARG_ACCOUNT_TYPE, account.type);
        intent.putExtra(AbstractLoginActivity.ARG_AUTH_TYPE, authTokenType);

        return accountManagerIntentBundle(intent);
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account,
        String authTokenType, Bundle options) throws NetworkErrorException {

        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account,
        String[] features) throws NetworkErrorException {
        return null;
    }
}
