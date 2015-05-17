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
import android.widget.TextView;

public class KanjiDrillEnglishActivity extends Activity {

    private Context myContext;
    private int count;
	private String[] kanji;
	private String[] english;
    private int[] english_right;
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
        english_right = CommonCode.fileToIntArray(myContext, "english_right.txt", 2136);
		
		kanjiDisplay = (TextView) findViewById(R.id.kanjiText);
        button1 = (Button) findViewById(R.id.buttonkanji1);
        button2 = (Button) findViewById(R.id.buttonkanji2);
        button3 = (Button) findViewById(R.id.buttonkanji3);
        button4 = (Button) findViewById(R.id.buttonkanji4);
        CommonCode.orderLinear(upto, order);
        setButtons();

	}

    @Override
    public void onStop(){
        super.onStop();
        CommonCode.intArrayToFile(myContext, "english_right.txt", english_right);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
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
            english_right[order[count]] = 1;
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
        kdd.setValues(kanji[count], "\n" + english[count]);
        kdd.show(getFragmentManager(), "KanaDrill Dialog");
    }

    private int setButtons() {

        if (count >= upto){
            System.exit(0);
        }
        while(english_right[order[count]] == 1){
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

        return count;

    }

}
