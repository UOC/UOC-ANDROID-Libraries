package com.uoc.esquelet.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.uoc.openapilibrary.Utils;
import com.uoc.openapilibrary.model.Event;
import com.uoc.openapilibrary.model.EventList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Manuel on 14/03/14.
 */
public class GetEventsActivity extends Activity implements View.OnClickListener {
    Event e;
    List<Event> listE;
    final ArrayList<String> list = new ArrayList<String>();
    StableArrayAdapter adapter;
    Button btnNewEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_events);
        final ListView listview = (ListView) findViewById(R.id.listView1);

        btnNewEvent = (Button)findViewById(R.id.btnNewEvent);
        btnNewEvent.setOnClickListener(this);
        adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, final View view,
                                    int pos, long id) {
                /*e = listE.get(pos);
                Intent newintent = new Intent (getApplicationContext(), ViewEventActivity.class);
                newintent.putExtra("ID",e.getId());
                GetEventsActivity.this.startActivity(newintent);*/

            }
        });
    }



    private class LoadEventTask extends AsyncTask<String, Void, EventList> {
        protected void onPostExecute(EventList result) {
            if (result != null) {
                ArrayList<Event> aux = result.getEvents();
                listE = aux;
                for (int i = 0; i < aux.size(); ++i) {
                    list.add(aux.get(i).getSummary());
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Hay problemas de conexion", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected EventList doInBackground(String... param) {
            return EventList.getCalendarEvents(param[0]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewEvent:
                startActivity(new Intent().setClass(this, PostCalendarEventActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        list.clear();
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
}