package com.jorgecastillo.kanadrill;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.widget.TextView;

public class TrainingActivity extends EveryActivity {

  protected TextView kanaText, romanjiText;
  
  protected int count = -1;
  protected int upto;
  protected int[] order;

  private SharedPreferences myPreferences;
  protected Resources myResources;

  protected String[] meaning;
  protected String[] japanese;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_training);

    kanaText = (TextView) findViewById(R.id.kanaText);
    romanjiText = (TextView) findViewById(R.id.romanjiText);

    myResources = getResources();
    setArrays();

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

      setButtons();
    }

  }

  public void setArrays(){}

  public void setButtons(){

    count++;
    if (count >= upto) {
      System.exit(0);
    }
    kanaText.setText(japanese[order[count]]);
    romanjiText.setText(meaning[order[count]]);
  }

  public void setButtonsBack(){
    count -= 2;
    if (count < -1){
      System.exit(0);
    }
    setButtons();
  }

  @Override
  public boolean onSingleTapUp(MotionEvent event) {
    setButtons();
    return true;
  }

  @Override
  public void rightLeftFling(){
    setButtons();
  }

  @Override
  public void leftRightFling(){
    setButtonsBack();
  }

}
