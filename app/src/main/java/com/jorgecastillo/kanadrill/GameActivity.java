package com.jorgecastillo.kanadrill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class GameActivity extends Activity {

  private TextView gameText;
  private Button button1, button2, button3, button4;
	
  private int count;
  private int upto;
  @SuppressWarnings("unused")
  private int wrong;
  @SuppressWarnings("unused")
  private int rigth;

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

    if (myPreferences.getBoolean("setup_true", false)) {

      int kana_list =
          Integer.parseInt(myPreferences.getString("kana_list", "1"));
	
      upto = CommonCode.setUpto(kana_list);

      order = new int[upto];

      CommonCode.orderRandom(upto, order);

      setButtons();

    }

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.main, menu);
    return true;

  }

  public void onClickButton1(View view) {

    if (order[count] == buttonValues[0]) {
      rigth++;
    } else {
      wrong++;
      wrongKana(order[count]);
    }

    count++;
    setButtons();

  }

  public void onClickButton2(View view) {
	
    if (order[count] == buttonValues[1]) {
      rigth++;
    } else {
      wrong++;
      wrongKana(order[count]);
    }

    count++;
    setButtons();

  }

  public void onClickButton3(View view) {

    if (order[count] == buttonValues[2]) {
      rigth++;
    } else {
      wrong++;
      wrongKana(order[count]);
    }

    count++;
    setButtons();

  }

  public void onClickButton4(View view) {

    if (order[count] == buttonValues[3]) {
      rigth++;
    } else {
      wrong++;
      wrongKana(order[count]);
    }

    count++;
    setButtons();
  }

  public void wrongKana(int count){
    KanaDrillDialog kdd = new KanaDrillDialog();
    kdd.setTitle(getString(R.string.wrong_kana));
    kdd.setValues(romanji[count], kana[count]);
    kdd.show(getFragmentManager(), "Kana Dialog");
  }

  private void setButtons() {
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

    int replace = CommonCode.randomInt(4);
    buttonValues[replace] = order[count];
    for(int i = 0; i < 4; i++){

      int val;
      if(i != replace) {
        val = CommonCode.randomInt(upto);
        for (int j = 0; j < i; j++) {
          if (val == buttonValues[j] || val == buttonValues[replace]) {
            j = -1;
            val = CommonCode.randomInt(upto);
          }
        }
        buttonValues[i] = val;
      }

    }
		
    gameText.setText(kana[order[count]]);
    button1.setText(romanji[buttonValues[0]]);
    button2.setText(romanji[buttonValues[1]]);
    button3.setText(romanji[buttonValues[2]]);
    button4.setText(romanji[buttonValues[3]]);

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
