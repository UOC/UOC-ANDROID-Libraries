package edu.uoc.esquelet.app;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.uoc.openapi.Utils;
import edu.uoc.openapi.model.Message;
import edu.uoc.openapi.model.MessageList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Manuel on 14/03/14.
 */
public class GetMailMessagesActivity extends Activity {
    /*STARTUOCAPIEXAMPLE*/
    Message m;
    String Fid;
    List<Message> listM;
    final ArrayList<String> list = new ArrayList<String>();
    StableArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_onesimplelist);
        final ListView listview = (ListView) findViewById(R.id.listView1);

        adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, final View view,
                                    int pos, long id) {
                /*m = listM.get(pos);
                Intent newintent = new Intent (getApplicationContext(), ViewMessageActivity.class);
                newintent.putExtra("MID", m.getId());
                GetMessagesActivity.this.startActivity(newintent);*/

            }
        });
        if(Utils.validToken()) {
            String[] params = new String[1];
            params[0] = Utils.getToken(getApplicationContext());
            new LoadClassroomTask().execute(params);
        }
        else new RefershTokenTask().execute();
    }

    private class RefershTokenTask extends AsyncTask<String, Void, String> {
        protected void onPostExecute(String result) {
            if (result != null) {
                String[] params = new String[1];
                params[0] = result;
                new LoadClassroomTask().execute(params);
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

    private class LoadClassroomTask extends AsyncTask<String, Void, MessageList> {
        protected void onPostExecute(MessageList result) {
            if (result != null) {
                ArrayList<Message> aux = result.getMessages();
                listM = aux;
                for (int i = 0; i < aux.size(); ++i) {
                    list.add(aux.get(i).getSubject());
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Hay problemas de conexion", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected MessageList doInBackground(String... param) {
            /* UOCAPICALL /api/v1/mail/messages GET*/
            return MessageList.getMailMessages(param[0], "0", "1000");
        }
    }
    /*ENDUOCAPIEXAMPLE*/
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