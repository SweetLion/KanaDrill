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

public class KanjiTrainingActivity extends Activity {

  private TextView kanjiText, kanaText, englishText;

  private int count;
  private int upto = 2136;
  private int[] order;

  private Resources myResources;

  private String[] kanji;
  private String[] english;
  private String[] kana;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_training_kanji);

    kanjiText = (TextView) findViewById(R.id.kanjiText);
    englishText = (TextView) findViewById(R.id.englishText);
    kanaText = (TextView) findViewById(R.id.kanaText);

    myResources = getResources();
    kanji = myResources.getStringArray(R.array.kanji);
    english = myResources.getStringArray(R.array.english);
    kana = myResources.getStringArray(R.array.kana);

    order = new int[2136];
    CommonCode.orderLinear(upto, order);
    kanjiText.setText(kanji[order[count]]);
    englishText.setText(english[order[count]]);
    kanaText.setText(kana[order[count]]);
    getActionBar().setTitle("" + (count + 1));




  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.training, menu);
    return true;
  }

  public void onClickButtonNext(View view){

    count++;

    if (count >= upto) {
      System.exit(0);
    }

    kanjiText.setText(kanji[order[count]]);
    englishText.setText(english[order[count]]);
    kanaText.setText(kana[order[count]]);
    getActionBar().setTitle("" + (count + 1));

  }
  public void onClickButtonBack(View view){

    count--;

    if (count < 0) {
      System.exit(0);
    }

    kanjiText.setText(kanji[order[count]]);
    englishText.setText(english[order[count]]);
    kanaText.setText(kana[order[count]]);
    getActionBar().setTitle("" + (count + 1));


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