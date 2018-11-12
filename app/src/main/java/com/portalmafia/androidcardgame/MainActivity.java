package com.portalmafia.androidcardgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    private static ArrayList<Activity> activities = new ArrayList<>();
    Class[] ActivitiesClass = new Class[3];
    Intent[] intents = new Intent[3];

    ImageView cardLeftImage, cardRightImage;
    Button buttonNews, buttonPlayerInfo, buttonSettings;
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
        ActivitiesClass[1] = PlayerInfoActivity.class;
        ActivitiesClass[2] = SettingsActivity.class;

        //buttonBattle.setTypeface(face);

        cardLeftImage = findViewById(R.id.cardLeftImage);
        cardRightImage = findViewById(R.id.cardRightImage);

        buttonNews = findViewById(R.id.buttoneNews);
        buttonPlayerInfo = findViewById(R.id.buttonPlayerInfo);
        buttonSettings = findViewById(R.id.buttonSettings);

        font = Typeface.createFromAsset(getAssets(), "fonts/curse.ttf");


        r = new Random();

        buttonNews.setTypeface(font);
        buttonPlayerInfo.setTypeface(font);
        buttonSettings.setTypeface(font);


        // Listen Restart button which will restart the gam

        // 0 - News
        // 1 - Player Info
        // 2 - Settings

        ButtonActivityListener(buttonNews, 0);
        ButtonActivityListener(buttonPlayerInfo, 1);
       ButtonActivityListener(buttonSettings, 2);


    }

    private int getDrawableCard(int card) {
        return getResources().getIdentifier("card" + card, "drawable", getPackageName());
    }

    private Toast getToast(String s) {
        return Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT);
    }

    private void restartGame() {

        for (Activity activity : activities)
            activity.recreate();

    }

    private void ButtonActivityListener(Button buttonNews, final int activityIndex)
    {
        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity(v,activityIndex );
            }
        });
    }

    public void ChangeActivity(View view, int index)
    {
        intents[index] = new Intent();
        intents[index].setClass(this, ActivitiesClass[index]);
        startActivity(intents[index]);
    }
}
