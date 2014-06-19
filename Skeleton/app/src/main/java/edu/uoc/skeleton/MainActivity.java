package edu.uoc.skeleton;

import android.app.Activity;
import android.app.ProgressDialog;
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

import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.OAuthJSONAccessTokenResponse;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import net.smartam.leeloo.common.message.types.GrantType;

import org.json.JSONObject;

import edu.uoc.openapi.LoginInterface;
import edu.uoc.openapi.RESTMethod;
import edu.uoc.openapi.model.AuthObj;

public class MainActivity  extends Activity implements LoginInterface {
    String oauthToken;
    static SharedPreferences settings;
    ProgressDialog pd;
    WebView webViewLogin;
    AuthObj user = AuthObj.getInstance();
    String code;
    Button Login;
    Button Delete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(Constants.CONFIG_FILE,
                MODE_PRIVATE);

        setContentView(R.layout.activity_login);

        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("loading");

        Login = (Button) findViewById(R.id.btnLogin);
        Delete = (Button) findViewById(R.id.btnDelete);

        webViewLogin = (WebView) findViewById(R.id.webViewLogin);
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
                        if (!token.equals("")) {
                            token = OapiUtils.decrypt(getApplicationContext(), token);
                            user.setAuthtoken(token);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (token.equals("")) {
                        String[] parts = url.split("=");
                        code = parts[1];
                        new getTokenTask().execute(code);
                    }
                    webViewLogin.setVisibility(View.INVISIBLE); // Amaguem el WebView quan ja tenim el code
                    if (!token.equals("")) {
                        Intent i = NextActivityIntent();
                        try {
                            user.setAuthsecret(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthSecret", "")));
                            user.setAuthclient(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthClient", "")));
                            user.setAuthrefresh(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthRefresh", "")));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        user.setTokentime(settings.getString("oauthToken", ""));
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
                    launchloginwindow();
                }
                else {
                    Intent i = NextActivityIntent();
                    try {
                        user.setAuthtoken(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthToken", "")));
                        user.setAuthsecret(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthSecret", "")));
                        user.setAuthclient(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthClient", "")));
                        user.setAuthrefresh(OapiUtils.decrypt(getApplicationContext(), settings.getString("oauthRefresh", "")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    user.setTokentime(settings.getString("tokenTime", ""));
                    startActivity(i);
                    finish();
                }
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Aqui es podria fer un clear cookies y dades guardades
                android.webkit.CookieManager.getInstance().removeAllCookie();
                settings.edit().clear().commit();
                user = AuthObj.reset();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(edu.uoc.skeleton.R.menu.activity_main, menu);
        return true;
    }

    public Intent NextActivityIntent() {
        return new Intent (this, CallChooser.class);
    }


    @Override
    public void launchloginwindow() {
        // Con los datos de entrada, nos lleva a un navegador web para hacer
        // login en el servicio

        String client = settings.getString("oauthClient","");
        if (!client.equals("")) {
            user.setAuthclient(client);
            try {
                oauthcall(OapiUtils.decrypt(getApplicationContext(), client));
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
        webViewLogin.loadUrl(request.getLocationUri() + "&response_type=code&device="+ Build.MODEL+"_"+ Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        webViewLogin.setVisibility(View.VISIBLE);
    }

    public class getTokenTask extends AsyncTask<String, Void, String> {
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
                code = result;
                new getClientSecret().execute(oauthToken);

            }
        }

        @Override
        protected String doInBackground(String... param) {
            askforToken();
            return user.getAuthtoken();
        }
    }

    @Override
    public void askforToken() {
        OAuthClientRequest request = null;
        try {
            request = OAuthClientRequest
                    .tokenLocation(Constants.ACCES_TOKEN_URI)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(Constants.CLIENT).setClientSecret(Constants.SECRET)
                    .setRedirectURI(Constants.REDIRECT_URI).setCode(code)
                    .buildBodyMessage();
        } catch (OAuthSystemException e) {
            e.printStackTrace();
        }

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse response;
            response = oAuthClient.accessToken(request);
            oauthToken = response.getAccessToken();
            if (oauthToken != null) {
                user.setAuthtoken(oauthToken);
                user.setTokentime(response.getExpiresIn());
                user.setAuthrefresh(response.getRefreshToken());
                settings.edit().putString("tokenTime", user.getTokentime()).commit();
                try {
                    settings.edit().putString("oauthRefresh", OapiUtils.encrypt(getApplicationContext(), user.getAuthrefresh())).commit();
                    settings.edit().putString("oauthToken", OapiUtils.encrypt(getApplicationContext(), oauthToken)).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (OAuthSystemException e1) {
            e1.printStackTrace();
        } catch (OAuthProblemException e1) {
            e1.printStackTrace();
        }
    }

    public class getClientSecret extends AsyncTask<String, Void, String> { // He hagut que posar aquesta classe com a public.
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
                Intent i = NextActivityIntent();
                //pd.hide();
                startActivity(i);
                finish(); // Cerramos pantalla de login.

            }
        }

        @Override
        protected String doInBackground(String... code) {
            askforClientSecret();
            return user.getAuthclient();
        }
    }

    @Override
    public void askforClientSecret() {
        String result;
        try {
            result = RESTMethod.Get(Constants.APP_URI, code);
            JSONObject json = null;
            json = new JSONObject(result);
            user.setAuthclient(json.getString("client"));
            user.setAuthsecret(json.getString("secret"));
            user.setAuthname(json.getString("name"));
            settings.edit().putString("oauthName", OapiUtils.encrypt(getApplicationContext(), user.getAuthname())).commit();
            settings.edit().putString("oauthClient", OapiUtils.encrypt(getApplicationContext(), user.getAuthclient())).commit();
            settings.edit().putString("oauthSecret", OapiUtils.encrypt(getApplicationContext(), user.getAuthsecret())).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

