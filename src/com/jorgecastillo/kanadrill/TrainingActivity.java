package com.jorgecastillo.kanadrill;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.preference.PreferenceManager;

public class TrainingActivity extends ActionBarActivity {
	
	private int count;
	private int upto;
	private int[] order;
	
	private String[] romanji;
	private String[] hiragana;
	private String[] katakana;
	
	private boolean onhiragana = true;
	
	private TextView kanaDisplay;
	private EditText kanaInput;
	
	private SharedPreferences myPreferences;
	private Context myContext;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training);
		
		Resources myResources = getResources();
		romanji = myResources.getStringArray(R.array.romanji);
		hiragana = myResources.getStringArray(R.array.hiragana);
		katakana = myResources.getStringArray(R.array.katakana);
		
		kanaDisplay = (TextView) findViewById(R.id.kanaDisplay);
		kanaInput = (EditText) findViewById(R.id.kanaInput);
		
		myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		if (myPreferences.getBoolean("setup_true", false)){
		
			int kana_list = Integer.parseInt(myPreferences.getString("kana_list", "1"));
            upto = CommonCode.setUpto(kana_list);
			
			order = new int[upto];
			
			int order_list = Integer.parseInt(myPreferences.getString("order_list", "1"));
			switch(order_list){
			  case 1:
				  CommonCode.orderRandom(upto, order);
				  break;
			  case 2:
				  CommonCode.orderLinear(upto, order);
				  break;
			  default:
				  break;
			}
			
			kanaDisplay.setText(hiragana[order[count]]);
			kanaInput.setText(hiragana[order[count]]);

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
	
	public void onClickButtonOkay(View view){
		
		if(!kanaInput.getText().toString().equals(romanji[order[count]])){
			
			Dialog dialog = new Dialog(TrainingActivity.this);
			dialog.setContentView(R.layout.dialog_view);
			
			TextView dialogKana = (TextView) dialog.findViewById(R.id.dialog_kana);
			TextView dialogRomanji = (TextView) dialog.findViewById(R.id.dialog_romanji);
			
			if (onhiragana){
			    dialogKana.setText("  " + hiragana[order[count]] + "  ");
			}else {
			    dialogKana.setText("  " + katakana[order[count]] + "  ");
			}
			dialogRomanji.setText("  " + romanji[order[count]] + "  ");
			
			dialog.show();
		}
		
		count++;
		
		if (count >= upto){
			
			count = 0;
			
			if (myPreferences.getBoolean("katakana_checkbox", true)){
			    onhiragana = !onhiragana;
			    Toast.makeText(myContext, onhiragana?"Hiragana":"Katakana", Toast.LENGTH_SHORT).show();
			}
		}
		
		if (onhiragana){
		    kanaDisplay.setText(hiragana[order[count]]);
		    kanaInput.setText(hiragana[order[count]]);
		}
		
		if((!onhiragana) && myPreferences.getBoolean("katakana_checkbox", true)){
			kanaDisplay.setText(katakana[order[count]]);
			kanaInput.setText(katakana[order[count]]);
		}
		
	}
	
}
