package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    private int nullX = 3;
    private int nullY = 3;
    private RelativeLayout group;
    private Button[][] blocks;
    private int[] num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readyPuzzle();
        makeNumArray();
        makeNums();
        showNums();
    }

    private void showNums() {
        nullX = 3;
        nullY = 3;
        for (int i = 0; i < group.getChildCount() - 1; i++) {
            blocks[i / 4][i % 4].setText(String.valueOf(num[i]));
        }
        blocks[nullX][nullY].setText("");
    }
    public void restart(View button) {
        readyPuzzle();
        makeNumArray();
        makeNums();
        showNums();
        Button resetBut = (Button)findViewById(R.id.restart);
        resetBut.setText("Reset");
    }

    private void makeNums() {
        int n = 15;
        Random random = new Random();
        while (n > 1) {
            int ranNum = random.nextInt(n--);
            int temp = num[ranNum];
            num[ranNum] = num[n];
            num[n] = temp;
        }

    }

    private void makeNumArray() {
        num = new int[16];
        for (int i = 0; i < group.getChildCount(); i++) {
            num[i] = i + 1;

        }
    }


    private void readyPuzzle() {
        group = findViewById(R.id.group);
        blocks = new Button[4][4];
        for (int i = 0; i < group.getChildCount(); i++) {
            blocks[i / 4][i % 4] = (Button) group.getChildAt(i);
        }
    }

    public void buttonClick(View view) {
        Button block = (Button) view;
        int x = block.getTag().toString().charAt(0) - '0';
        int y = block.getTag().toString().charAt(1) - '0';

        if((Math.abs(nullX-x)==1&&nullY==y)||(Math.abs(nullY-y)==1&&nullX==x)){
            blocks[nullX][nullY].setText(block.getText().toString());
            blocks[nullX][nullY].setBackgroundResource(android.R.drawable.btn_default);
            block.setText(" ");
            nullX=x;//moves the empty button
            nullY=y;
            checkSolve();
        }

    }

    private void checkSolve() {
        boolean solved = false;
        if (nullX == 3 && nullY == 3) {
            for (int i = 0; i < group.getChildCount() - 1; i++) {
                if (blocks[i / 4][i % 4].getText().toString().equals(String.valueOf(i + 1))) {
                    solved = true;
                } else {
                    solved = false;
                    break;
                }
            }

        }
        if (solved) {
            Button button = (Button)findViewById(R.id.restart);
            button.setText("You Win! Play Again!");
        }
    }

}

