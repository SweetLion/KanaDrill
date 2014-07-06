package com.jorgecastillo.kanadrill;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends ActionBarActivity {
	
	private TextView gameText;
	private Button button1, button2, button3, button4;
	private int count;
	private int upto;
	private int[] order;
	private SharedPreferences myPreferences;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		gameText = (TextView) findViewById(R.id.gameText);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		
		myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (myPreferences.getBoolean("setup_true", false)){
			
			int kana_list = Integer.parseInt(myPreferences.getString("kana_list", "1"));
            upto = CommonCode.setUpto(kana_list);
			
			order = new int[upto];
			
			CommonCode.orderRandom(upto, order);
			
			gameText.setText("A");
			button1.setText("bb");
			button2.setText("cc");
			button3.setText("dd");
			button4.setText("ee");

			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
