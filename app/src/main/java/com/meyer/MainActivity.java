package com.meyer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    boolean down = false;
    View RollButton;
    private boolean rolled = false;
    private View SendOnButton;
    private boolean rolledtwice;
    private AdView mAdView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RollButton = findViewById(R.id.button);
        RollButton.setVisibility(View.GONE);
        RollButton.setClickable(false);

        SendOnButton = findViewById(R.id.sendOn);
        SendOnButton.setVisibility(View.GONE);
        SendOnButton.setClickable(false);
        MobileAds.initialize(this, "ca-app-pub-6648370217691523~8925994957");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final ImageView diceCup = findViewById(R.id.diceCup);
        diceCup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if (!down) {
                            MoveCupDown();
                        } else {
                            MoveCupUp();
                        }
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void MoveCupDown() {
        ImageView diceCup = findViewById(R.id.diceCup);

        ObjectAnimator animX = ObjectAnimator.ofFloat(diceCup, "x", 0);
        ObjectAnimator animY = ObjectAnimator.ofFloat(diceCup, "y", -130);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.setDuration(0);
        animSetXY.start();
        RollButton.setVisibility(View.VISIBLE);
        RollButton.setClickable(true);
        down = true;
        if (rolled) {
            SendOnButton.setVisibility(View.GONE);
            SendOnButton.setClickable(false);

        }
    }

    private void MoveCupUp() {
        if (rolledtwice) return;
        ImageView diceCup = findViewById(R.id.diceCup);

        ObjectAnimator animX = ObjectAnimator.ofFloat(diceCup, "x", 0);
        ObjectAnimator animY = ObjectAnimator.ofFloat(diceCup, "y", -800);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.setDuration(0);
        animSetXY.start();
        RollButton.setVisibility(View.GONE);
        RollButton.setClickable(false);
        SendOnButton.setVisibility(View.GONE);
        SendOnButton.setClickable(false);

        down = false;
    }

    /**
     * Called when the user taps the Roll button
     */
    public void Roll(View view) {
        if (!down) return;
        if (rolled) {
            rolledtwice = true;
        }
        TextView roll1TextView = findViewById(R.id.roll1);
        TextView roll2TextView = findViewById(R.id.roll2);

        ImageView roll1ImageView = findViewById(R.id.die1);
        ImageView roll2ImageView = findViewById(R.id.die2);

        int roll1 = new Random().nextInt(6) + 1;
        int roll2 = new Random().nextInt(6) + 1;
        if (roll2 > roll1) {
            int temp = roll1;
            roll1 = roll2;
            roll2 = temp;
        }
        switch (roll1) {
            case 1:
                roll1ImageView.setImageResource(R.drawable.ic_dice_six_faces_one);
                break;
            case 2:
                roll1ImageView.setImageResource(R.drawable.ic_dice_six_faces_two);
                break;
            case 3:
                roll1ImageView.setImageResource(R.drawable.ic_dice_six_faces_three);
                break;
            case 4:
                roll1ImageView.setImageResource(R.drawable.ic_dice_six_faces_four);
                break;
            case 5:
                roll1ImageView.setImageResource(R.drawable.ic_dice_six_faces_five);
                break;
            case 6:
                roll1ImageView.setImageResource(R.drawable.ic_dice_six_faces_six);
                break;
            default:
                break;
        }
        switch (roll2) {
            case 1:
                roll2ImageView.setImageResource(R.drawable.ic_dice_six_faces_one);
                break;
            case 2:
                roll2ImageView.setImageResource(R.drawable.ic_dice_six_faces_two);
                break;
            case 3:
                roll2ImageView.setImageResource(R.drawable.ic_dice_six_faces_three);
                break;
            case 4:
                roll2ImageView.setImageResource(R.drawable.ic_dice_six_faces_four);
                break;
            case 5:
                roll2ImageView.setImageResource(R.drawable.ic_dice_six_faces_five);
                break;
            case 6:
                roll2ImageView.setImageResource(R.drawable.ic_dice_six_faces_six);
                break;
            default:
                break;
        }

        roll1TextView.setText(String.format(Locale.ENGLISH, "%d", roll1));
        roll2TextView.setText(String.format(Locale.ENGLISH, "%d", roll2));
        RollButton.setVisibility(View.GONE);
        RollButton.setClickable(false);
        SendOnButton.setVisibility(View.VISIBLE);
        SendOnButton.setClickable(true);
        rolled = true;
    }

    public void SendOn(View view) {
        SendOnButton.setVisibility(View.GONE);
        SendOnButton.setClickable(false);
        RollButton.setVisibility(View.VISIBLE);
        RollButton.setClickable(true);
        rolled = false;
        rolledtwice = false;
    }


}
