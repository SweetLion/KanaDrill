package com.jorgecastillo.kanadrill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SharedPreferences myPreferences;
	private Context myContext;
	private String textToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myContext = getApplicationContext();
		myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		if (!myPreferences.getBoolean("setup_true", false)) {
			
			SharedPreferences.Editor editMyPreferences = myPreferences.edit();
			editMyPreferences.putBoolean("setup_true", true);
			editMyPreferences.commit();
			
			textToast = "Setup the app";
			Toast.makeText(myContext, textToast, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);

		}
		
	}
	
	public void onClickButtonStart(View view) {
		  
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		
	}
	
	public void onClickButtonTrain(View view){
	  
	  Intent intent = new Intent(this, TrainingActivity.class);
	  startActivity(intent);
	  
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
