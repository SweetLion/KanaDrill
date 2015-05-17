package com.jorgecastillo.kanadrill;

import android.os.Bundle;

public class HiraganaDrillActivity extends DrillActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

  @Override
  public void setArrays(){
    meaning = myResources.getStringArray(R.array.romanji);
    japanese = myResources.getStringArray(R.array.hiragana);
  }

}
