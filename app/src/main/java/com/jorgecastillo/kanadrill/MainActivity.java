package com.jorgecastillo.kanadrill;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends EveryActivity {
	
	private SharedPreferences myPreferences;
	private Context myContext;
	private String textToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myContext = getApplicationContext();
		myPreferences = PreferenceManager.getDefaultSharedPreferences(this);

		if (!myPreferences.getBoolean("setup_true", false)) {
			
			SharedPreferences.Editor editMyPreferences = myPreferences.edit();
			editMyPreferences.putBoolean("setup_true", true);
			editMyPreferences.commit();
			
			textToast = "Setup the app";
			Toast.makeText(myContext, textToast, Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent(this, SettingsActivity.class);
			startActivity(intent);

		}
		
	}

    public void onClickButtonHiraganaDrill(View view){

        Intent intent = new Intent(this, HiraganaDrillActivity.class);
        startActivity(intent);
    }

    public void onClickButtonKatakanaDrill(View view){

        Intent intent = new Intent(this, KatakanaDrillActivity.class);
        startActivity(intent);
    }

    public void onClickButtonHiraganaTraining(View view){

        Intent intent = new Intent(this, HiraganaTrainingActivity.class);
        startActivity(intent);
    }

    public void onClickButtonKatakanaTraining(View view){

        Intent intent = new Intent(this, KatakanaTrainingActivity.class);
        startActivity(intent);
    }

    public void onClickButtonKanjiDrillEnglish(View view){

        Intent intent = new Intent(this, KanjiDrillEnglishActivity.class);
        startActivity(intent);
    }

    public void onClickButtonKanjiDrillJapanese(View view){

        Intent intent = new Intent(this, KanjiDrillJapaneseActivity.class);
        startActivity(intent);
    }

    public void onClickButtonKanjiTraining(View view){

        Intent intent = new Intent(this, KanjiTrainingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        recreate();
    }

}
