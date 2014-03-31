package com.uoc.openapilibrary;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Utils {
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

    public static Boolean validToken(){
        return LoginActivity.validToken();
    }
	
	public static String getToken(Context context) {
		return LoginActivity.getToken(context);
	}

    public static String refreshToken(Context context) {
        return LoginActivity.refreshToken(context);
    }

	public static String toJSON(Object obj) {
		return new Gson().toJson(obj);
	}

    /*
    Because of "security reasons" I was told not to store strings on the device in plain text,
     not even the key I use to encrypt said strings, so I was forced to implement encrypt/decrypt algorithms that
     only use the original installation date as key seed and the name of the package as salt for PBEKeySpec

     Because they are static functions, the user needs to provide not only the text to encrypt/decrypt, but the
     context as well.

     Just use encrypt(getApplicationContext(), text) to encrypt and decrypt(getApplicationContext(), text)
     to decrypt. Because you will use getToken() a lot, it was modified to do a decrypt call of the token,
     that's why getToken() needs the context as well.
     */
    public static String encrypt(Context context, String text) throws Exception {
        byte[] message= Base64.encode(text.getBytes(), Base64.DEFAULT);
        SecretKey skeySpec = generateKey(getSeed(context),"com.uoc.esquelet.app".getBytes());
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(message);
        String result = Base64.encodeToString(encrypted, Base64.DEFAULT);
        return result;
    }

    public static String decrypt(Context context, String encrypted) throws Exception {
        SecretKey skeySpec = generateKey(getSeed(context),"com.uoc.esquelet.app".getBytes());
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decordedValue = Base64.decode(encrypted.getBytes(), Base64.DEFAULT);
        byte[] decValue = cipher.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        String decoded=new String(Base64.decode(decryptedValue, Base64.DEFAULT));
        return decoded;
    }

    public static SecretKey generateKey(char[] passphraseOrPin, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        final int iterations = 1000;

        final int outputKeyLength = 128;

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(passphraseOrPin, salt, iterations, outputKeyLength);
        SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
        return secretKey;
    }

    private static char[] getSeed(Context context){
        long installed = 0;
        try {
            installed = context
                    .getPackageManager()
                    .getPackageInfo("com.uoc.esquelet.app", 0)
                    .firstInstallTime
            ;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return Long.toString(installed).toCharArray();
    }

}
