package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOverActivity extends Activity {
    private int playerPoints=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int points = getIntent().getIntExtra("PointsID",0); //defualts value in case no data is send

        TextView textView =(TextView)findViewById(R.id.textViewPoints);//set the socre on the screen
        textView.setText(String.valueOf(points));//convert  point to string
        playerPoints=points;
    }
    //save score button call to save store
    public void saveScore(View view)
    {
    //set up shared perfernces

        SharedPreferences preferences= getSharedPreferences("WORD_SCORES", Context.MODE_PRIVATE);
        //get the name from game over layout
        EditText editText =(EditText)findViewById(R.id.editTextName);
        //set to string
        String name = editText.getText().toString();

        //start the preference editor
        SharedPreferences.Editor editor=preferences.edit();
        //get the previous score fromthe key
        String previousScores=preferences.getString("SCORES","");
        Log.d("MYLOG","Presvious Score: "+ previousScores);
        //key = score  value = containing string
        editor.putString("SCORES",name +" "+" " + playerPoints+" POINTS\n "+previousScores);
        //save bufer
        editor.commit();
        //name x points ]n name2 y points
        Toast.makeText(this,"Scored saved ",Toast.LENGTH_SHORT).show();
        editText.setText("");
        //finish



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_call_png:
                newGame ();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void newGame(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

}
