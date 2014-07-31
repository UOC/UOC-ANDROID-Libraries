package edu.uoc.openapi.uoclogin.views;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;

import dagger.ObjectGraph;
import de.greenrobot.event.EventBus;
import edu.uoc.openapi.R;
import edu.uoc.openapi.model.AuthObj;
import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;
import edu.uoc.openapi.uoclogin.controllers.UocAccount;
import edu.uoc.openapi.uoclogin.controllers.WebViewController;
import edu.uoc.openapi.uoclogin.controllers.modules.ServerAuthenticateModule;
import edu.uoc.openapi.uoclogin.interactors.events.ServerTokenAcquiredEvent;
import edu.uoc.openapi.uoclogin.interactors.interfaces.ServerAuthenticator;
import javax.inject.Inject;

public class AbstractLoginActivity extends AccountAuthenticatorActivity {

    public final static String ARG_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String ARG_AUTH_TYPE = "AUTH_TYPE";
    public final static String ARG_IS_ADDING_NEW_ACCOUNT = "IS_ADDING_ACCOUNT";

    public final static String ARG_REFRESH_TOKEN = "REFRESH_TOKEN";
    public final static String ARG_EXPIRES_IN = "EXPIRES_IN";
    public final static String ARG_TOKEN_ISSUED_TIME = "TOKEN_ISSUED_TIME";

    @Inject
    public ServerAuthenticator serverAuthenticator;
    @Inject
    public AccountManager mAccountManager;
    WebView loginWebView;
    @Inject
    ConstantsInterface mConstants;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        initialize();

        setContentView(R.layout.activity_login);

        setUpCookies();

        loginWebView = (WebView) findViewById(R.id.loginWebView1);

        loginWebView.getSettings().setSaveFormData(false);

        loginWebView.setWebViewClient(
            new WebViewController(mConstants.getRedirectUri(), this, serverAuthenticator));

        String url = buildUrl();

        loginWebView.loadUrl(url);

    }

    private String buildUrl() {
        return mConstants.getAuthorize()
            + mConstants.getParamDevice()
            + (Build.MODEL).replace(" ", "%20")
            + mConstants.getParamClient()
            + mConstants.getClient()
            + mConstants.getParamRedirectUri()
            + mConstants.getRedirectUri()
            + mConstants.getParamResponseType()
            + mConstants.getResponseType();
    }

    private void setUpCookies() {
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        cookieManager.setAcceptCookie(true);
    }

    private void initialize() {
        EventBus.getDefault().register(this);
        ObjectGraph objectGraph = ObjectGraph.create(new ServerAuthenticateModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    public void onEventMainThread(ServerTokenAcquiredEvent event) {

        final Intent intent = new Intent();

        String accountName = UocAccount.ACCOUNT_NAME;
        String accountType = UocAccount.ACCOUNT_TYPE;
        String accountPassword = "PARAM_USER_PASS";

        final Account account = new Account(accountName, accountType);
        String authToken = null;

        if (getIntent().getBooleanExtra(ARG_IS_ADDING_NEW_ACCOUNT, false)) {

            authToken = event.getToken();
            String mAuthTokenType = UocAccount.AUTHTOKEN_TYPE_FULL_ACCESS;

            mAccountManager.addAccountExplicitly(account, accountPassword, null);
            mAccountManager.setAuthToken(account, mAuthTokenType, authToken);

            saveUserData(account, event);
        }

        setAccountAuthenticatorResult(intent.getExtras());
        int result = authToken == null ? RESULT_CANCELED : RESULT_OK;
        setResult(result, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (loginWebView.canGoBack()) loginWebView.goBack();
    }

    @Override protected void onDestroy() {

        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void saveUserData(Account account, ServerTokenAcquiredEvent event) {

        mAccountManager.setAuthToken(account, UocAccount.ACCOUNT_TYPE, event.getToken());
        mAccountManager.setUserData(account, ARG_EXPIRES_IN, event.getExpiresIn());
        mAccountManager.setUserData(account, ARG_REFRESH_TOKEN, event.getRefreshToken());
        mAccountManager.setUserData(account, ARG_TOKEN_ISSUED_TIME, event.getTokenIsuedTime());
        if (event.getClient() != null) {
            mAccountManager.setUserData(account, mConstants.getAppName() + "_Client", event.getClient());
            mAccountManager.setUserData(account, mConstants.getAppName() + "_Secret", event.getSecret());
        }

    }
}