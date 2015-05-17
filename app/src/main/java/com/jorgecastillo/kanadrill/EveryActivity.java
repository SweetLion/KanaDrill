package com.jorgecastillo.kanadrill;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class EveryActivity extends Activity implements GestureDetector.OnGestureListener {

  protected GestureDetector mDetector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mDetector = new GestureDetector(this,this);
  }

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

  @Override
  public boolean onTouchEvent(MotionEvent event){
    this.mDetector.onTouchEvent(event);
    return super.onTouchEvent(event);
  }

  @Override
  public boolean onDown(MotionEvent event) { return true; }

  @Override
  public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

    float startX, startY, endX, endY;
    startX = event1.getX();
    startY = event1.getY();
    endX = event2.getX();
    endY = event2.getY();
    if (endX < startX){
      rightLeftFling();
    } else if (endX > startX) {
      leftRightFling();
    }
    return true;
  }

  @Override
  public void onLongPress(MotionEvent event) {}

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    return true;
  }

  @Override
  public void onShowPress(MotionEvent event) {}

  @Override
  public boolean onSingleTapUp(MotionEvent event) { return true; }

  public void rightLeftFling(){}
  public void leftRightFling(){}

}
