package com.jorgecastillo.kanadrill;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TrainingActivity extends Activity {

  private TextView kanaText, romanjiText;
  
  private int count;
  private int upto;
  @SuppressWarnings("unused")
  private int wrong;
  @SuppressWarnings("unused")
  private int rigth;
  private int[] order;

  private SharedPreferences myPreferences;
  private Resources myResources;

  private String[] romanji;
  private String[] hiragana;
  private String[] katakana;
  private String[] kana;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_training);
    
    kanaText = (TextView) findViewById(R.id.kanaText);
    romanjiText = (TextView) findViewById(R.id.romanjiText);
    
    myResources = getResources();

    romanji = myResources.getStringArray(R.array.romanji);
    hiragana = myResources.getStringArray(R.array.hiragana);
    katakana = myResources.getStringArray(R.array.katakana);
    kana = hiragana.clone();

    myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

    if (myPreferences.getBoolean("setup_true", false)) {

      int kana_list =
          Integer.parseInt(myPreferences.getString("kana_list", "1"));
  
      upto = CommonCode.setUpto(kana_list);

      order = new int[upto];

      if(1 == Integer.parseInt(myPreferences.getString("order_list", "1")) ){

        CommonCode.orderRandom(upto, order);
      } else {
        CommonCode.orderLinear(upto, order);
      }

      kanaText.setText(kana[order[count]]);
      romanjiText.setText(romanji[order[count]]);

    }

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.training, menu);
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
  
  public void onClickButtonNext(View view){

    count++;

      if (count >= upto) {
        if (myPreferences.getBoolean("katakana_checkbox", true)
            && Arrays.equals(kana, hiragana)) {
          count = 0;
          kana = katakana.clone();
        } else{
          System.exit(0);
          // The next line is to avoid an ArrayIndexOutOfBoundsException
          count = 0;
        }
        
      }

      kanaText.setText(kana[order[count]]);
      romanjiText.setText(romanji[order[count]]);

  }
  
}
