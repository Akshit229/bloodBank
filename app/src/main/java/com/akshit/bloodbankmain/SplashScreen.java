package com.akshit.bloodbankmain;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;


public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        final String city = PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                .getString("city", "no_city");
        if(city.equals("no_city")){
          startActivity(new Intent(SplashScreen.this, LoginActivity.class));
        }else{
          startActivity(new Intent(SplashScreen.this, MainActivity.class));
        }
        finish();
      }
    }, 1500);
  }
}
