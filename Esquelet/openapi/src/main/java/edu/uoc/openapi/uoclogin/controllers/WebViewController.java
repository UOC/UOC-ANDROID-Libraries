package edu.uoc.openapi.uoclogin.controllers;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import edu.uoc.openapi.uoclogin.interactors.interfaces.ServerAuthenticator;

public class WebViewController extends WebViewClient {

    public ServerAuthenticator serverAuthenticator;
    String mRedirectUri;
    Context mContext;
    String mLoginUrl;

    public WebViewController(String redirectUri, Context context,
        ServerAuthenticator serverAuthenticator) {

        mContext = context;
        mRedirectUri = redirectUri;
        this.serverAuthenticator = serverAuthenticator;
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);

        if (url.startsWith(mRedirectUri) && view.getVisibility() == View.VISIBLE) {

            String[] parts = url.split("=");
            final String code = parts[1];

            // Refresh login url if can't perform login
            CookieSyncManager.createInstance(mContext);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.setAcceptCookie(true);
            view.loadUrl(mLoginUrl);
            serverAuthenticator.getServerToken(code);
        } else {
            mLoginUrl = view.getUrl();
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT) @Override public void onPageFinished(WebView view,
        String url) {
        view.getSettings().setJavaScriptEnabled(true);
        String user = "";
        String pwd = "";
        view.evaluateJavascript("javascript:document.getElementById('username').value = '"
            + user
            + "';document.getElementById('password').value='"
            + pwd
            + "';", null);
    }
}
