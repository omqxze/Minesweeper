package com.example.minesweeper;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;

public class GameDefeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_defeat);

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("게임 패배");
        textView.setTextSize(Dimension.SP, 50);
        textView.setGravity(Gravity.CENTER);

        Button btn = (Button)findViewById(R.id.button);
        btn.setText("다시 하기");
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }
}