package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class GameLost extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lost);

        //GET MESSAGE FROM INTENT single player
        Intent intent = getIntent();
        String message = intent.getStringExtra(GameActivity.EXTRA_MESSAGE);
        //CREATE A TEXT VIEW
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(message);

//GET MESSAGE FROM INTENT from mutilplayer
        Intent intent2 = getIntent();
        String message2 = intent.getStringExtra(GameMultiActivity.EXTRA_MESSAGE);
        //CREATE A TEXT VIEW
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView.setText(message);

    }


    public void StarMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_lost, menu);

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_call_png:
                newGame();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void newGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}