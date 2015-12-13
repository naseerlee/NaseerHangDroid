package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSinglePlayerGame(View view)
    {
        Intent intent= new Intent(this, GameActivity.class);
            startActivity(intent);

    }
    public void startMultiPlayerGame(View view)
    {
        Intent intent= new Intent(this, MultiPlayerActivity.class);
        startActivity(intent);

    }
    public void openScores(View view)
    {
        Intent intent =new Intent(this,ScoresActivity.class);
        startActivity(intent);

    }
    public void StarMainActivity(View view)
    {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
         //Handle item selection
        switch (item.getItemId()) {
            case R.id.action_help_png:
                newGame();
               return true;
           default:
                return super.onOptionsItemSelected(item);
      }
    }

    private void newGame() {
        Intent intent = new Intent(this, About.class);
        startActivity(intent);

    }


}
