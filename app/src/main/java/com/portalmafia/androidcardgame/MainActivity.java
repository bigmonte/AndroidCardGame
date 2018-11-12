package com.portalmafia.androidcardgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
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
    Class[] ActivitiesClass = new Class[1];
    Intent[] intents = new Intent[1];

    ImageView cardLeftImage, cardRightImage;
    TextView scoreLeft, scoreRight;
    Button buttonBattle;
    Button buttonRestart;
    String gameCompetitionStatus = "competing";
    Typeface font;

    Random r;

    int maxScore = 7;
    int leftScore, rightScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.activity_main);
        ActivitiesClass[0] = NewsActivity.class;

        //buttonBattle.setTypeface(face);

        cardLeftImage = findViewById(R.id.cardLeftImage);
        cardRightImage = findViewById(R.id.cardRightImage);

        buttonBattle = findViewById(R.id.buttonBattle);

        font = Typeface.createFromAsset(getAssets(), "fonts/curse.ttf");


        r = new Random();

        buttonBattle.setTypeface(font);


        // Listen Restart button which will restart the gam
        openNewsActivity(buttonBattle);

    }

    private int getDrawableCard(int card) {
        return getResources().getIdentifier("card" + card, "drawable", getPackageName());
    }

    private void setScoreRight(String s) {
        scoreRight.setText(String.valueOf(s));
    }

    private void setScoreLeft(String s) {
        scoreLeft.setText(s);
    }

    private Toast getToast(String s) {
        return Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
    }

    private void restartGame() {

        for (Activity activity : activities)
            activity.recreate();

    }

    private void openNewsActivity(Button buttonNews)
    {
        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity(v);
            }
        });
    }

    public void ChangeActivity(View view)
    {
        intents[0] = new Intent();
        intents[0].setClass(this, ActivitiesClass[0]);
        startActivity(intents[0]);
    }
}
