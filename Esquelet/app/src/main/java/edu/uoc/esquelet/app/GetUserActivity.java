package edu.uoc.esquelet.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import edu.uoc.openapi.model.AuthObj;
import edu.uoc.openapi.model.User;

public class GetUserActivity extends Activity {
    TextView tvId;
    TextView tvUsername;
    TextView tvName;
    TextView tvNumber;
    TextView tvFull;
    TextView tvPhoto;
    TextView tvLang;
    TextView tvSession;
    TextView tvEmail;
    AuthObj user = AuthObj.getInstance();
    static SharedPreferences settings;
    EsqueletConstants constants = new EsqueletConstants();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_people);

        if(user.validToken()) {
            String[] params = new String[1];
            params[0] = user.getAuthtoken();
            new LoadEventTask().execute(params);
        }
        else new RefreshTokenTask().execute();
    }

    private class RefreshTokenTask extends AsyncTask<String, Void, String> {
        protected void onPostExecute(String result) {
            if (result != null) {
                String[] params = new String[1];
                params[0] = result;
                new LoadEventTask().execute(params);
            } else {
                Toast.makeText(getApplicationContext(),
                        "Hay problemas de conexion", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected String doInBackground(String... param) {
            user.refreshAuthtoken(new EsqueletConstants().getToken(),
                    new EsqueletConstants().getRedirectUri());
            settings = getSharedPreferences(constants.getConfigFile(),
                    MODE_PRIVATE);
            try {
                settings.edit().putString("oauthToken", OapiUtils.encrypt(getApplicationContext(), user.getAuthtoken())).commit();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return user.getAuthtoken();
        }
    }

    private class LoadEventTask extends AsyncTask<String, Void, User> {
        protected void onPostExecute(User result) {
            if (result != null) {
                tvId = (TextView)findViewById(R.id.tvId);
                tvId.setText(result.getId());
                tvUsername = (TextView)findViewById(R.id.tvUsername);
                tvUsername.setText(result.getUsername());
                tvName = (TextView)findViewById(R.id.tvName);
                tvName.setText(result.getName());
                tvNumber = (TextView)findViewById(R.id.tvNumber);
                tvNumber.setText(result.getNumber());
                tvFull = (TextView)findViewById(R.id.tvFull);
                tvFull.setText(result.getFullName());
                tvPhoto = (TextView)findViewById(R.id.tvPhoto);
                tvPhoto.setText(result.getPhotoUrl());
                tvLang = (TextView)findViewById(R.id.tvLang);
                tvLang.setText(result.getLanguage());
                tvSession = (TextView)findViewById(R.id.tvSession);
                tvSession.setText(result.getSessionId());
                tvEmail = (TextView)findViewById(R.id.tvEmail);
                tvEmail.setText(result.getEmail());
            } else {
                Toast.makeText(getApplicationContext(),
                        "Hay problemas de conexion", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected User doInBackground(String... param) {
            try {
                return new User().getUser(param[0], new EsqueletConstants().getBaseUri());
            } catch (Exception e) {
                e.printStackTrace();
                return new User();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}