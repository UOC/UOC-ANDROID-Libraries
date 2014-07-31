package edu.uoc.openapi;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RESTMethod {

	public static String Get(String URI, String token) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI+"?access_token="+token);
		httpGet.setHeader("content-type", "application/json");

        //Could throw an exception
		HttpResponse resp = httpClient.execute(httpGet);
		return EntityUtils.toString(resp.getEntity());
	}
	
	public static String Get(String URI, String token, String parameters) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(URI+"?access_token="+token+parameters);
		httpGet.setHeader("content-type", "application/json");

        //Could throw an exception
		HttpResponse resp = httpClient.execute(httpGet);
		return EntityUtils.toString(resp.getEntity());

	}

	public static String Put(String URI, String token, Object object) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(URI +"?access_token="+ token);
		httpPut.setHeader("content-type", "application/json");
        String JSONObject = new Gson().toJson(object);

        //Could throw an exception
		StringEntity entity = new StringEntity(JSONObject, "UTF-8");
		httpPut.setEntity(entity);
		HttpResponse resp = httpClient.execute(httpPut);
		return EntityUtils.toString(resp.getEntity());

	}

	public static String Post(String URI, String token, Object object) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URI +"?access_token="+ token);
		httpPost.setHeader("content-type", "application/json");
        String JSONObject = new Gson().toJson(object);

        //Could throw an exception
		StringEntity entity = new StringEntity(JSONObject);
		httpPost.setEntity(entity);
		HttpResponse resp = httpClient.execute(httpPost);
		return EntityUtils.toString(resp.getEntity());
	}

	public static String Post(String URI, String token) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URI +"?access_token="+ token);
		httpPost.setHeader("content-type", "application/json");

        //Could throw an exception
		HttpResponse resp = httpClient.execute(httpPost);
		return EntityUtils.toString(resp.getEntity());
	}

}
