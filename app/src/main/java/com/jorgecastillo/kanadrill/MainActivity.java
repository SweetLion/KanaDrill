package com.jorgecastillo.kanadrill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private SharedPreferences myPreferences;
	private Context myContext;
	private String textToast;
    private Resources myResources;
	
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
	
	public void onClickButtonStart(View view) {
		  
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		
	}
	
	public void onClickButtonTrain(View view){
	  
	  Intent intent = new Intent(this, TrainingActivity.class);
	  startActivity(intent);
	  
	}
    public void onClickButtonKanji(View view){

        Intent intent = new Intent(this, KanjiActivity.class);
        startActivity(intent);

    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        myResources = getResources();
        switch (id){
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_contact:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "vookat@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "KanaDrill App - ");
                startActivity(Intent.createChooser(intent, myResources.getString(R.string.action_contact_title)));
                return true;
            case R.id.action_share:
                String urlToShare = "https://play.google.com/store/apps/details?id=com.jorgecastillo.kanadrill";
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                intent.setPackage("com.facebook.katana");
                startActivity(intent);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
