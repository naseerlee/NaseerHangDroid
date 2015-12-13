package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ScoresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        //find perefernece
        SharedPreferences preferences = getSharedPreferences("WORD_SCORES", MODE_PRIVATE);
        //read preferences
        String scores = preferences.getString("SCORES","NO SCORES");
        //get the textview for the score
        TextView textView =(TextView)findViewById(R.id.textViewScores);
        //put score in text view
        textView.setText(scores);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}