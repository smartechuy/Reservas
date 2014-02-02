package uy.com.smartech.reservas.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import uy.com.smartech.reservas.controller.data.DataCategory;
import uy.com.smartech.reservas.controller.data.DataLocal;
import uy.com.smartech.reservas.controller.data.DataLogin;
import uy.com.smartech.reservas.controller.data.DataResponse;
import uy.com.smartech.reservas.controller.data.DataUser;

public class ServiceLayerImpl implements ServiceLayer {

	public static final String SERVER = "http://192.168.1.9:8080/Kona-Dashboard2/api/taio/app1/";

	public static final String LOGIN_URL = "r_login";
	public static final String USER_URL = "mr_user";
	boolean log = true;

	@Override
	public boolean login(String userId, String password) {

		DataLogin login = new DataLogin();
		login.setUser(userId);
		login.setPass(password);

		Gson gson = new Gson();

		String json = gson.toJson(login);

		System.out.println("sending " + json + " to " + SERVER + LOGIN_URL);

		String response = postJson(SERVER + LOGIN_URL, json);

		DataResponse dataResponse = gson.fromJson(response, DataResponse.class);
		System.out.println(dataResponse);

		return dataResponse.getData().isSuccess();
	}

	@Override
	public DataResponse newUser(String userId, String email, String password,
			String phoneNumber) {

		System.out.println("starting service");
		DataUser user = new DataUser();
		user.setEmail(email);
		user.setName(userId);
		user.setPass(password);

		System.out.println("sending " + user);

		Gson gson = new Gson();
		String json = gson.toJson(user);

		String response = postJson(SERVER + USER_URL, json);
		System.out.println(response);

		DataResponse dataResponse = gson.fromJson(response, DataResponse.class);

		System.out.println(dataResponse);

		return dataResponse;
		// return null;
	}

	@Override
	public List<DataCategory> getCategories() {

		return null;
	}

	@Override
	public List<DataLocal> getLocalesByCategory(String categorId) {

		return null;
	}

	@Override
	public void addLocalToFavorite(String localId, String userId) {

	}

	public static String postJson(String uri, String json) {
		try {

			System.out.println("ponting " + json);
			HttpPost httpPost = new HttpPost(uri);
			httpPost.setEntity(new StringEntity(json));
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse res = new DefaultHttpClient().execute(httpPost);

			InputStream content;
			try {
				content = res.getEntity().getContent();
				BufferedReader buffer = new BufferedReader(
						new InputStreamReader(content));
				String s = "";
				String response = "";
				try {
					while ((s = buffer.readLine()) != null) {
						response += s;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out.println(response);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONArray getJsonArray(String url) throws JSONException {

		String response = getFromString(url);
		JSONArray jObj = new JSONArray(response);
		return jObj;
	}

	public static JSONObject getJson(String url) throws JSONException {

		String response = getFromString(url);
		JSONObject jObj = new JSONObject(response);
		return jObj;
	}

	private static String getFromString(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		String response = "";
		try {
			HttpResponse execute = client.execute(httpGet);
			InputStream content = execute.getEntity().getContent();

			BufferedReader buffer = new BufferedReader(new InputStreamReader(
					content));
			String s = "";
			while ((s = buffer.readLine()) != null) {
				response += s;
			}

			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
