package br.com.android.guest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ShowActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
			
	       this.setRequestedOrientation
	       (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	       
		ListView guestlist = (ListView) findViewById(R.id.guest_list);
		final ArrayList<String> list = new ArrayList<String>();     

		ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		final String response = makeResquest("http://192.168.0.21:3000/guests.json");
		Log.d("JSON", response);
		
		try {
			JSONArray json = new JSONArray(response);
			for (int i = 0; i < json.length(); i++) {
				String name = json.getJSONObject(i).getString("name");
				
				list.add(name);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		ArrayAdapter<String> adap = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		guestlist.setAdapter(adap);
		
		guestlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				JSONArray array;
				
				try {
					array = new JSONArray(response);
					String na = array.getJSONObject(position).getString("name");
					String ge = array.getJSONObject(position).getString("gender");
					String ag = array.getJSONObject(position).getString("age");
					Intent intent = new Intent(ShowActivity.this, InfoActivity.class);
					intent.putExtra("name", na);
					intent.putExtra("gender", ge);
					intent.putExtra("age", ag);
					startActivity(intent);
				
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		
				
			}
		);}
	
	
	private String makeResquest(String urlAddress) {
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAddress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			con.disconnect();
		}
		return response;
	}
	
	private String readStream(InputStream in) {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return builder.toString();
	}		
}