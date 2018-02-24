package com.example.bandeng.picuga;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Game extends AppCompatActivity {

    ImageView[] pzl;
    LinearLayout[] drop;
    Button buttonBack;
    ProgressBar timeLeft;
    GlobalFunction gf;

    SharedPreferences gamePref;
    final String SharedPrefFile = "com.example.bandeng.picuga";

    TextView timerTextView;
    long startTime = 0;
    int time = 300;
    boolean backflag = false;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            time--;
            if(time==0) {
                SharedPreferences.Editor gamePrefEdit = gamePref.edit();
                gamePrefEdit.putInt("SCORE", score);
                gamePrefEdit.apply();
                boolean win = checkWin(drop, pzl);
                if(!win && backflag) {
                    finish();
                }
                else if(!win) {
                    startActivity(new Intent(Game.this, LossActivity.class));
                    finish();
                }
                if(win) {
                    finish();
                }
            }
            timerHandler.postDelayed(this, 500);
            timeLeft.setProgress(time);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gf = new GlobalFunction(getApplicationContext());
        timeLeft = findViewById(R.id.progressBar);
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);

        gamePref = getSharedPreferences(SharedPrefFile, MODE_PRIVATE);
        pzl = new ImageView[] {
                findViewById(R.id.pzl_1),
                findViewById(R.id.pzl_2),
                findViewById(R.id.pzl_3),
                findViewById(R.id.pzl_4),
                findViewById(R.id.pzl_5),
                findViewById(R.id.pzl_6),
                findViewById(R.id.pzl_7),
                findViewById(R.id.pzl_8),
                findViewById(R.id.pzl_9),
                findViewById(R.id.pzl_10),
                findViewById(R.id.pzl_11),
                findViewById(R.id.pzl_12),
        };
        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        drop = new LinearLayout[] {
                findViewById(R.id.drop1),
                findViewById(R.id.drop2),
                findViewById(R.id.drop3),
                findViewById(R.id.drop4),
                findViewById(R.id.drop5),
                findViewById(R.id.drop6),
                findViewById(R.id.drop7),
                findViewById(R.id.drop8),
                findViewById(R.id.drop9),
                findViewById(R.id.drop10),
                findViewById(R.id.drop11),
                findViewById(R.id.drop12),
                findViewById(R.id.drop13),
                findViewById(R.id.drop14),
                findViewById(R.id.drop15),
                findViewById(R.id.drop16),
                findViewById(R.id.drop17),
                findViewById(R.id.drop18),
                findViewById(R.id.drop19),
                findViewById(R.id.drop20),
                findViewById(R.id.drop21),
                findViewById(R.id.drop22),
                findViewById(R.id.drop23),
                findViewById(R.id.drop24),
                findViewById(R.id.drop25),
                findViewById(R.id.drop26),
                findViewById(R.id.drop27),
                findViewById(R.id.drop28),
                findViewById(R.id.drop29),
                findViewById(R.id.drop30),
        };

        int[] pzl_board;
        switch(gamePref.getInt("DIFFICULTY_TIME", 120)) {
            case 120:
                pzl_board = new int[] {0,1,2,3,4,5,6,11,12,17,18,23,24,25,26,27,28,29};
                break;
            case 60:
                pzl_board = new int[] {0,1,2,3,4,5,6,7,10,11,12,13,16,17,18,19,22,23,24,25,26,27,28,29};
                break;
            case 30:
                pzl_board = new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,22,23,24,25,26,27,28,29};
                break;
            default:
                pzl_board = new int[] {0,1,2,3,4,5,6,11,12,17,18,23,24,25,26,27,28,29};
                break;
        }

        for (ImageView pieces : pzl) {
            Random r = new Random();
            int index = r.nextInt(pzl_board.length);
            for (int i = 0; i<10; i++) {
                index = r.nextInt(pzl_board.length);
            }
            changePieceLocatoin(pieces, drop[pzl_board[index]]);
        }

        buttonBack = findViewById(R.id.button_back);
        View decorView = getWindow().getDecorView();


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gf.playBubble();
                backflag = true;
                time = 1;
            }
        });
        time = gamePref.getInt("DIFFICULTY_TIME", 60);
        timeLeft.setMax(time);

        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        for(LinearLayout dropThis : drop ) {
            dropThis.setOnDragListener(dropHere);
        }
        for(ImageView pzlTouch : pzl) {
            pzlTouch.setOnTouchListener(touchHere);
        }

        rootLayout.setOnDragListener(backgroundCatch);

    }

    void changePieceLocatoin(ImageView piece, LinearLayout location) {
        ViewGroup originOwner = (ViewGroup)piece.getParent();
        View existingView;
        originOwner.removeView(piece);
        if(location.getChildCount()>0) {
            existingView = location.getChildAt(0);
            location.removeView(existingView);
            originOwner.addView(existingView);
        }
        location.addView(piece);
    }

    View.OnTouchListener touchHere = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN) {
                ClipData clipData = ClipData.newPlainText("","");
                View.DragShadowBuilder dsb = new View.DragShadowBuilder(view);
                view.startDrag(clipData, dsb, view,0);
                view.setVisibility(View.INVISIBLE);
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
                return true;
            }
            else if(motionEvent.getAction()==MotionEvent.ACTION_UP) {
                view.setVisibility(View.VISIBLE);
                view.performClick();
                return false;
            }
            else {
                return false;
            }
        }
    };

    View.OnDragListener dropHere = new View.OnDragListener() {
        @Override
        public boolean onDrag(View destinationView, DragEvent dragEvent) {
            if(dragEvent.getAction()==DragEvent.ACTION_DROP) {
                View dragView = (View)dragEvent.getLocalState();
                ViewGroup originOwner = (ViewGroup) dragView.getParent();
                originOwner.removeView(dragView);
                View existingView;
                if(((LinearLayout)destinationView).getChildCount()>0) {
                    existingView = ((LinearLayout)destinationView).getChildAt(0);
                    ((LinearLayout)destinationView).removeView(existingView);
                    originOwner.addView(existingView);
                    ((LinearLayout)originOwner).setGravity(Gravity.CENTER);
                }
                ((LinearLayout)destinationView).addView(dragView);
                ((LinearLayout)destinationView).setGravity(Gravity.CENTER);
                dragView.setVisibility(View.VISIBLE);
                boolean win = checkWin(drop,pzl);
                if(win) {
                    SharedPreferences.Editor gamePrefEdit = gamePref.edit();
                    gamePrefEdit.putInt("SCORE", score);
                    gamePrefEdit.apply();
                    startActivity(new Intent(getApplicationContext(), LevelCompleteActivity.class));
                    time=1;
                }
            }
            return true;
        }
    };

    View.OnDragListener backgroundCatch = new View.OnDragListener() {
        @Override
        public boolean onDrag(View destinationView, DragEvent dragEvent) {
            if(dragEvent.getAction()==DragEvent.ACTION_DROP) {
                View dragView = (View)dragEvent.getLocalState();
                dragView.setVisibility(View.VISIBLE);
            }
            return true;
        }
    };

    int score = 0;

    boolean checkWin(LinearLayout[] drop, ImageView[] pzl) {
        int count = 0;
        if(drop[7].getChildCount()>0 && drop[7].getChildAt(0)==pzl[0]) {
            count++;
        }
        if (drop[8].getChildCount() > 0 && drop[8].getChildAt(0)==pzl[1]) {
            count++;
        }
        if (drop[9].getChildCount() > 0 && drop[9].getChildAt(0)==pzl[2]) {
            count++;
        }
        if (drop[10].getChildCount() > 0 && drop[10].getChildAt(0)==pzl[3]) {
            count++;
        }
        if (drop[13].getChildCount() > 0 && drop[13].getChildAt(0)==pzl[4]) {
            count++;
        }
        if (drop[14].getChildCount() > 0 && drop[14].getChildAt(0)==pzl[5]) {
            count++;
        }
        if (drop[15].getChildCount() > 0 && drop[15].getChildAt(0)==pzl[6]) {
            count++;
        }
        if (drop[16].getChildCount() > 0 && drop[16].getChildAt(0)==pzl[7]) {
            count++;
        }
        if (drop[19].getChildCount() > 0 && drop[19].getChildAt(0)==pzl[8]) {
            count++;
        }
        if (drop[20].getChildCount() > 0 && drop[20].getChildAt(0)==pzl[9]) {
            count++;
        }
        if (drop[21].getChildCount() > 0 && drop[21].getChildAt(0)==pzl[10]) {
            count++;
        }
        if (drop[22].getChildCount() > 0 && drop[22].getChildAt(0)==pzl[11]) {
            count++;
        }
        score = (count*100)/12;
        if(score>=100) {
            return true;
        }
        else return false;
    }
}