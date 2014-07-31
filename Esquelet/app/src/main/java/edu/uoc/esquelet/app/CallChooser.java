package edu.uoc.esquelet.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.path.android.jobqueue.JobManager;

import org.apache.http.auth.AuthenticationException;

import edu.uoc.openapi.model.AuthObj;
import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;
import edu.uoc.openapi.uoclogin.interactors.UocServerAuthenticator;
import edu.uoc.openapi.uoclogin.interactors.interfaces.ServerAuthenticator;


public class CallChooser extends Activity implements View.OnClickListener {
    AuthObj user = AuthObj.getInstance();
    ServerAuthenticator serverAuthenticator;
    ConstantsInterface constants = new EsqueletConstants();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serverAuthenticator =
                new UocServerAuthenticator(this, AccountManager.get(this), new JobManager(this),
                        new EsqueletConstants());
        serverAuthenticator.authenticate();
        setContentView(R.layout.activity_test);
        Button btnViewProfile = (Button)findViewById(R.id.btnViewProfile);
        btnViewProfile.setOnClickListener(this);
        Button btnViewUser = (Button)findViewById(R.id.btnViewUser);
        btnViewUser.setOnClickListener(this);
        Button btnEvents = (Button)findViewById(R.id.btnEvents);
        btnEvents.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void onClick(View v) {

        Account acc = null;
        String token = null;
        serverAuthenticator.authenticate();
        try {
            acc = serverAuthenticator.getAccount();
            token = serverAuthenticator.getToken();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        if(acc == null) Log.i("NEWLOG", "account is null");
        else Log.i("NEWLOG", "account is NOT null");
        AuthObj auth = AuthObj.getInstance();
        auth.setAuthclient(AccountManager.get(this).getUserData(acc, constants.getAppName() + "_Client"));
        auth.setAuthrefresh(AccountManager.get(this).getUserData(acc, "REFRESH_TOKEN"));
        auth.setAuthsecret(AccountManager.get(this).getUserData(acc, constants.getAppName() + "_Secret"));
        auth.setAuthtoken(token);
        auth.setTokentime(AccountManager.get(this).getUserData(acc, "EXPIRES_IN"));
        switch (v.getId()) {
            case R.id.btnViewUser:
                startActivity(new Intent().setClass(this, GetUserActivity.class));
                break;
            case R.id.btnViewProfile:
                startActivity(new Intent().setClass(this, GetMailMessagesActivity.class));
                break;
            case R.id.btnEvents:
                startActivity(new Intent().setClass(this, GetEventsActivity.class));
                break;
            case R.id.btnResources:
                startActivity(new Intent().setClass(this, GetClassroomListActivity.class));
                break;
            default:
                break;
        }
    }

}