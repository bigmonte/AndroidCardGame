package com.portalmafia.androidcardgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {


    private static ArrayList<Activity> activities = new ArrayList<>();

    ImageView cardLeftImage, cardRightImage;
    TextView scoreLeft, scoreRight;
    Button button_deal;
    Button button_restart;
    String gameCompetitionStatus = "competing";

    Random r;
    int maxScore = 5;


    int leftScore, rightScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.activity_main);


        cardLeftImage =   findViewById(R.id.cardLeftImage);
        cardRightImage =  findViewById(R.id.cardRightImage);
        scoreLeft =  findViewById(R.id.scoreLeft);
        scoreRight = findViewById(R.id.scoreRight);
        button_deal = findViewById(R.id.button_deal);
        button_restart = findViewById(R.id.button_restart);

        r = new Random();

        button_deal.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                // generate the two card numbers
                int leftCard = r.nextInt(10) +2; // this is for cards 2 - 11
                int rightCard = r.nextInt(10) +2; // this is for cards 2 - 11



                // Draw card images, the int identifies the score of the card
                // and also the card number in file name

                int leftImage = getDrawableCard(leftCard);
                cardLeftImage.setImageResource(leftImage);

                int rightImage = getDrawableCard(rightCard);
                cardRightImage.setImageResource(rightImage);


                // compare cards, add points and update score
                if (leftCard > rightCard)
                {
                    leftScore++;
                    setScoreLeft(String.valueOf(leftScore));
                    getToast("You lost this battle!").show();

                }
                else if(leftCard < rightCard)
                {
                    rightScore++;
                    setScoreRight(String.valueOf(rightScore));
                    getToast("You win this battle!").show();



                } else if (leftCard == rightCard)
                {
                    getToast("Draw Battle").show();
                }

                // Check competition

                if (leftScore == maxScore && rightScore == maxScore)
                {
                    gameCompetitionStatus ="draw";
                }

                else if (leftScore >= maxScore )
                {
                    gameCompetitionStatus ="left_wins";
                }

                else if (rightScore >= maxScore)
                {
                    gameCompetitionStatus ="right_wins";
                }

                if(gameCompetitionStatus.equals("draw"))
                {
                    hideThingsMakeRestartButtonVisible();
                    setScoreLeft("Draw!!");

                }

               else  if(gameCompetitionStatus.equals("left_wins"))
                {
                    hideThingsMakeRestartButtonVisible();
                    setScoreLeft("Bot Wins!!");

                }
                else if(gameCompetitionStatus.equals("right_wins"))
                {
                    hideThingsMakeRestartButtonVisible();
                    scoreLeft.setText(R.string.congrats);

                }

            }
            private void hideThingsMakeRestartButtonVisible()
            {
                scoreRight.setVisibility(View.GONE);
                cardLeftImage.setVisibility(View.GONE);
                cardRightImage.setVisibility(View.GONE);
                button_deal.setVisibility(View.GONE);
                button_restart.setVisibility(View.VISIBLE);
            }



        });

        // Listen Restart button which will restart the game

        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });


    }

    private int getDrawableCard(int card) {
        return getResources().getIdentifier("card" + card, "drawable", getPackageName());
    }

    private void setScoreRight(String s ) {
        scoreRight.setText(String.valueOf(s));
    }

    private void setScoreLeft(String s) {
        scoreLeft.setText(s);
    }

    private Toast getToast(String s) {
        return Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
    }

    private void restartGame() {

        for (Activity activity: activities)
            activity.recreate();


    }
}
