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


    private static ArrayList<Activity> activites = new ArrayList<Activity>();

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
        activites.add(this);
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
                int leftCard = r.nextInt(13) +2; // this is for cards 2 - 14
                int rightCard = r.nextInt(13) +2; // this is for cards 2 - 14



                // show card images

                int leftImage = getResources().getIdentifier("card" + leftCard, "drawable", getPackageName());
                cardLeftImage.setImageResource(leftImage);

                int rightImage = getResources().getIdentifier("card" + rightCard, "drawable", getPackageName());
                cardRightImage.setImageResource(rightImage);


                // compare cards, add points and update score
                if (leftCard > rightCard)
                {
                    leftScore++;
                    scoreLeft.setText(String.valueOf(leftScore));

                }
                else if(leftCard < rightCard)
                {
                    rightScore++;
                    scoreRight.setText(String.valueOf(rightScore));


                } else if (leftCard == rightCard)
                {
                    Toast.makeText(MainActivity.this,"Draw Battle", Toast.LENGTH_SHORT).show();
                }

                // Win Gmae

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
                    scoreLeft.setText("Draw!!");

                }

               else  if(gameCompetitionStatus.equals("left_wins"))
                {
                    hideThingsMakeRestartButtonVisible();
                    scoreLeft.setText("Bot Wins!!");

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


        button_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });


    }
    private void restartGame() {

        for (Activity activity: activites)
            activity.recreate();


    }
}
