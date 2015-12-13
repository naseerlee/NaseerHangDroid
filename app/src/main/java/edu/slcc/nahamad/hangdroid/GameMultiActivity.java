package edu.slcc.nahamad.hangdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameMultiActivity extends Activity {
    String theWord = "";
    int badLetterCount = 0;
    int goodLetterCount = 0;
    int points = 0;
    public final static String EXTRA_MESSAGE="Naseer Ahamad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_game);
        //setWord();
        //get the word from the intent
        String wordToGuess = "";
        wordToGuess = getIntent().getStringExtra("GuessID");
        //debug
        Log.d("MYLOG", "Word to Guess: " + wordToGuess);


        createTextViews(wordToGuess);
        theWord = wordToGuess;

    }

    private void createTextViews(String word) {
        //get the layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutLetters);

        //
        for (int i = 0; i < word.length(); i++) {

            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.single_letter, null);
            layout.addView(textView);
        }


    }


    // caled when clicks on button set in activity game xml
    public void newLetter(View view) {
        Toast.makeText(this, "newLetter", Toast.LENGTH_SHORT).show();

        //find the text box with a letter in it using its id then cast it
        EditText editText = (EditText) findViewById(R.id.editTextLetter);

        //set it to my string variable
        String letter = editText.getText().toString();

        //blank out tect field for next guess
        editText.setText("");

        //test i am getting the letter
        Log.d("MYLOG", "The letter is " + letter);

        //check the lenght of the letter
        if (letter.length() == 1) {
            checkLetter(letter);
        } else {
            Toast.makeText(this, "Please Enter single Letter", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkLetter(String letter) {
        Toast.makeText(this, "checkLetter: " + letter, Toast.LENGTH_SHORT).show();

        //phrase to char for camparison
        char aLetter = letter.charAt(0);

        //tracks if the letter was found in the word
        boolean letterGuessed = false;

        //loop to look for the new letter
        for (int i = 0; i < theWord.length(); i++) {
            if (theWord.charAt(i) == aLetter) {
                letterGuessed = true;
                Log.d("MYLOG", "Letter Found " + aLetter);
                letterGuessed = true;
                goodLetterCount++;
                showLetter(i, aLetter);
            }
        }//end for loop

        //letter not found in the word and add it to list
        if (!letterGuessed) {
            Log.d("MYLOG", "Letter Not Found: " + aLetter);
            badLetterCount++;

            //send the bad letter to string
            wrongLetter(Character.toString(aLetter));
        }

        letterGuessed = false;

        //won! if the guess count is equal to word length
        Log.d("MYLOG", "Check for Win: " + goodLetterCount + "" + theWord.length());

        if (goodLetterCount == theWord.length())

        {
            Toast.makeText(this, "you won!", Toast.LENGTH_SHORT).show();
            points++;
            gameOver();
        }
    }

    //wrong letter methode
    public void wrongLetter(String badLetter) {
        //make reference of the text view of wrong guessed letters in activity_game.xml
        TextView textView = (TextView) findViewById(R.id.textviewWrong);

        //save previous lettets
        String previousLetters = textView.getText().toString();

        //add or append
        Log.d("MYLOG", "Bad Letters: " + badLetter + "" + badLetterCount);
        if (badLetterCount > 1) {
            textView.setText(previousLetters + "" + badLetter);
        } else {
            textView.setText(badLetter);
        }

        //make a reference of the imageview in activity_game.xml using the r reference
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (badLetterCount == 1)
            imageView.setImageResource(R.drawable.droid_1);
        if (badLetterCount == 2)
            imageView.setImageResource(R.drawable.droid_2);
        if (badLetterCount == 3)
            imageView.setImageResource(R.drawable.droid_3);
        if (badLetterCount == 4)
            imageView.setImageResource(R.drawable.droid_4);
        if (badLetterCount == 4)
            imageView.setImageResource(R.drawable.droid_5);
        if (badLetterCount == 5) {
            Toast.makeText(this, "apple rule", Toast.LENGTH_LONG).show();

            gameLost();

        }
    }

   public void gameLost()
   {
       //creat intent
       Intent intent = new Intent(this, GameLost.class);
       //sent data with intent
       intent.putExtra(EXTRA_MESSAGE, theWord);
       //start activity
       startActivity(intent);
       clearScreen();

    }

    public void showLetter(int position, char letterGuessed) {
        //make a ref if the linear layout in the game activity xml using rh R reference
        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutLetters);
        //make a ref of the textview of the child within the layout that matches the position of the guessed letter
        TextView textView = (TextView) layout.getChildAt(position);
        //replace the "" of the text view of the guess letters to text view
        textView.setText(Character.toString(letterGuessed));
    }

    public void gameOver() {
        //creat intent
        Intent intent = new Intent(this, GameOverActivity.class);
        //sent data with intent
        intent.putExtra("PointsID", points);
        //start activity
        startActivity(intent);
        clearScreen();

        //new wordsetWord();
    }//game over

    

    public void clearScreen() {
        TextView textView = (TextView) findViewById(R.id.textviewWrong);
        textView.setText("Guessed!!!");
        //reset counter
        badLetterCount = 0;
        goodLetterCount = 0;
        //clear guessed word layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutLetters);
        //reset textview to "_"
        for (int i = 0; i < layout.getChildCount(); i++) {
            TextView childTextView = (TextView) layout.getChildAt(i);
            childTextView.setText("_");

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_refresh:
                hint();
                return true;
            case R.id.action_search_png:
                showScore();
              return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void hint() {

        LinearLayout layout = (LinearLayout) findViewById(R.id.LayoutLetters);
        Toast.makeText(this, "Cheat Letter: " + theWord.charAt(1), Toast.LENGTH_SHORT).show();
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.droid_1);


    }
    private void showScore(){
        Intent intent = new Intent(this, ScoresActivity.class);
        startActivity(intent);
    }


}
