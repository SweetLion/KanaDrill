package com.jorgecastillo.kanadrill;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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
	private String[] romanji;
	private TextView kanjiDisplay;
	private EditText kanjiInput;
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
	/*
	public void onClickButtonOkay(View view){
		
		if (count == 829){
			count = 830;
		}
			
		kanjiDisplay.setText(kanji[count]);
		kanjiInput.setText(english[count++]);
		
		if (count >= 2136){
			count = 0;
		}
		
	}*/

    public void onClickButton1(View view) {
        if (order[count] == buttonValues[0]) {
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void onClickButton2(View view) {
        if (order[count] == buttonValues[1]) {
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void onClickButton3(View view) {
        if (order[count] == buttonValues[2]) {
        } else {
            wrongKanji(order[count]);
        }
        count++;
        setButtons();
    }

    public void onClickButton4(View view) {
        if (order[count] == buttonValues[3]) {
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

        int replace = randomInt(4);
        buttonValues[replace] = order[count];

        for (int i = 0; i < 4; i++){
            int val;
            if (i != replace) {
                val = randomInt(upto);
                for (int j = 0; j < i; j++) {
                    if (val == buttonValues[j] || val == buttonValues[replace]) {
                        j = -1;
                        val = randomInt(upto);
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

    private void orderRandom(int upto, int[] order){
        Arrays.fill(order, -1);
        for (int i = 0; i < upto;){
            int val = (int) Math.floor(Math.random() * upto);
            if (!intExists(order, val, upto)){
                order[i] = val;
                i++;
            }
        }
    }

    private int randomInt(int upto) {
        int number;
        number = (int) Math.floor(Math.random() * upto);
        return number;
    }


}
