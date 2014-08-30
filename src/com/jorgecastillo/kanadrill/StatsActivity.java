package com.jorgecastillo.kanadrill;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class StatsActivity extends Activity {
	
	Bundle myExtras;
	String text;
	TextView myText;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stats);
		
		Bundle myExtras = getIntent().getExtras();
		text = myExtras.getString("HiraganaErrorRate");
		text += "\n" + myExtras.getString("KatakanaErrorRate");
		myText = (TextView) findViewById(R.id.statsText);
		myText.setText(text);
		
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
