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
import java.util.Arrays;

public class GameActivity extends ActionBarActivity {
	
	private TextView gameText;
	private Button button1, button2, button3, button4;
	
	private int count;
	private int upto;
	private int wrong;
	private int rigth;
	private int replace;
	
	private int[] order;
	private int[] buttonValues = new int[4];
	
	private SharedPreferences myPreferences;
	private Resources myResources;
	private Context myContext;
	
	private String[] romanji;
	private String[] hiragana;
	private String[] katakana;
	private String[] kana;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		gameText = (TextView) findViewById(R.id.gameText);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		
		myResources = getResources();
		myContext = getApplicationContext();
		
		romanji = myResources.getStringArray(R.array.romanji);
		hiragana = myResources.getStringArray(R.array.hiragana);
		katakana = myResources.getStringArray(R.array.katakana);
		kana = hiragana.clone();
		
		
		myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (myPreferences.getBoolean("setup_true", false)){
			
			int kana_list = Integer.parseInt(myPreferences.getString("kana_list", "1"));
            upto = CommonCode.setUpto(kana_list);
			
			order = new int[upto];
			
			CommonCode.orderRandom(upto, order);

			setButtons();

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
		
		
		if(order[count] == buttonValues[0]){
            rigth++;
		} else{
			wrong++;
			Toast.makeText(myContext, "Wrong!", Toast.LENGTH_SHORT).show();
		}
		
		count++;
		setButtons();
		
	}

	public void onClickButton2(View view){
		
		
		if(order[count] == buttonValues[1]){
            rigth++;
		} else{
			wrong++;
			Toast.makeText(myContext, "Wrong!", Toast.LENGTH_SHORT).show();
		}
		
		count++;
		setButtons();
		
	}

	public void onClickButton3(View view){
		
		
		if(order[count] == buttonValues[2]){
            rigth++;
		} else{
			wrong++;
			Toast.makeText(myContext, "Wrong!", Toast.LENGTH_SHORT).show();
		}
		
		count++;
		setButtons();
		
	}

	public void onClickButton4(View view){
		
		
		if(order[count] == buttonValues[3]){
            rigth++;
		} else{
			wrong++;
			Toast.makeText(myContext, "Wrong!", Toast.LENGTH_SHORT).show();
		}
		
		count++;
		setButtons();
	}

	private void setButtons(){
		
		if(count >= upto){
			if ( myPreferences.getBoolean("katakana_checkbox", true) && Arrays.equals(kana, hiragana) ){
		          count = 0;
		          kana = katakana.clone();
			} else{
				Intent intent = new Intent(this, StatsActivity.class);
				intent.putExtra("Key1","This is a test");
				startActivity(intent);
				//The next line is to avoid an ArrayIndexOutOfBoundsException
				count = 0;
			}
		}
		
		
		
		buttonValues[0] = CommonCode.randomInt(upto);
		buttonValues[1] = CommonCode.randomInt(upto);
		buttonValues[2] = CommonCode.randomInt(upto);
		buttonValues[3] = CommonCode.randomInt(upto);
		replace = CommonCode.randomInt(4);
		buttonValues[replace] = order[count];
		
		gameText.setText(kana[order[count]]);
		button1.setText(romanji[buttonValues[0]]);
		button2.setText(romanji[buttonValues[1]]);
		button3.setText(romanji[buttonValues[2]]);
		button4.setText(romanji[buttonValues[3]]);
		
	}
}
