package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //start a tread with runabble class
        Runnable wait3seconds = new Runnable()
        {

            public void run()
            {
                nextActivity();
            }
        };
        //handler class muniplate the tread and import andiod os handler
        Handler handler = new Handler();
        handler.postDelayed(wait3seconds, 3000);
    }
    //start next act
    public void nextActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }





}
