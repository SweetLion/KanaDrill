package com.jorgecastillo.kanadrill;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends ActionBarActivity {
	
	private TextView gameText;
	private Button button1, button2, button3, button4;
	
	private int count;
	private int upto;
	private int replace;
	private int[] order;
	private int[] buttonValues = new int[4];
	
	private SharedPreferences myPreferences;
	private Context myContext;
	
	private String[] romanji;
	private String[] hiragana;
	private String[] katakana;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		gameText = (TextView) findViewById(R.id.gameText);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		
		Resources myResources = getResources();
		romanji = myResources.getStringArray(R.array.romanji);
		hiragana = myResources.getStringArray(R.array.hiragana);
		katakana = myResources.getStringArray(R.array.katakana);
		
		myContext = getApplicationContext();
		myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (myPreferences.getBoolean("setup_true", false)){
			
			int kana_list = Integer.parseInt(myPreferences.getString("kana_list", "1"));
            upto = CommonCode.setUpto(kana_list);
			
			order = new int[upto];
			
			CommonCode.orderRandom(upto, order);
			
			buttonValues[0] = CommonCode.randomInt(upto);
			buttonValues[1] = CommonCode.randomInt(upto);
			buttonValues[2] = CommonCode.randomInt(upto);
			buttonValues[3] = CommonCode.randomInt(upto);
			replace = CommonCode.randomInt(4);
			buttonValues[replace] = order[count];
			
			gameText.setText(hiragana[order[count]]);
			button1.setText(hiragana[buttonValues[0]]);
			button2.setText(hiragana[buttonValues[1]]);
			button3.setText(hiragana[buttonValues[2]]);
			button4.setText(hiragana[buttonValues[3]]);

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
	
	public void onClickButton1(View view){
		
		boolean rigth = false;
		
		if(order[count] == buttonValues[0]){
			rigth = true;
		}
		
		Toast.makeText(myContext, rigth?"Rigth":"Wrong", Toast.LENGTH_SHORT).show();
		
	}

	public void onClickButton2(View view){
		
		boolean rigth = false;
		
		if(order[count] == buttonValues[1]){
			rigth = true;
		}
		
		Toast.makeText(myContext, rigth?"Rigth":"Wrong", Toast.LENGTH_SHORT).show();
	}

	public void onClickButton3(View view){
		
		boolean rigth = false;
		
		if(order[count] == buttonValues[2]){
			rigth = true;
		}
		
		Toast.makeText(myContext, rigth?"Rigth":"Wrong", Toast.LENGTH_SHORT).show();
	}

	public void onClickButton4(View view){
		
		boolean rigth = false;
		
		if(order[count] == buttonValues[3]){
			rigth = true;
		}
		
		Toast.makeText(myContext, rigth?"Rigth":"Wrong", Toast.LENGTH_SHORT).show();
	}

}
