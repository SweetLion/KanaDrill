package com.jorgecastillo.kanadrill;

import android.os.Bundle;

public class KanjiDrillJapaneseActivity extends KanjiDrillActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public void setVariables(){
    kanji = myResources.getStringArray(R.array.kanji);
    meaning = myResources.getStringArray(R.array.kana);
    filename = "kana_right.txt";
    meaning_right = CommonCode.fileToIntArray(myContext, filename, upto);
  }
}