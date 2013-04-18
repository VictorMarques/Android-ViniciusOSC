package br.com.android.guest;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	       this.setRequestedOrientation
	       (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	       
		Button button = (Button) findViewById(R.id.add);
		Button button2 = (Button) findViewById(R.id.show);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddActivity.class);
				startActivity(intent);
				
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ShowActivity.class);
				startActivity(intent);
				
			}
		});
	}
}