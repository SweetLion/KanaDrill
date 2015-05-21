package com.jorgecastillo.kanadrill;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DrillActivity extends EveryActivity {

  protected TextView gameText;
  protected Button button1, button2, button3, button4;
	
  protected int count;
  protected int upto;

  protected int[] order;
  private int[] buttonValues = new int[4];
  protected Resources myResources;

  protected String[] meaning;
  protected String[] japanese;

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

    setArrays();

    if (myPreferences.getBoolean("setup_true", false)) {

      int kana_list = Integer.parseInt(myPreferences.getString("kana_list", "1"));

      upto = CommonCode.setUpto(kana_list);

      order = new int[upto];

      CommonCode.orderRandom(upto, order);

      setButtons();

    }

  }

  public void setArrays(){}

  public void onClickButton1(View view) { everyButton(0); }

  public void onClickButton2(View view) { everyButton(1); }

  public void onClickButton3(View view) { everyButton(2); }

  public void onClickButton4(View view) { everyButton(3); }

  public void onClickGameText(View view){
    wrongKana(order[count]);
    count++;
    setButtons();
  }
  
  public void everyButton(int value){

      if (order[count] == buttonValues[value]) {
      } else {
          wrongKana(order[count]);
      }

      count++;
      setButtons();
  }

  public void wrongKana(int count){
    KanaDrillDialog kdd = new KanaDrillDialog();
    kdd.setTitle(getString(R.string.wrong_kana));
    kdd.setValues(japanese[count], " = " + meaning[count]);
    kdd.show(getFragmentManager(), "Kana Dialog");
  }

  private void setButtons() {

    if (count >= upto) {
        System.exit(0);
    }


    int replace = CommonCode.randomInt(4);
    buttonValues[replace] = order[count];
    for(int i = 0; i < 4; i++){

      int val;
      if(i != replace) {
        val = CommonCode.randomInt(upto);
        for (int j = 0; j < i; j++) {
          while (val == buttonValues[j] || val == buttonValues[replace]) {
            val = CommonCode.randomInt(upto);
          }
        }
        buttonValues[i] = val;
      }

    }
		
    gameText.setText(japanese[order[count]]);
    button1.setText(meaning[buttonValues[0]]);
    button2.setText(meaning[buttonValues[1]]);
    button3.setText(meaning[buttonValues[2]]);
    button4.setText(meaning[buttonValues[3]]);

  }
}
