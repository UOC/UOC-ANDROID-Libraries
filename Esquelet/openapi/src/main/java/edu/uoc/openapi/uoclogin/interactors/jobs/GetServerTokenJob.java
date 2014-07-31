package edu.uoc.openapi.uoclogin.interactors.jobs;

import android.util.Log;

import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.OAuthJSONAccessTokenResponse;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import net.smartam.leeloo.common.message.types.GrantType;

import org.json.JSONObject;

import de.greenrobot.event.EventBus;
import edu.uoc.openapi.RESTMethod;
import edu.uoc.openapi.uoclogin.controllers.ConstantsInterface;
import edu.uoc.openapi.uoclogin.interactors.events.ServerTokenAcquiredEvent;

/**
 * Created by bgonzalezde_p on 07/07/2014.
 */

public class GetServerTokenJob extends Job {

    public static final int PRIORITY = 1;

    public ConstantsInterface mConstants;
    private String code;
    private GrantType grantType;
    private String refreshToken;
    private String newclient;
    private String newsecret;

    public GetServerTokenJob(String code, ConstantsInterface constants, GrantType grantType) {

        super(new Params(PRIORITY).requireNetwork().persist());
        this.grantType = grantType;
        mConstants = constants;
        this.code = code;
    }

    public GetServerTokenJob(ConstantsInterface constants, GrantType grantType,
        String refreshToken, String client, String secret) {

        super(new Params(PRIORITY).requireNetwork().persist());
        this.grantType = grantType;
        mConstants = constants;
        this.refreshToken = refreshToken;
        this.newclient = client;
        this.newsecret = secret;
    }

    public GetServerTokenJob(ConstantsInterface constants, GrantType grantType,
                             String refreshToken) {

        super(new Params(PRIORITY).requireNetwork().persist());
        this.grantType = grantType;
        mConstants = constants;
        this.refreshToken = refreshToken;
    }

    @Override public void onAdded() {

    }

    @Override public void onRun() throws Throwable {
        String client = (newclient != null) ? newclient : mConstants.getClient();
        String secret = (newsecret != null) ? newsecret : mConstants.getSecret();
        OAuthClientRequest request = null;
        try {
            request = OAuthClientRequest.tokenLocation(mConstants.getToken())
                .setGrantType(grantType)
                .setClientId(client)
                .setClientSecret(secret)
                .setRedirectURI(mConstants.getRedirectUri())
                .setRefreshToken(refreshToken)
                .setCode(code)
                .buildBodyMessage();

        } catch (OAuthSystemException e) {

            e.printStackTrace();
        }

        String oauthToken = null;
        try {

            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthJSONAccessTokenResponse response;
            response = oAuthClient.accessToken(request);
            oauthToken = response.getAccessToken();
///////////////////////////////
            if (refreshToken == null) {
                String clSeResult;
                clSeResult = RESTMethod.Get(mConstants.getAPPUri(), oauthToken);
                JSONObject json = null;
                json = new JSONObject(clSeResult);
                EventBus.getDefault()
                        .post(new ServerTokenAcquiredEvent(oauthToken, response.getExpiresIn(),
                                response.getRefreshToken(), json.getString("client"), json.getString("secret")));
            }
///////////////////////////////
            EventBus.getDefault()
                    .post(new ServerTokenAcquiredEvent(oauthToken, response.getExpiresIn(),
                            response.getRefreshToken()));
        } catch (OAuthSystemException e1) {

            e1.printStackTrace();
        } catch (OAuthProblemException e1) {

            e1.printStackTrace();
        }
    }

    @Override protected void onCancel() {
        //TODO
    }

    @Override protected boolean shouldReRunOnThrowable(Throwable throwable) {
        //TODO
        return true;
    }
}
