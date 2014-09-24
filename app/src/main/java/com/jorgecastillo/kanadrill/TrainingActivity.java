package com.jorgecastillo.kanadrill;

import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        myResources = getResources();
        switch (id){
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "vookat@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "KanaDrill App - ");
                startActivity(Intent.createChooser(intent, myResources.getString(R.string.action_contact_title)));
                return true;
            case R.id.action_share:
                String urlToShare = "https://play.google.com/store/apps/details?id=com.jorgecastillo.kanadrill";
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                intent.setPackage("com.facebook.katana");
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
  
}
