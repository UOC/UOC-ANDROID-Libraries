package edu.uoc.esquelet.app;

import android.content.Intent;
import android.view.Menu;

import edu.uoc.openapi.LoginActivity;


public class MainActivity  extends LoginActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(edu.uoc.esquelet.app.R.menu.activity_main, menu);
        return true;
    }

    @Override
    public Intent NextActivityIntent() {
        return new Intent (this, CallChooser.class);
    }


}

