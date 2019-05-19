package com.armcomptech.akash.tictactoe;

//test1

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{
    Animation animFadeOut;

    private int turns = 0; //keeps track of how many valid turns are taken
    private int waitTime = 901;

    private TextView mTextViewPlayerOneScore;
    private TextView mTextViewPlayerTwoScore;

    //buttons
    private Button mButtonReset;
    private Button mButtonTopLeft;
    private Button mButtonTopMiddle;
    private Button mButtonTopRight;
    private Button mButtonMidLeft;
    private Button mButtonMidMiddle;
    private Button mButtonMidRight;
    private Button mButtonLowLeft;
    private Button mButtonLowMiddle;
    private Button mButtonLowRight;

    private ImageView mPlayerOneLogo;
    private ImageView mPlayerTwoLogo;
    private ImageView mImageTopLeft;
    private ImageView mImageTopMiddle;
    private ImageView mImageTopRight;
    private ImageView mImageMidLeft;
    private ImageView mImageMidMiddle;
    private ImageView mImageMidRight;
    private ImageView mImageLowLeft;
    private ImageView mImageLowMiddle;
    private ImageView mImageLowRight;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animFadeOut = AnimationUtils.loadAnimation(getBaseContext(),R.anim.fadeout);
        animFadeOut.setAnimationListener(this);

        playerTurn = 1;

        playerOneScore = 0;
        playerTwoScore = 0;

        mPlayerOneLogo = findViewById(R.id.playerOneLogo);
        mPlayerTwoLogo = findViewById(R.id.playerTwoLogo);

        mTextViewPlayerOneScore = findViewById(R.id.playerOneScore);
        mTextViewPlayerTwoScore = findViewById(R.id.playerTwoScore);

        mButtonReset = findViewById(R.id.resetButton);
        mButtonTopLeft = findViewById(R.id.topLeftButton);
        mButtonTopMiddle = findViewById(R.id.topMiddleButton);
        mButtonTopRight = findViewById(R.id.topRightButton);
        mButtonMidLeft = findViewById(R.id.midLeftButton);
        mButtonMidMiddle = findViewById(R.id.midMiddleButton);
        mButtonMidRight = findViewById(R.id.midRightButton);
        mButtonLowLeft = findViewById(R.id.lowLeftButton);
        mButtonLowMiddle = findViewById(R.id.lowMiddleButton);
        mButtonLowRight = findViewById(R.id.lowRightButton);

        mImageTopLeft = findViewById(R.id.topLeftImage);
        mImageTopMiddle = findViewById(R.id.topMiddleImage);
        mImageTopRight = findViewById(R.id.topRightImage);
        mImageMidLeft = findViewById(R.id.midLeftImage);
        mImageMidMiddle = findViewById(R.id.midMiddleImage);
        mImageMidRight = findViewById(R.id.midRightImage);
        mImageLowLeft = findViewById(R.id.lowLeftImage);
        mImageLowMiddle = findViewById(R.id.lowMiddleImage);
        mImageLowRight = findViewById(R.id.lowRightImage);

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
                    mImageTopLeft.setImageResource(R.drawable.circle);
                    topLeft = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageTopLeft.setImageResource(R.drawable.cross);
                    topLeft = 'X';
                }

                checkForMatch();
            }
        });

        mButtonTopMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (topMiddle != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mImageTopMiddle.setImageResource(R.drawable.circle);
                    topMiddle = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageTopMiddle.setImageResource(R.drawable.cross);
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
                    mImageTopRight.setImageResource(R.drawable.circle);
                    topRight = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageTopRight.setImageResource(R.drawable.cross);
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
                    mImageMidLeft.setImageResource(R.drawable.circle);
                    midLeft = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageMidLeft.setImageResource(R.drawable.cross);
                    midLeft = 'X';
                }

                checkForMatch();
            }
        });

        mButtonMidMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (midMiddle != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mImageMidMiddle.setImageResource(R.drawable.circle);
                    midMiddle = 'O';
                }
                else{
                    playerTurn = 1; //alternate
                    mImageMidMiddle.setImageResource(R.drawable.cross);
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
                    mImageMidRight.setImageResource(R.drawable.circle);
                    midRight = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageMidRight.setImageResource(R.drawable.cross);
                    midRight = 'X';
                }

                checkForMatch();
            }
        });

        mButtonLowLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowLeft != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mImageLowLeft.setImageResource(R.drawable.circle);
                    lowLeft = 'O';
                }
                else{
                    playerTurn = 1; //alternate
                    mImageLowLeft.setImageResource(R.drawable.cross);
                    lowLeft = 'X';
                }

                checkForMatch();
            }
        });

        mButtonLowMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowMiddle != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mImageLowMiddle.setImageResource(R.drawable.circle);
                    lowMiddle = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageLowMiddle.setImageResource(R.drawable.cross);
                    lowMiddle = 'X';
                }

                checkForMatch();
            }
        });

        mButtonLowRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lowRight != ' ') {
                    return;
                }

                if (playerTurn == 1) {
                    playerTurn = 2; //alternate
                    mImageLowRight.setImageResource(R.drawable.circle);
                    lowRight = 'O';
                }
                else {
                    playerTurn = 1; //alternate
                    mImageLowRight.setImageResource(R.drawable.cross);
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
        return;
    }

    //increase player two score by one
    public void incrementPlayerTwoScore() {
        playerTwoScore++;
        mTextViewPlayerTwoScore.setText("Player Two: " + playerTwoScore);
        return;
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

        mImageTopLeft.startAnimation(animFadeOut);
        mImageTopMiddle.startAnimation(animFadeOut);
        mImageTopRight.startAnimation(animFadeOut);
        mImageMidLeft.startAnimation(animFadeOut);
        mImageMidMiddle.startAnimation(animFadeOut);
        mImageMidRight.startAnimation(animFadeOut);
        mImageLowLeft.startAnimation(animFadeOut);
        mImageLowMiddle.startAnimation(animFadeOut);
        mImageLowRight.startAnimation(animFadeOut);
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

        mImageTopLeft.setImageResource(R.drawable.white);
        mImageTopMiddle.setImageResource(R.drawable.white);
        mImageTopRight.setImageResource(R.drawable.white);
        mImageMidLeft.setImageResource(R.drawable.white);
        mImageMidMiddle.setImageResource(R.drawable.white);
        mImageMidRight.setImageResource(R.drawable.white);
        mImageLowLeft.setImageResource(R.drawable.white);
        mImageLowMiddle.setImageResource(R.drawable.white);
        mImageLowRight.setImageResource(R.drawable.white);
    }

    //make scribble sound when updating the score

    public void checkForMatch() {
        turns++;

        updatePlayerLogoVisibility(); //update to show whos turn it is

        checkLeftVert();
        checkMiddleVert();
        checkRightVert();
        checkTopHorz();
        checkMiddleHorz();
        checkLowHorz();
        checkTopLeftDiag();
        checkTopRightDiag();

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
        mImageTopMiddle.setImageResource(R.drawable.white);
        mImageTopRight.setImageResource(R.drawable.white);
        mImageMidMiddle.setImageResource(R.drawable.white);
        mImageMidRight.setImageResource(R.drawable.white);
        mImageLowMiddle.setImageResource(R.drawable.white);
        mImageLowRight.setImageResource(R.drawable.white);

        //animate fade out
        mImageTopLeft.startAnimation(animFadeOut);
        mImageMidLeft.startAnimation(animFadeOut);
        mImageLowLeft.startAnimation(animFadeOut);
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
        mImageTopLeft.setImageResource(R.drawable.white);
        mImageTopRight.setImageResource(R.drawable.white);
        mImageMidLeft.setImageResource(R.drawable.white);
        mImageMidRight.setImageResource(R.drawable.white);
        mImageLowLeft.setImageResource(R.drawable.white);
        mImageLowRight.setImageResource(R.drawable.white);

        //animate fade out
        mImageTopMiddle.startAnimation(animFadeOut);
        mImageMidMiddle.startAnimation(animFadeOut);
        mImageLowMiddle.startAnimation(animFadeOut);
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
        mImageTopLeft.setImageResource(R.drawable.white);
        mImageTopMiddle.setImageResource(R.drawable.white);
        mImageMidLeft.setImageResource(R.drawable.white);
        mImageMidMiddle.setImageResource(R.drawable.white);
        mImageLowLeft.setImageResource(R.drawable.white);
        mImageLowMiddle.setImageResource(R.drawable.white);

        //animate fade out
        mImageTopRight.startAnimation(animFadeOut);
        mImageMidRight.startAnimation(animFadeOut);
        mImageLowRight.startAnimation(animFadeOut);
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
        mImageMidLeft.setImageResource(R.drawable.white);
        mImageMidMiddle.setImageResource(R.drawable.white);
        mImageMidRight.setImageResource(R.drawable.white);
        mImageLowLeft.setImageResource(R.drawable.white);
        mImageLowMiddle.setImageResource(R.drawable.white);
        mImageLowRight.setImageResource(R.drawable.white);

        //animate fade out
        mImageTopLeft.startAnimation(animFadeOut);
        mImageTopMiddle.startAnimation(animFadeOut);
        mImageTopRight.startAnimation(animFadeOut);
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
        mImageTopLeft.setImageResource(R.drawable.white);
        mImageTopMiddle.setImageResource(R.drawable.white);
        mImageTopRight.setImageResource(R.drawable.white);
        mImageLowLeft.setImageResource(R.drawable.white);
        mImageLowMiddle.setImageResource(R.drawable.white);
        mImageLowRight.setImageResource(R.drawable.white);

        //animate fade out
        mImageMidLeft.startAnimation(animFadeOut);
        mImageMidMiddle.startAnimation(animFadeOut);
        mImageMidRight.startAnimation(animFadeOut);
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
        mImageTopLeft.setImageResource(R.drawable.white);
        mImageTopMiddle.setImageResource(R.drawable.white);
        mImageTopRight.setImageResource(R.drawable.white);
        mImageMidLeft.setImageResource(R.drawable.white);
        mImageMidMiddle.setImageResource(R.drawable.white);
        mImageMidRight.setImageResource(R.drawable.white);

        //animate fade out
        mImageLowLeft.startAnimation(animFadeOut);
        mImageLowMiddle.startAnimation(animFadeOut);
        mImageLowRight.startAnimation(animFadeOut);
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
        mImageTopMiddle.setImageResource(R.drawable.white);
        mImageTopRight.setImageResource(R.drawable.white);
        mImageMidLeft.setImageResource(R.drawable.white);
        mImageMidRight.setImageResource(R.drawable.white);
        mImageLowLeft.setImageResource(R.drawable.white);
        mImageLowMiddle.setImageResource(R.drawable.white);

        //animate fade out
        mImageTopLeft.startAnimation(animFadeOut);
        mImageMidMiddle.startAnimation(animFadeOut);
        mImageLowRight.startAnimation(animFadeOut);
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
