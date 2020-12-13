package com.androidstudio.kotlin.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private ArrayList<Integer> answers = new ArrayList<>();
    private int locationOfCorrectAnswer;

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
    }

    public void chooseAnswer(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        int incorrectAnswer;

        Random rand = new Random();
        int num1 = rand.nextInt(21);
        int num2 = rand.nextInt(21);

        //UPDATE SUM TEXT VIEW VALUES
        sumTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfCorrectAnswer = rand.nextInt(4);

        for(int i=0 ; i<4 ; i++){
            if(i==locationOfCorrectAnswer){
                answers.add(num1+num2);
            }
            else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == (num1+num2)){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
}