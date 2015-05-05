package com.jorgecastillo.kanadrill;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class KanjiActivity extends Activity {

    private Context myContext;
	private int count;
	private String[] kanji;
	private String[] english;
	private String[] kana;
    private int[] english_rigth;
	private TextView kanjiDisplay;
    private int[] order;
    private int upto = 2136;
    int[] buttonValues = new int[4];

    private Button button1, button2, button3, button4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kanji);

        order = new int[2136];
		Resources myResources = getResources();
        myContext = getApplicationContext();
		kanji = myResources.getStringArray(R.array.kanji);
		english = myResources.getStringArray(R.array.english);
        kana = myResources.getStringArray(R.array.kana);
        english_rigth = CommonCode.fileToIntArray(myContext, "english_rigth.txt", 2136);
        Log.v("PRIMER VALOR", "" + english_rigth[order[count]]);
		
		kanjiDisplay = (TextView) findViewById(R.id.kanjiText);
        button1 = (Button) findViewById(R.id.buttonkanji1);
        button2 = (Button) findViewById(R.id.buttonkanji2);
        button3 = (Button) findViewById(R.id.buttonkanji3);
        button4 = (Button) findViewById(R.id.buttonkanji4);
        CommonCode.orderLinear(upto, order);
		setButtons();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

    public void onClickButton1(View view) {
        if (order[count] == buttonValues[0]) {
            english_rigth[order[count]] = 1;
            CommonCode.intArrayToFile(myContext, "english_rigth.txt", english_rigth);
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void onClickButton2(View view) {
        if (order[count] == buttonValues[1]) {
            english_rigth[order[count]] = 1;
            Log.v("VALOR A ESCRIBIR", "" + 1);
            CommonCode.intArrayToFile(myContext, "english_rigth.txt", english_rigth);
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void onClickButton3(View view) {
        if (order[count] == buttonValues[2]) {
            english_rigth[order[count]] = 1;
            CommonCode.intArrayToFile(myContext, "english_rigth.txt", english_rigth);
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void onClickButton4(View view) {
        if (order[count] == buttonValues[3]) {
            english_rigth[order[count]] = 1;
            CommonCode.intArrayToFile(myContext, "english_rigth.txt", english_rigth);
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void wrongKanji(int count){
        KanaDrillDialog kdd = new KanaDrillDialog();
        kdd.setTitle(getString(R.string.wrong_kanji));
        kdd.setValues(kanji[count], english[count]);
        kdd.show(getFragmentManager(), "Kanji Dialog");
    }
    private void setButtons() {

        if (count >= upto){
            System.exit(0);
            count = 0;
        }
        while(english_rigth[order[count]] == 1){
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
        button1.setText(english[buttonValues[0]]);
        button2.setText(english[buttonValues[1]]);
        button3.setText(english[buttonValues[2]]);
        button4.setText(english[buttonValues[3]]);

    }

    private boolean intExists(int[] vector, int element, int length){
        for(int i = 0; i < length; i++){
            if (vector[i] == element){
                return true;
            }
        }
        return false;
    }

}
