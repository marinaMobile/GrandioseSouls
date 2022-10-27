package com.bigwinlab.b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GrandioseGame extends AppCompatActivity {


    final String LEVEL = "LEVEL";
    final int EASY = 0;
    final int MEDIUM = 1;
    final String FOR_TIMER = "forTimer";

    private Button button4x4;
    private Button button2x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grandiose_game);
        button4x4 = (Button)findViewById(R.id.button_4x4_game);
        button2x2 = (Button)findViewById(R.id.button_2x2_game);

        button2x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrandioseGame.this,GameItself.class);
                intent.putExtra(LEVEL,2);
                intent.putExtra(FOR_TIMER,EASY);
                startActivity(intent);
            }
        });

        button4x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrandioseGame.this,GameItself.class);
                intent.putExtra(LEVEL,4);
                intent.putExtra(FOR_TIMER,MEDIUM);
                startActivity(intent);
            }
        });
    }

}