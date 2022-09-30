package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private boolean isTouch = false;
    private static final int col = 4;
    private static final int grid = col * col;

    private String[] blocks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        randomize();
        display();

    }

    public void display(){
        ArrayList<Button> pieces=new ArrayList<>();
        Button button;
        for(int i=0;i<blocks.length;i++){
            button = new Button(this);

            if(blocks[i].equals("0")){
                button.setText("1");
            }
            else if(blocks[i].equals("1")){
                button.setText("2");
            }
            else if(blocks[i].equals("2")){
                button.setText("2");
            }
            else if(blocks[i].equals("3")){
                button.setText("2");
            }
            else if(blocks[i].equals("2")){
                button.setText("3");
            }
            else if(blocks[i].equals("3")){
                button.setText("4");
            }
            else if(blocks[i].equals("4")){
                button.setText("5");
            }
            else if(blocks[i].equals("5")){
                button.setText("6");
            }
            else if(blocks[i].equals("6")){
                button.setText("7");
            }
            else if(blocks[i].equals("7")){
                button.setText("8");
            }
            else if(blocks[i].equals("8")){
                button.setText("9");
            }
            else if(blocks[i].equals("9")){
                button.setText("10");
            }
            else if(blocks[i].equals("10")){
                button.setText("11");
            }
            else if(blocks[i].equals("11")){
                button.setText("12");
            }
            else if(blocks[i].equals("12")){
                button.setText("13");
            }
            else if(blocks[i].equals("13")){
                button.setText("14");
            }
            else if(blocks[i].equals("14")){
                button.setText("15");
            }
            else if(blocks[i].equals("15")){
                button.setText(" ");
            }
            pieces.add(button);
        }


    }

    private void randomize(){
        int nums;
        String temp;
        Random random = new Random();
        for (int i = blocks.length-1;i<0;i--){
            nums=random.nextInt(i+1);
            temp=blocks[nums];
            blocks[nums]=blocks[i];
            blocks[i]=temp;

        }
    }
    public void init(){
        blocks = new String[grid];
        for (int i=0;i<grid;i++){
            blocks[i] = String.valueOf(i);
        }
    }
}