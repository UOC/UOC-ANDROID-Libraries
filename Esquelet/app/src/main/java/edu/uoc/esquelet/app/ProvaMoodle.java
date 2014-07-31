package edu.uoc.esquelet.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class ProvaMoodle extends Activity {

    private String secret = "Z1mxOMo54tgHnmzpMgoz5nAHjpClHUS0OZX8rsi98Jb6uxpPzbZvFwmW8SSPTaPWhGlWNLPqyCyrW0sWpiWHeNdNmT2M8yeVphiURRBBrE1a1rr047naYgRPV282sv7c&";
    private Uri url;
    private String baseurl = "GET&http%3A%2F%2Fa&";
    private TextView textPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prova_moodle);
        Intent i = getIntent();
        url = i.getData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private static String hmacSha1(String value, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException,
            InvalidKeyException {
        String type = "HmacSHA1";
        SecretKeySpec secret = new SecretKeySpec(key.getBytes(), type);
        Mac mac = Mac.getInstance(type);
        mac.init(secret);
        byte[] bytes = mac.doFinal(value.getBytes());
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    @Override
    public void onStart() {
        super.onStart();
        textPM = (TextView)findViewById(R.id.textPM);
        String converted = new String();
        Set<String> dictionary = url.getQueryParameterNames();
        String signature = url.getQueryParameter("signature");
        String newURL = baseurl+Uri.encode(url.toString().split("einamoodle://\\?")[1].split("&signature")[0],"._-");
        Boolean correct_time = (Long.parseLong(url.getQueryParameter("timestamp"))+300) > ((System.currentTimeMillis() / 1000));
        try {
            converted = hmacSha1(newURL, secret);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        if(converted == null){
            textPM.setText("Is null");
        }
        else if(converted.equals(signature) && correct_time) {
            textPM.setText("Match");
        }
        else {
            textPM.setText("Fail");
        }
    }
}
