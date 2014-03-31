package com.uoc.esquelet.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


/**
 * Created by Manuel on 14/03/14.
 */
public class CallChooser extends Activity implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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