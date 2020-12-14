package com.armcomptech.akash.tictactoe;

import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{
    Animation animFadeOut;
    private FirebaseAnalytics mFirebaseAnalytics;

    private int turns = 0; //keeps track of how many valid turns are taken
    private int waitTime = 901;

    private TextView mTextViewPlayerOneScore;
    private TextView mTextViewPlayerTwoScore;

    //buttons
    private Button mButtonReset;
    private Button mButtonStart;

    private ImageView mPlayerOneLogo;
    private ImageView mPlayerTwoLogo;

    private ImageButton mButtonTopLeft;
    private ImageButton mButtonTopMid;
    private ImageButton mButtonTopRight;
    private ImageButton mButtonMidLeft;
    private ImageButton mButtonMidMid;
    private ImageButton mButtonMidRight;
    private ImageButton mButtonBottomLeft;
    private ImageButton mButtonBottomMid;
    private ImageButton mButtonBottomRight;


    //keeps track of the score of both players
    private int playerOneScore;
    private int playerTwoScore;

    private int playerTurn ; //player 1 goes first

    private char topLeft = ' ';
    private char topMiddle = ' ';
    private char topRight = ' ';
    private char midLeft = ' ';
    private char midMiddle = ' ';
    private char midRight = ' ';
    private char lowLeft = ' ';
    private char lowMiddle = ' ';
    private char lowRight = ' ';

    //TODO: Change disableFirebaseLogging to false when releasing
    public static Boolean disableFirebaseLogging = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        if (!disableFirebaseLogging) {
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "App Opened");
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
        }

        animFadeOut = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fadeout);
        animFadeOut.setAnimationListener(this);

        playerTurn = 1;

        playerOneScore = 0;
        playerTwoScore = 0;

        mPlayerOneLogo = findViewById(R.id.playerOneLogo);
        mPlayerTwoLogo = findViewById(R.id.playerTwoLogo);

        mTextViewPlayerOneScore = findViewById(R.id.playerOneScore);
        mTextViewPlayerTwoScore = findViewById(R.id.playerTwoScore);

        mButtonReset = findViewById(R.id.reset_button);
        mButtonStart = findViewById(R.id.start_button);
        mButtonTopLeft = findViewById(R.id.topLeft);
        mButtonTopMid = findViewById(R.id.topMid);
        mButtonTopRight = findViewById(R.id.topRight);
        mButtonMidLeft = findViewById(R.id.midLeft);
        mButtonMidMid = findViewById(R.id.midMid);
        mButtonMidRight = findViewById(R.id.midRight);
        mButtonBottomLeft = findViewById(R.id.bottomLeft);
        mButtonBottomMid = findViewById(R.id.bottomMid);
        mButtonBottomRight = findViewById(R.id.bottomRight);

        mTextViewPlayerOneScore.setText("Player One: " + playerOneScore);
        mTextViewPlayerTwoScore.setText("Player Two: " + playerTwoScore);

        mPlayerTwoLogo.setVisibility(View.INVISIBLE);

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Resetting the Game",Toast.LENGTH_SHORT).show();
                resetScore();
                resetBoard();
            }
        });

        mButtonTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topLeft != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonTopLeft.setImageResource(R.drawable.circle);
                    topLeft = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonTopLeft.setImageResource(R.drawable.cross);
                    topLeft = 'X';
                }

                checkForMatch();
            }
        });

        mButtonTopMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topMiddle != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonTopMid.setImageResource(R.drawable.circle);
                    topMiddle = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonTopMid.setImageResource(R.drawable.cross);
                    topMiddle = 'X';
                }

                checkForMatch();
            }
        });

        mButtonTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topRight != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonTopRight.setImageResource(R.drawable.circle);
                    topRight = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonTopRight.setImageResource(R.drawable.cross);
                    topRight = 'X';
                }

                checkForMatch();
            }
        });

        mButtonMidLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (midLeft != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonMidLeft.setImageResource(R.drawable.circle);
                    midLeft = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonMidLeft.setImageResource(R.drawable.cross);
                    midLeft = 'X';
                }

                checkForMatch();
            }
        });

        mButtonMidMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (midMiddle != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonMidMid.setImageResource(R.drawable.circle);
                    midMiddle = 'O';
                }
                else{
                    playerTurn = 1; //alternate
                    mButtonMidMid.setImageResource(R.drawable.cross);
                    midMiddle = 'X';
                }

                checkForMatch();
            }
        });

        mButtonMidRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (midRight != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonMidRight.setImageResource(R.drawable.circle);
                    midRight = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonMidRight.setImageResource(R.drawable.cross);
                    midRight = 'X';
                }

                checkForMatch();
            }
        });

        mButtonBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowLeft != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonBottomLeft.setImageResource(R.drawable.circle);
                    lowLeft = 'O';
                }
                else{
                    playerTurn = 1; //alternate
                    mButtonBottomLeft.setImageResource(R.drawable.cross);
                    lowLeft = 'X';
                }

                checkForMatch();
            }
        });

        mButtonBottomMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowMiddle != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonBottomMid.setImageResource(R.drawable.circle);
                    lowMiddle = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonBottomMid.setImageResource(R.drawable.cross);
                    lowMiddle = 'X';
                }

                checkForMatch();
            }
        });

        mButtonBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowRight != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mButtonBottomRight.setImageResource(R.drawable.circle);
                    lowRight = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mButtonBottomRight.setImageResource(R.drawable.cross);
                    lowRight = 'X';
                }

                checkForMatch();
            }
        });
    }

    //increase player one score by one
    public void incrementPlayerOneScore() {
        playerOneScore++;
        mTextViewPlayerOneScore.setText("Player One: " + playerOneScore);
    }

    //increase player two score by one
    public void incrementPlayerTwoScore() {
        playerTwoScore++;
        mTextViewPlayerTwoScore.setText("Player Two: " + playerTwoScore);
    }

    public void updatePlayerLogoVisibility() {
        if (playerTurn == 1) {
            mPlayerOneLogo.setVisibility(View.VISIBLE);
            mPlayerTwoLogo.setVisibility(View.INVISIBLE);
        } else {
            mPlayerOneLogo.setVisibility(View.INVISIBLE);
            mPlayerTwoLogo.setVisibility(View.VISIBLE);
        }
    }

    //reset the score of both players
    public void resetScore() {
        playerOneScore = 0;
        playerTwoScore = 0;
        mTextViewPlayerOneScore.setText("Player One: " + playerOneScore);
        mTextViewPlayerTwoScore.setText("Player Two: " + playerTwoScore);
    }

    //reset the graphics of the board
    public void resetBoard() {
        turns = 0;

        Runnable r = new Runnable() {
            @Override
            public void run(){
                resetBoardNoAnimation();
            }
        };

        mButtonTopLeft.startAnimation(animFadeOut);
        mButtonTopMid.startAnimation(animFadeOut);
        mButtonTopRight.startAnimation(animFadeOut);
        mButtonMidLeft.startAnimation(animFadeOut);
        mButtonMidMid.startAnimation(animFadeOut);
        mButtonMidRight.startAnimation(animFadeOut);
        mButtonBottomLeft.startAnimation(animFadeOut);
        mButtonBottomMid.startAnimation(animFadeOut);
        mButtonBottomRight.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);
    }

    public void resetBoardNoAnimation() {
        turns = 0;
        playerTurn = 1; //player one starts once again

        mPlayerOneLogo.setVisibility(View.VISIBLE);
        mPlayerTwoLogo.setVisibility(View.INVISIBLE);

        topLeft = ' ';
        topMiddle = ' ';
        topRight = ' ';
        midLeft = ' ';
        midMiddle = ' ';
        midRight = ' ';
        lowLeft = ' ';
        lowMiddle = ' ';
        lowRight = ' ';

        mButtonTopLeft.setImageResource(R.mipmap.white);
        mButtonTopMid.setImageResource(R.mipmap.white);
        mButtonTopRight.setImageResource(R.mipmap.white);
        mButtonMidLeft.setImageResource(R.mipmap.white);
        mButtonMidMid.setImageResource(R.mipmap.white);
        mButtonMidRight.setImageResource(R.mipmap.white);
        mButtonBottomLeft.setImageResource(R.mipmap.white);
        mButtonBottomMid.setImageResource(R.mipmap.white);
        mButtonBottomRight.setImageResource(R.mipmap.white);
    }

    //make scribble sound when updating the score

    public void checkForMatch() {
        turns++;

        updatePlayerLogoVisibility(); //update to show whos turn it is

        if ( checkLeftVert() ||
                checkMiddleVert() ||
                checkRightVert() ||
                checkTopHorz() ||
                checkMiddleHorz() ||
                checkLowHorz() ||
                checkTopLeftDiag() ||
                checkTopRightDiag()) {
            return;
        }

        if (turns == 9) {
            Toast.makeText(getApplicationContext(),"It's a Draw!",Toast.LENGTH_SHORT).show();
            resetBoard();
        }
    }

    public boolean checkLeftVert() {
        if ((topLeft == ' ') || (midLeft == ' ') || (lowLeft == ' ')) {
            return false;
        }
        char temp = topLeft;
        if (midLeft != temp) {
            return false;
        }

        if (lowLeft != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonTopMid.setImageResource(R.mipmap.white);
        mButtonTopRight.setImageResource(R.mipmap.white);
        mButtonMidMid.setImageResource(R.mipmap.white);
        mButtonMidRight.setImageResource(R.mipmap.white);
        mButtonBottomMid.setImageResource(R.mipmap.white);
        mButtonBottomRight.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonTopLeft.startAnimation(animFadeOut);
        mButtonMidLeft.startAnimation(animFadeOut);
        mButtonBottomLeft.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkMiddleVert() {
        if ((topMiddle == ' ') || (midMiddle == ' ') || (lowMiddle == ' ')) {
            return false;
        }
        char temp = topMiddle;
        if (midMiddle != temp) {
            return false;
        }

        if (lowMiddle != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonTopLeft.setImageResource(R.mipmap.white);
        mButtonTopRight.setImageResource(R.mipmap.white);
        mButtonMidLeft.setImageResource(R.mipmap.white);
        mButtonMidRight.setImageResource(R.mipmap.white);
        mButtonBottomLeft.setImageResource(R.mipmap.white);
        mButtonBottomRight.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonTopMid.startAnimation(animFadeOut);
        mButtonMidMid.startAnimation(animFadeOut);
        mButtonBottomMid.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkRightVert() {
        if ((topRight == ' ') || (midRight == ' ') || (lowRight == ' ')) {
            return false;
        }
        char temp = topRight;
        if (midRight != temp) {
            return false;
        }

        if (lowRight != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonTopLeft.setImageResource(R.mipmap.white);
        mButtonTopMid.setImageResource(R.mipmap.white);
        mButtonMidLeft.setImageResource(R.mipmap.white);
        mButtonMidMid.setImageResource(R.mipmap.white);
        mButtonBottomLeft.setImageResource(R.mipmap.white);
        mButtonBottomMid.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonTopRight.startAnimation(animFadeOut);
        mButtonMidRight.startAnimation(animFadeOut);
        mButtonBottomRight.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkTopHorz() {
        if ((topLeft == ' ') || (topMiddle == ' ') || (topRight == ' ')) {
            return false;
        }
        char temp = topLeft;
        if (topMiddle != temp) {
            return false;
        }

        if (topRight != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonMidLeft.setImageResource(R.mipmap.white);
        mButtonMidMid.setImageResource(R.mipmap.white);
        mButtonMidRight.setImageResource(R.mipmap.white);
        mButtonBottomLeft.setImageResource(R.mipmap.white);
        mButtonBottomMid.setImageResource(R.mipmap.white);
        mButtonBottomRight.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonTopLeft.startAnimation(animFadeOut);
        mButtonTopMid.startAnimation(animFadeOut);
        mButtonTopRight.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkMiddleHorz() {
        if ((midLeft == ' ') || (midMiddle == ' ') || (midRight == ' ')) {
            return false;
        }
        char temp = midLeft;
        if (midMiddle != temp) {
            return false;
        }

        if (midRight != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonTopLeft.setImageResource(R.mipmap.white);
        mButtonTopMid.setImageResource(R.mipmap.white);
        mButtonTopRight.setImageResource(R.mipmap.white);
        mButtonBottomLeft.setImageResource(R.mipmap.white);
        mButtonBottomMid.setImageResource(R.mipmap.white);
        mButtonBottomRight.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonMidLeft.startAnimation(animFadeOut);
        mButtonMidMid.startAnimation(animFadeOut);
        mButtonMidRight.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkLowHorz() {
        if ((lowLeft == ' ') || (lowMiddle == ' ') || (lowRight == ' ')) {
            return false;
        }
        char temp = lowLeft;
        if (lowMiddle != temp) {
            return false;
        }

        if (lowRight != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonTopLeft.setImageResource(R.mipmap.white);
        mButtonTopMid.setImageResource(R.mipmap.white);
        mButtonTopRight.setImageResource(R.mipmap.white);
        mButtonMidLeft.setImageResource(R.mipmap.white);
        mButtonMidMid.setImageResource(R.mipmap.white);
        mButtonMidRight.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonBottomLeft.startAnimation(animFadeOut);
        mButtonBottomMid.startAnimation(animFadeOut);
        mButtonBottomRight.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkTopLeftDiag() {
        if ((topLeft == ' ') || (midMiddle == ' ') || (lowRight == ' ')) {
            return false;
        }
        char temp = topLeft;
        if (midMiddle != temp) {
            return false;
        }

        if (lowRight != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player One Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }

        Runnable r = new Runnable() {
            @Override
            public void run() {
                resetBoardNoAnimation();
            }
        };

        //setEverything else to blank
        mButtonTopMid.setImageResource(R.mipmap.white);
        mButtonTopRight.setImageResource(R.mipmap.white);
        mButtonMidLeft.setImageResource(R.mipmap.white);
        mButtonMidRight.setImageResource(R.mipmap.white);
        mButtonBottomLeft.setImageResource(R.mipmap.white);
        mButtonBottomMid.setImageResource(R.mipmap.white);

        //animate fade out
        mButtonTopLeft.startAnimation(animFadeOut);
        mButtonMidMid.startAnimation(animFadeOut);
        mButtonBottomRight.startAnimation(animFadeOut);
        Handler h = new Handler();
        h.postDelayed(r, waitTime);

        return true;
    }

    public boolean checkTopRightDiag() {
        if ((topRight == ' ') || (midMiddle == ' ') || (lowLeft == ' ')) {
            return false;
        }
        char temp = topRight;
        if (midMiddle != temp) {
            return false;
        }

        if (lowLeft != temp) {
            return false;
        }

        //match found
        if (temp == 'O') {
            Toast.makeText(getApplicationContext(),"Player one Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerOneScore();
        } else {
            Toast.makeText(getApplicationContext(),"Player Two Wins!",Toast.LENGTH_SHORT).show();
            incrementPlayerTwoScore();
        }
        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
