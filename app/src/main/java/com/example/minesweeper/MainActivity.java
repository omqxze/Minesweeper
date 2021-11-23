package com.example.minesweeper;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minesweeper.databinding.ActivityMainBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public int numberOfBtn = 0;
    public int flagNumbers = 10;
    public boolean flag = false;
    BlockButton[][] buttons = new BlockButton[9][9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        createView();
    }
    @SuppressLint("ResourceAsColor")
    public void createView(){
        TableLayout table=binding.tableLayout;
        table.setShrinkAllColumns(true);

        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
        textView.setText("Mines : 10");
        textView.setTextSize(Dimension.SP, 30);

        TextView textView2 = new TextView(this);
        textView2.setText("Flag : "+Integer.toString(flagNumbers));
        textView2.setTextSize(Dimension.SP, 30);

        table.addView(textView);
        table.addView(textView2);

        for (int i = 0; i < 9; i++){
            TableRow tableRow = new TableRow(getApplicationContext());
            for (int j = 0; j < 9; j++){
                BlockButton btn = new BlockButton(this, i, j);
                btn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
                btn.setOnClickListener(v -> {
                    if(numberOfBtn == 70){
                        Intent intent = new Intent(this, GameWinActivity.class);
                        startActivity(intent);
                    }
                    if (flag == false) {
                        if(btn.isUncover()){
                            if(btn.isMine()==true){
                                Intent intent = new Intent(getApplicationContext(), GameDefeatActivity.class);
                                startActivity(intent);
                            }
                            if(btn.isMine()==false) {
                                uncoverNeighborMines(btn.getXPosition(), btn.getYPosition());
                            }
                        }
                    }
                    else{
                        if(btn.isUncover()) {
                            btn.setText("+");
                            btn.setUncover(false);
                            btn.setFlag(true);
                            flagNumbers--;
                            textView2.setText("Flag : "+Integer.toString(flagNumbers));
                        }
                        else {
                            btn.setText("");
                            btn.setUncover(true);
                            btn.setFlag(false);
                            flagNumbers++;
                            textView2.setText("Flag : "+Integer.toString(flagNumbers));
                        }
                    }
                });
                tableRow.addView(btn);
                buttons[i][j]=btn;
            }
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            table.addView(tableRow);
        }


        Button btn = new Button(this);
        btn.setFocusable(false);
        btn.setText("BREAK");
        btn.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));
        btn.setOnClickListener(v -> {
            if (flag == false) {
                flag = true;
                Toast.makeText(getApplicationContext(), "깃발 상태", Toast.LENGTH_SHORT).show();
            }
            else {
                flag = false;
                Toast.makeText(getApplicationContext(), "초기 상태", Toast.LENGTH_SHORT).show();
            }
        });
        makeMine();
        makeNeighborMines();
        table.addView(btn);
    }

    public void makeNeighborMines(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                findNeighborMines(i,j);
            }
        }
    }

    public void makeMine(){
        int mines = 0;
        while(true) {
            if (mines == 10){
                break;
            }
            Random rand = new Random();
            int x = rand.nextInt(9);
            int y = rand.nextInt(9);
            if (buttons[x][y].isMine() == true) {
                continue;
            }
            else{
                buttons[x][y].setMine(true);
                mines++;
            }
        }
    }

    public void findNeighborMines(int x, int y){
        int neighborMines = 0;

        if (checkMine(x-1,y-1))
            neighborMines++;
        if (checkMine(x-1,y))
            neighborMines++;
        if (checkMine(x,y-1))
            neighborMines++;
        if (checkMine(x-1,y+1))
            neighborMines++;
        if (checkMine(x+1,y))
            neighborMines++;
        if (checkMine(x,y+1))
            neighborMines++;
        if (checkMine(x+1,y+1))
            neighborMines++;
        if (checkMine(x+1,y-1))
            neighborMines++;

        buttons[x][y].setNeighborMines(neighborMines);
    }

    public boolean checkMine(int x , int y){
        try {
            return buttons[x][y].isMine();
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    public boolean checkNeighborMines(int x , int y){
        try{
            return buttons[x][y].isUncover()&&(buttons[x][y].isFlag()==false);
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }

    public void uncoverNeighborMines(int x , int y){
        if(buttons[x][y].getNeighborMines()==0){
            buttons[x][y].setBackgroundColor(224);
            numberOfBtn++;
            buttons[x][y].setUncover(false);
        }else{
            buttons[x][y].setText(buttons[x][y].neighborMines+"");
            buttons[x][y].setBackgroundColor(224);
            numberOfBtn++;
            buttons[x][y].setUncover(false);
        }
        if(buttons[x][y].getNeighborMines() == 0){
            if(checkNeighborMines(x-1,y-1)){
                uncoverNeighborMines(x-1,y-1);
            }
            if(checkNeighborMines(x-1,y)){
                uncoverNeighborMines(x-1,y);
            }
            if(checkNeighborMines(x,y-1)){
                uncoverNeighborMines(x,y-1);
            }
            if(checkNeighborMines(x-1,y+1)){
                uncoverNeighborMines(x-1,y+1);
            }
            if(checkNeighborMines(x+1,y)){
                uncoverNeighborMines(x+1,y);
            }
            if(checkNeighborMines(x,y+1)){
                uncoverNeighborMines(x,y+1);
            }
            if(checkNeighborMines(x+1,y-1)){
                uncoverNeighborMines(x+1,y-1);
            }
            if(checkNeighborMines(x+1,y+1)){
                uncoverNeighborMines(x+1,y+1);
            }

        }
        return;
    }
}