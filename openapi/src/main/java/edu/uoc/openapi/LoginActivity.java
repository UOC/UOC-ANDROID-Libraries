package edu.uoc.openapi;

import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.OAuthJSONAccessTokenResponse;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import net.smartam.leeloo.common.message.types.GrantType;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import org.json.JSONException;
import org.json.JSONObject;


public abstract class LoginActivity extends Activity {
    private String oauthToken;
    public static SharedPreferences settings;
    private ProgressDialog pd;
    public WebView webViewLogin;
    Button Login;
    Button Delete;

    public abstract Intent NextActivityIntent();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(Constants.CONFIG_FILE,
                MODE_PRIVATE);
        setContentView(edu.uoc.openapi.R.layout.activity_login);

        pd = new ProgressDialog(LoginActivity.this);
        pd.setMessage("loading");

        Login = (Button) findViewById(edu.uoc.openapi.R.id.btnLogin);
        Delete = (Button) findViewById(edu.uoc.openapi.R.id.btnDelete);

        webViewLogin = (WebView) findViewById(edu.uoc.openapi.R.id.webViewLogin);
        webViewLogin.getSettings().setJavaScriptEnabled(true);
        webViewLogin.setVisibility(View.INVISIBLE);
        webViewLogin.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                /*
                La segona condicio del if esta perque la pagina uoc://oauthresponse s'inicia dues
                vegades. Pero nomes volem registrar la primera ja que sino intenta obtenir el
                token dues vegades.
                */
                pd.dismiss();
                if (url.startsWith(Constants.REDIRECT_URI) && webViewLogin.getVisibility() == View.VISIBLE) {
                    String token = null;
                    try {
                        token = settings.getString("oauthToken", "");
                        if (!token.equals("")) token = Utils.decrypt(getApplicationContext(), token);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (token.equals("")) {
                        String[] parts = url.split("=");
                        String code = parts[1];
                        new getTokenTask().execute(code);
                    }
                    webViewLogin.setVisibility(View.INVISIBLE); // Amaguem el WebView quan ja tenim el code
                    if (!token.equals("")) {
                        Intent i = NextActivityIntent();
                        startActivity(i);
                        finish();
                    }
                }

            }

            /*public void onPageFinished(WebView view, String url) {
                pd.show();
            }*/

        });

        Login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // No es necessari mostrar el WebView fins que no es clica el boto de login
                if (settings.getString("oauthRefresh","").equals("") || settings.getString("oauthClient","").equals("") || settings.getString("oauthSecret","").equals("")) {
                    pd.show();
                    IniciarLogin();
                }
                else {
                    Intent i = NextActivityIntent();
                    startActivity(i);
                    finish();
                }
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Aqui es podria fer un clear cookies y dades guardades
                android.webkit.CookieManager.getInstance().removeAllCookie();  // <- Esborra les cookies
                settings.edit().clear().commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }

    protected static String getToken(Context context) {
        String token = settings.getString("oauthToken", "");
        if (token.equals("")) return "";
        else try {
            return Utils.decrypt(context, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    protected static Boolean validToken() {
        if (settings.getString("tokenTime","").equals("")){
            return false;
        }
        return Long.parseLong(settings.getString("tokenTime","")) > ((System.currentTimeMillis()/1000)+60); //if the token has less than 60 seconds of life
    }

    public void IniciarLogin() {
        // Con los datos de entrada, nos lleva a un navegador web para hacer
        // login en el servicio

        String client = settings.getString("oauthClient","");
        if (!client.equals("")) {
            try {
                oauthcall(Utils.decrypt(getApplicationContext(), client));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            oauthcall(Constants.CLIENT);
        }

    }

    private void oauthcall(String client){
        OAuthClientRequest request = null;
        try {
            // preparamos la petición con nuestro code
            request = OAuthClientRequest
                    .authorizationLocation(Constants.AUTHORIZE_URI)
                            // No se pasa como parametro, porque deberia ser siempre la
                            // misma
                    .setClientId(client)
                    .setRedirectURI(Constants.REDIRECT_URI).buildQueryMessage();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }
        // Lanzamos las pagina de login
        webViewLogin.loadUrl(request.getLocationUri() + "&response_type=code&device="+Build.MODEL+"_"+ Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        webViewLogin.setVisibility(View.VISIBLE);
    }

    public class getTokenTask extends AsyncTask<String, Void, String> {  // He tingut que posar aquesta classe com a public.
        /*
         * Get Token && start next activity
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(String result) {
            if (result == null) {
                Toast.makeText(getApplicationContext(), "Error al hacer Login",
                        Toast.LENGTH_LONG).show();
            } else {
                // If result is not null, then neither is oauthToken
                new getClientSecret().execute(oauthToken);

            }
        }

        @Override
        protected String doInBackground(String... code) {
            return retriveAccesToken(Constants.CLIENT, Constants.SECRET,
                    Constants.REDIRECT_URI, code[0]);

        }
    }

    protected static String refreshToken(Context context) {
        OAuthClientRequest request = null;
        if (settings.getString("oauthRefresh","").equals("")) return null;

        String client;
        String secret;
        String refresh;

        client = settings.getString("oauthClient","");
        secret = settings.getString("oauthSecret","");
        refresh = settings.getString("oauthRefresh","");
        if (!client.equals("")) {
            try {
                client = Utils.decrypt(context, client);
                secret = Utils.decrypt(context, secret);
                refresh = Utils.decrypt(context, refresh);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            request = OAuthClientRequest
                    .tokenLocation(Constants.ACCES_TOKEN_URI)
                    .setGrantType(GrantType.REFRESH_TOKEN)
                    .setClientId(client).setClientSecret(secret)
                    .setRedirectURI(Constants.REDIRECT_URI).setRefreshToken(refresh)
                    .buildBodyMessage();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            return null;
        }

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse response;
            response = oAuthClient.accessToken(request);
            if (response.getAccessToken() != null) {
                settings.edit().putString("tokenTime", correctTime(response.getExpiresIn())).commit();
                try {
                    settings.edit().putString("oauthRefresh", Utils.encrypt(context, response.getRefreshToken())).commit();
                    settings.edit().putString("oauthToken", Utils.encrypt(context, response.getAccessToken())).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return response.getAccessToken();

        } catch (OAuthSystemException e1) {
            e1.printStackTrace();
            return null;
        } catch (OAuthProblemException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public class getClientSecret extends AsyncTask<String, Void, String> {  // He hagut que posar aquesta classe com a public.
        /*
         * Get Client & Secret && start next activity
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(String result) {
            if (result == null) {
                Toast.makeText(getApplicationContext(), "Error al hacer Login",
                        Toast.LENGTH_LONG).show();
            } else {
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    try {
                        settings.edit().putString("oauthName", Utils.encrypt(getApplicationContext(), json.getString("name"))).commit();
                        settings.edit().putString("oauthClient", Utils.encrypt(getApplicationContext(), json.getString("client"))).commit();
                        settings.edit().putString("oauthSecret", Utils.encrypt(getApplicationContext(), json.getString("secret"))).commit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent i = NextActivityIntent();
                //pd.hide();
                startActivity(i);
                finish(); // Cerramos pantalla de login.

            }
        }

        @Override
        protected String doInBackground(String... code) {
            return RESTMethod.Get(Constants.APP_URI, code[0]);
        }
    }

    private String retriveAccesToken(String client, String secret,
                                     String redirectUri, String code) {
        OAuthClientRequest request = null;
        try {
            request = OAuthClientRequest
                    .tokenLocation(Constants.ACCES_TOKEN_URI)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(client).setClientSecret(secret)
                    .setRedirectURI(redirectUri).setCode(code)
                    .buildBodyMessage();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
            return null;
        }

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse response;
            response = oAuthClient.accessToken(request);
            oauthToken = response.getAccessToken();
            if (oauthToken != null) {
                settings.edit().putString("tokenTime", correctTime(response.getExpiresIn())).commit();
                try {
                    settings.edit().putString("oauthRefresh", Utils.encrypt(getApplicationContext(), response.getRefreshToken())).commit();
                    settings.edit().putString("oauthToken", Utils.encrypt(getApplicationContext(), oauthToken)).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return oauthToken;

        } catch (OAuthSystemException e1) {
            e1.printStackTrace();
            return null;
        } catch (OAuthProblemException e1) {
            e1.printStackTrace();
            return null;
        }
    }

    protected static String correctTime(String seconds){
        if(seconds != null) {
            Long time = System.currentTimeMillis()/1000;
            time = time + Long.parseLong(seconds);
            return time.toString();
        }else return "never";
    }

}

