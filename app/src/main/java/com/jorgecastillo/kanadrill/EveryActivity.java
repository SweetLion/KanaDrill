package com.jorgecastillo.kanadrill;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;

public class EveryActivity extends Activity {

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.training, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    Intent intent;
    switch (id){
      case R.id.action_settings:
        intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        return true;
      case R.id.action_contact:
        intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "vookat@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, "KanaDrill App - ");
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.action_contact_title)));
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