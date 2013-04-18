package br.com.android.guest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;


public class InfoActivity extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info);
		
	       this.setRequestedOrientation
	       (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	       
		TextView name = (TextView) findViewById(R.id.info_name);
		TextView gender = (TextView) findViewById(R.id.info_gender);
		TextView age = (TextView) findViewById(R.id.info_age);
		Bundle details = getIntent().getExtras();
		
		String detname = details.getString("name");
		String detgender = details.getString("gender");
		String detage = details.getString("age"); 
		
		name.setText(getString(R.string.info_name, detname));
		gender.setText(getString(R.string.info_gender, detgender));
		age.setText(getString(R.string.info_age, detage));
	}
}