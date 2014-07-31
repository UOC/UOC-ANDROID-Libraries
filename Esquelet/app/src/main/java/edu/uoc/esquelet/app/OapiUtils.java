package edu.uoc.esquelet.app;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class OapiUtils {
    public static Bitmap loadImageFromNetwork(String photoUrl) {
        Bitmap bm = null;
        try {
            InputStream in = new java.net.URL(photoUrl).openStream();
            bm = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bm;
    }

    public static String toJSON(Object obj) {
        return new Gson().toJson(obj);
    }

    public static String encrypt(Context context, String text) throws Exception {
        byte[] message= Base64.encode(text.getBytes(), Base64.DEFAULT);
        SecretKey skeySpec = generateKey(getSeed(context),"edu.uoc.esquelet.app".getBytes());
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(message);
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }

    public static String decrypt(Context context, String encrypted) throws Exception {
        SecretKey skeySpec = generateKey(getSeed(context),"edu.uoc.esquelet.app".getBytes());
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decordedValue = Base64.decode(encrypted.getBytes(), Base64.DEFAULT);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return new String(Base64.decode(decryptedValue, Base64.DEFAULT));
    }

    public static SecretKey generateKey(char[] passphraseOrPin, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        final int iterations = 1000;

        final int outputKeyLength = 128;

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(passphraseOrPin, salt, iterations, outputKeyLength);
        return secretKeyFactory.generateSecret(keySpec);
    }

    private static char[] getSeed(Context context){
        long installed = 0;
        try {
            installed = context
                    .getPackageManager()
                    .getPackageInfo("edu.uoc.esquelet.app", 0)
                    .firstInstallTime
            ;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Long.toString(installed).toCharArray();
    }

}
