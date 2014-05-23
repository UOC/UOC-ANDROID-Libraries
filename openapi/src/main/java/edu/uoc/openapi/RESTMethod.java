package edu.uoc.openapi;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class RESTMethod {

	public static String Get(String URI, String token) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI+"?access_token="+token);
		httpGet.setHeader("content-type", "application/json");
		try {
			HttpResponse resp = httpClient.execute(httpGet);
			return EntityUtils.toString(resp.getEntity());
		} catch (Exception ex) {
			Log.e("REST", "GET Error!", ex);
		}
		return null;
	}
	
	public static String Get(String URI, String token, String parameters) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI+"?access_token="+token+parameters);
		httpGet.setHeader("content-type", "application/json");
		try {
			HttpResponse resp = httpClient.execute(httpGet);
			return EntityUtils.toString(resp.getEntity());
		} catch (Exception ex) {
			Log.e("REST", "GET Error!", ex);
		}
		return null;
	}

	public static String Put(String URI, String token, String JSONObject) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(URI +"?access_token="+ token);
		httpPut.setHeader("content-type", "application/json");

		try {

			StringEntity entity = new StringEntity(JSONObject, "UTF-8");
			httpPut.setEntity(entity);
			HttpResponse resp = httpClient.execute(httpPut);

			String putResponseString = EntityUtils.toString(resp.getEntity());
			return putResponseString;

		} catch (Exception ex) {
			Log.e("REST", "PUT Error!", ex);
		}
		return null;
	}

	public static String Post(String URI, String token, String JSONObject) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URI +"?access_token="+ token);
		httpPost.setHeader("content-type", "application/json");

		try {
			StringEntity entity = new StringEntity(JSONObject);
			httpPost.setEntity(entity);
			HttpResponse resp = httpClient.execute(httpPost);
			return EntityUtils.toString(resp.getEntity());

		} catch (Exception ex) {
			Log.e("REST", "POST Error!", ex);
		}
		return null;
	}

	public static String Post(String URI, String token) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URI +"?access_token="+ token);
		httpPost.setHeader("content-type", "application/json");

		try {
			HttpResponse resp = httpClient.execute(httpPost);
			return EntityUtils.toString(resp.getEntity());

		} catch (Exception ex) {
			Log.e("REST", "POST Error!", ex);
		}
		return null;
	}

}
