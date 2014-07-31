package edu.uoc.openapi.uoclogin.controllers;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UocAuthenticatorService extends Service {

    public UocAuthenticator authenticator;

    @Override
    public IBinder onBind(Intent intent) {

        UocAuthenticator authenticator = new UocAuthenticator(this);

        return authenticator.getIBinder();
    }
}
