package edu.uoc.esquelet.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import edu.uoc.openapi.Utils;
import edu.uoc.openapi.model.User;

/**
 * Created by Manuel on 14/03/14.
 */
public class GetUserActivity extends Activity {
    /*STARTUOCAPIEXAMPLE*/
    TextView tvId;
    TextView tvUsername;
    TextView tvName;
    TextView tvNumber;
    TextView tvFull;
    TextView tvPhoto;
    TextView tvLang;
    TextView tvSession;
    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_people);

        if(Utils.validToken()) {
            String[] params = new String[1];
            params[0] = Utils.getToken(getApplicationContext());
            new LoadEventTask().execute(params);
        }
        else new RefershTokenTask().execute();
    }

    private class RefershTokenTask extends AsyncTask<String, Void, String> {
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
            return Utils.refreshToken(getApplicationContext());
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
            /* UOCAPICALL /api/v1/user GET*/
            return User.getUser(param[0]);
        }
    }
    /*ENDUOCAPIEXAMPLE*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}