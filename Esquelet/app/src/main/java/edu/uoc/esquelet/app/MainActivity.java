package edu.uoc.esquelet.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.path.android.jobqueue.JobManager;

import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.OAuthJSONAccessTokenResponse;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import net.smartam.leeloo.common.message.types.GrantType;

import org.apache.http.auth.AuthenticationException;
import org.json.JSONObject;

import edu.uoc.openapi.LoginInterface;
import edu.uoc.openapi.RESTMethod;
import edu.uoc.openapi.model.AuthObj;
import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;
import edu.uoc.openapi.uoclogin.interactors.UocServerAuthenticator;
import edu.uoc.openapi.uoclogin.interactors.interfaces.ServerAuthenticator;


public class MainActivity  extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = NextActivityIntent();
        startActivity(i);
        finish();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(edu.uoc.esquelet.app.R.menu.activity_main, menu);
        return true;
    }

    public Intent NextActivityIntent() {
        return new Intent (this, CallChooser.class);
    }
}

