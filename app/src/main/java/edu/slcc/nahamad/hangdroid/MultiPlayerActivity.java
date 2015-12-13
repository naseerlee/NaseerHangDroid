package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static edu.slcc.nahamad.hangdroid.R.id.LayoutLetters;

public class MultiPlayerActivity extends Activity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_player);

        //connect to xml
        editText=(EditText)findViewById(R.id.editTextWord);
        //addlisterner
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public  void playMultiPlayerGame(View view)
    {
        //get the text from xml word
        EditText editText=(EditText)findViewById(R.id.editTextWord);
        //cast the word to string that will be word to guess
        String wordToGuess = editText.getText().toString();
        //debug
        Log.d("MYLOG", "Multiplayer word" + wordToGuess);
        //creat the three steps
        //create intent
        Intent intent=new Intent(this,GameMultiActivity.class);
        //send the word with intent
        intent.putExtra("GuessID",wordToGuess);
        //Start activity
        startActivity(intent);
    }
    public  void showMultiPlayerGame(View view)
    {
        //get the text from xml word
        EditText editText=(EditText)findViewById(R.id.editTextWord);
        //cast the word to string that will be word to guess
        String wordToGuess = editText.getText().toString();

        Intent intent=new Intent(this,GameOverActivity.class);
        //send the word with intent
        intent.putExtra("GuessID",wordToGuess);
        //Start activity
        startActivity(intent);
    }

}//end of class
