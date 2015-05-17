package com.jorgecastillo.kanadrill;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KanjiTrainingActivity extends EveryActivity {

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

}