package com.bigwinlab.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.Random;


public class GameItself extends AppCompatActivity implements  View.OnClickListener {


    private int numOfElements;
    private  Card    [] allButtons;
    private int [] allButtonsGraphicLocation;
    private int [] allButtonsGraphics;
    private Card selectButton1;
    private Card selectButton2;
    private boolean isBusy = false;
    final String LEVEL = "LEVEL";
    final String FOR_TIMER = "forTimer";
    private int sizeOfMat;
    private int numOfSeconds;

    private EditText timer_txt;


    private int[] timerSeconds = {30, 45, 60};
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_itself);

        bindUI();
        initForTimer(numOfSeconds + 1, timer_txt);
        GridLayout theGridLayout = (GridLayout)findViewById(R.id.grid_layout_for_all);
        sizeOfMat = getIntent().getIntExtra(LEVEL,0);
        int numCol =sizeOfMat;
        int numRow = sizeOfMat;
        this.numOfElements = numCol * numRow;
        this.allButtons = new Card[numOfElements];
        this.allButtonsGraphics = new int [numOfElements/2];
        if(numRow == 2){
            putAllButtonsGraphicForEasy();
        }else if(numRow == 4){
            putAllButtonsGraphicForMedium();
        }
        this.allButtonsGraphicLocation = new int [numOfElements];
        shuffleButtonGraphics();
        initCards(numRow,numCol,theGridLayout);


    }

    private void initForTimer(long numOfSeconds, EditText timerText) {
        timer = new Timer(numOfSeconds * 1000, 1000, timerText, this);
        timer.start();
    }


    public void initCards(int numRow, int numCol, GridLayout theGridLayout){
        for (int row = 0; row < numRow ; row++){
            for(int col = 0 ; col <numCol ; col++){
                Card tempButton = new Card(this,row,col,allButtonsGraphics[allButtonsGraphicLocation[row *numCol +col]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                allButtons[row * numCol +col] = tempButton;
                theGridLayout.addView(tempButton);
            }
        }
    }

    protected void shuffleButtonGraphics(){
        Random rand = new Random();

        for (int i = 0; i < numOfElements ; i++ ){
            this.allButtonsGraphicLocation[i] = i % (numOfElements/2);
        }
        for (int i = 0; i < numOfElements ; i++ ){
            int temp = this.allButtonsGraphicLocation[i];
            if(numOfElements == 4){
                int swapIndex = rand.nextInt(4);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }else if(numOfElements == 16){
                int swapIndex = rand.nextInt(16);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }else{
                int swapIndex = rand.nextInt(24);
                allButtonsGraphicLocation[i] = allButtonsGraphicLocation[swapIndex];
                allButtonsGraphicLocation[swapIndex] = temp;
            }

        }
    }

    public void onBackPressed(){
        timer.cancel();
        helperForMenu();
    }

    private void bindUI() {
        timer_txt = (EditText)findViewById(R.id.timer_game);
        int level = getIntent().getIntExtra(FOR_TIMER, 0);
        numOfSeconds = timerSeconds[level];
    }


    public void putAllButtonsGraphicForEasy(){
        this.allButtonsGraphics[0] = R.drawable.button_1;
        this.allButtonsGraphics[1] = R.drawable.button_2;
    }

    public void putAllButtonsGraphicForMedium(){
        this.allButtonsGraphics[0] = R.drawable.button_1;
        this.allButtonsGraphics[1] = R.drawable.button_2;
        this.allButtonsGraphics[2] = R.drawable.button_3;
        this.allButtonsGraphics[3] = R.drawable.button_4;
        this.allButtonsGraphics[4] = R.drawable.button_5;
        this.allButtonsGraphics[5] = R.drawable.button_6;
        this.allButtonsGraphics[6] = R.drawable.button_7;
        this.allButtonsGraphics[7] = R.drawable.button_8;
    }

    private boolean checkIfDone() {
        for (int i = 0; i < numOfElements; i++) {
            if (allButtons[i].isEnabled()) {
                return false;
            }
        }
        return true;
    }

    private void backToMenu() {
        Handler tempHandler = new Handler();
        tempHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                helperForMenu();
            }
        }, 3000);

    }

    public void timeOutForGame() {
        Toast.makeText(GameItself.this, "Time's up", Toast.LENGTH_LONG).show();
        helperForMenu();
    }

    private void helperForMenu(){
        Intent intent = new Intent(GameItself.this, GrandioseGame.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if(isBusy){
            return;
        }
        Card button = (Card) view;
        if(button.isMatched){
            return;
        }
        if(selectButton1 == null){
            selectButton1 = button;
            selectButton1.flip();
            return;
        }
        if(selectButton1.getId() == button.getId()){
            return;
        }
        if(selectButton1.getFrontImageID() == button.getFrontImageID()){
            button.flip();
            button.setMatched(true);
            selectButton1.setMatched(true);

            selectButton1.setEnabled(false);
            button.setEnabled(false);
            selectButton1 = null;
            if(checkIfDone()){
                GameItself.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GameItself.this, "Winner", Toast.LENGTH_LONG).show();
                    }
                });
                timer.cancel();
                backToMenu();
            }
            return;

        }else{
            selectButton2  = button;
            selectButton2.flip();
            isBusy = true;

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectButton2.flip();
                    selectButton1.flip();
                    selectButton1 = null;
                    selectButton2 = null;
                    isBusy = false;
                }
            },500);
        }
    }
}