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

    private int nullX = 3; //initalizes the empty button with its correct x and y
    private int nullY = 3;
    private RelativeLayout group; //relativelayout allowed me to switch around the buttons relative to other buttons
    private Button[][] blocks; //2d array of buttons which will act as blocks of the puzzle
    private int[] num; //just an int array for all of the numbers for the blocks


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readyPuzzle();
        makeNumArray();
        makeNums();
        showNums();
    }

    private void showNums() {//sets the text to all blocks then sets null block to " ".
        nullX = 3;
        nullY = 3;
        for (int i = 0; i < group.getChildCount() - 1; i++) {
            blocks[i / 4][i % 4].setText(String.valueOf(num[i]));
        }
        blocks[nullX][nullY].setText("");
    }
    public void restart(View button) {//remakes the board also makes sure to switch button text back to restart when not solved
        readyPuzzle();
        makeNumArray();
        makeNums();
        showNums();
        Button resetBut = (Button)findViewById(R.id.restart);
        resetBut.setText("Restart");
    }

    private void makeNums() {// randomizes numbers up to 15 to different elements in the int array
        int n = 15;
        Random random = new Random();
        while (n > 1) {
            int ranNum = random.nextInt(n--);
            int temp = num[ranNum];
            num[ranNum] = num[n];
            num[n] = temp;
        }

    }

    private void makeNumArray() {//fills num[]
        num = new int[16];
        for (int i = 0; i < group.getChildCount(); i++) {
            num[i] = i + 1;

        }
    }


    private void readyPuzzle() {//sets up buttons in relative layout
        group = findViewById(R.id.group);
        blocks = new Button[4][4];
        for (int i = 0; i < group.getChildCount(); i++) {
            blocks[i / 4][i % 4] = (Button) group.getChildAt(i);
        }
    }

    public void buttonClick(View view) {//runs onClick
        Button block = (Button) view;
        int x = block.getTag().toString().charAt(0) - '0';//gets x of button
        int y = block.getTag().toString().charAt(1) - '0';//gets y of button

        if((Math.abs(nullX-x)==1&&nullY==y)||(Math.abs(nullY-y)==1&&nullX==x)){
            blocks[nullX][nullY].setText(block.getText().toString());//basically copies button clicked into null button
            blocks[nullX][nullY].setBackgroundResource(android.R.drawable.btn_default);
            block.setText(" ");
            nullX=x;//moves the empty button to where the button was clicked
            nullY=y;
            checkSolve();
        }

    }

    private void checkSolve() {
        boolean solved = false;
        if (nullX == 3 && nullY == 3) {//if the null button is bottom right
            for (int i = 0; i < group.getChildCount() - 1; i++) {//checks to see if all blocks are in right spot
                if (blocks[i / 4][i % 4].getText().toString().equals(String.valueOf(i + 1))) {
                    solved = true;
                } else {
                    solved = false;
                    break;
                }
            }

        }
        if (solved) {//changes restart button to notify the puzzle was solved
            Button button = (Button)findViewById(R.id.restart);
            button.setText("You Win! Play Again!");
        }
    }

}

