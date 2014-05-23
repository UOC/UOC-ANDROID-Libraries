package edu.uoc.esquelet.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.uoc.openapi.Utils;
import edu.uoc.openapi.model.MobileResource;
import edu.uoc.openapi.model.MobileResourceList;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GetResources extends Activity {
    MobileResource app;
    List<MobileResource> listA;
    final ArrayList<String> list = new ArrayList<String>();
    StableArrayAdapter adapter;
    String CID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_onesimplelist);
        final ListView listview = (ListView) findViewById(R.id.listView1);
        adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);
        Intent myIntent= getIntent();
        String[] params = new String[2];
        CID = myIntent.getStringExtra("CID");
        if(Utils.validToken()) {
            params[0] = Utils.getToken(getApplicationContext());
            params[1] = CID;
            new LoadMailResourceTask().execute(params);
        }
        else new RefershTokenTask().execute();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, final View view,
                                    int pos, long id) {
                app = listA.get(pos);
                Uri uri = Uri.parse(app.getUrlAndroid());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                try
                {
                    GetResources.this.startActivity(intent);
                }
                catch (ActivityNotFoundException e)
                {
                    uri = Uri.parse(app.getUrlMarketAndroid());
                    intent = new Intent(Intent.ACTION_VIEW, uri);
                    try
                    {
                        GetResources.this.startActivity(intent);
                    }
                    catch (ActivityNotFoundException e2)
                    {
                        Toast.makeText(getApplicationContext(),"No idea",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private class RefershTokenTask extends AsyncTask<String, Void, String> {
        protected void onPostExecute(String result) {
            if (result != null) {
                String[] params = new String[1];
                params[0] = result;
                params[1] = CID;
                new LoadMailResourceTask().execute(params);
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

    private class LoadMailResourceTask extends AsyncTask<String, Void, MobileResourceList> {
        protected void onPostExecute(MobileResourceList result) {
            if (result != null) {

                ArrayList<MobileResource> aux = result.getApps();
                listA = aux;
                for (int i = 0; i < aux.size(); ++i) {
                    list.add(aux.get(i).getNameAndroid());
                }
                adapter.notifyDataSetChanged();

            } else {
                Toast.makeText(getApplicationContext(),
                        "Hay problemas de conexion", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected MobileResourceList doInBackground(String... param) {
            return MobileResourceList.getClassroomsIdResourcesApps(param[0], param[1]);
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

}
