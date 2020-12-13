package com.androidstudio.kotlin.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private ArrayList<Integer> answers = new ArrayList<>();
    private TextView resultTextView;
    private TextView pointsTextView;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private TextView sumTextView;
    private TextView timerTextView;
    private int locationOfCorrectAnswer;
    private int score = 0;
    private int numOfQuestions = 0;
    private int incorrectAnswer;

    public void start(View view){
        startButton.setVisibility(view.INVISIBLE);
    }

    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct !");
        }
        else {
            resultTextView.setText("Incorrect !");
        }
        numOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+ "/" +Integer.toString(numOfQuestions));
        generateQuestion();
    }

    public void generateQuestion(){
        Random rand = new Random();
        int num1 = rand.nextInt(21);
        int num2 = rand.nextInt(21);

        //UPDATE SUM TEXT VIEW VALUES
        sumTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        generateQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Your score : " +Integer.toString(score)+ "/" +Integer.toString(numOfQuestions) );
                timerTextView.setText("0s");
            }
        }.start();
    }
}