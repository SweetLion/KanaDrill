package com.jorgecastillo.kanadrill;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KanjiDrillActivity extends EveryActivity {

  protected Context myContext;
  protected int count;
	protected String[] kanji;
	protected String[] meaning;
  protected int[] meaning_right;
  protected String filename;
	protected TextView kanjiDisplay;
  protected int[] order;
  protected int upto = 2136;
  protected int[] buttonValues = new int[4];
  protected Button button1, button2, button3, button4;
  protected Resources myResources;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji);

    order = new int[upto];
		myResources = getResources();
    myContext = getApplicationContext();

    setVariables();
		
		kanjiDisplay = (TextView) findViewById(R.id.kanjiText);
    button1 = (Button) findViewById(R.id.buttonkanji1);
    button2 = (Button) findViewById(R.id.buttonkanji2);
    button3 = (Button) findViewById(R.id.buttonkanji3);
    button4 = (Button) findViewById(R.id.buttonkanji4);

    CommonCode.orderLinear(upto, order);
    setButtons();

	}

  public  void setVariables(){}

  @Override
  public void onStop(){
    super.onStop();
    CommonCode.intArrayToFile(myContext, filename, meaning_right);
  }

  public void onClickButton1(View view) { everyButton(0); }

  public void onClickButton2(View view) { everyButton(1); }

  public void onClickButton3(View view) { everyButton(2); }

  public void onClickButton4(View view) { everyButton(3); }

  public void everyButton(int value){
    checkValues(value);
    count++;
    setButtons();
  }

  public void checkValues(int value){
    if (order[count] == buttonValues[value]) {
      meaning_right[order[count]] = 1;
    } else {
      wrongInput(order[count]);
    }
  }

  public void onClickTextViewKanji(View view) {
    wrongInput(order[count]);
    count++;
    setButtons();
  }

  public void wrongInput(int count){
    KanaDrillDialog kdd = new KanaDrillDialog();
    kdd.setTitle(getString(R.string.wrong_value));
    kdd.setValues(kanji[count], "\n" + meaning[count]);
    kdd.show(getFragmentManager(), "KanaDrill Dialog");
  }

  private int setButtons() {

    if (count == 829) {
      count = 830;
    }
    if (count >= upto){
      System.exit(0);
    }
    while(meaning_right[order[count]] == 1){
      count++;
    }

    int replace = CommonCode.randomInt(4);
    buttonValues[replace] = order[count];

    for (int i = 0; i < 4; i++){
      int val;
      if (i != replace) {
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

    kanjiDisplay.setText(kanji[order[count]]);
    button1.setText(meaning[buttonValues[0]]);
    button2.setText(meaning[buttonValues[1]]);
    button3.setText(meaning[buttonValues[2]]);
    button4.setText(meaning[buttonValues[3]]);

    return count;

  }

}
