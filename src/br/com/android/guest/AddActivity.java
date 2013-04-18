package br.com.android.guest;



import java.io.IOException;

import us.monoid.web.Resty;
import static us.monoid.web.Resty.*;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);
		
	       this.setRequestedOrientation
	       (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		final EditText nameEditText = (EditText) findViewById(R.id.name_edit);
		final EditText genderEditText = (EditText) findViewById(R.id.gender_edit);
		final EditText ageEditText = (EditText) findViewById(R.id.age_edit);
		Button button = (Button) findViewById(R.id.add);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			 try {
				String name = nameEditText.getEditableText().toString();
				String gender = genderEditText.getEditableText().toString();
				String age = ageEditText.getEditableText().toString();
				
				Resty resty = new Resty();
				 resty.json("http://192.168.0.21:3000/guests", 
						 form(data("guest[name]",  name),
								 data("guest[gender]", gender),
								 data("guest[age]", age)));
				 Intent intent  = new Intent(AddActivity.this, MainActivity.class);
				 startActivity(intent);
					
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});

}
}