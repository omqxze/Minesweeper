package com.example.minesweeper;

import android.content.Context;
import android.widget.Button;

public class BlockButton extends Button {

    boolean uncover = true;
    boolean mine;       // Whether it is flagged
    int x, y;           // The positions of the button (x, y position)
    boolean flag = false;       // Whether it is a mine
    int neighborMines=0;  // The number of mines around the block
    public BlockButton(Context context, int x, int y) {
        super(context);
        this.x = x;
        this.y = y;
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isUncover() {
        return uncover;
    }

    public void setUncover(boolean uncover) {
        this.uncover = uncover;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getNeighborMines() {
        return neighborMines;
    }

    public void setNeighborMines(int neighborMines) {
        this.neighborMines = neighborMines;
    }
}