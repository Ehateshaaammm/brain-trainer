package com.example.mohammadehatesham.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button button;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView questionTextView;
    TextView resultTextView;
    TextView timerTextView;
    TextView finalTextView;
    TextView textView;
    ConstraintLayout gameLayout;
    ConstraintLayout finalLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrect;
    TextView scoreTextView;
    int score = 0;
    int numberOfQuestion = 0;
    public void Start (View view){
        button.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(playAgainButton);
    }
    public void newQuestions() {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrect = rand.nextInt(4);
        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrect) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    public void choose(View view){
        String l = Integer.toString(locationOfCorrect);
        String t = view.getTag().toString();
        resultTextView.setVisibility(View.VISIBLE);
        if(l.equals(t)){
            resultTextView.setText("Correct!!");
            score++;
        }else{
            resultTextView.setText("Incorrect!!");
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        newQuestions();

    }
    public void playAgain(View view){
        gameLayout.setVisibility(View.VISIBLE);
        textView.setVisibility(View.INVISIBLE);
        finalTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestion=0;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        newQuestions();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.VISIBLE);
                finalTextView.setVisibility(View.VISIBLE);
             playAgainButton.setVisibility(View.VISIBLE);
             gameLayout.setVisibility(View.INVISIBLE);
             finalTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
             finalLayout.setVisibility(View.VISIBLE);
            }
        }.start();

    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        textView = (TextView)findViewById(R.id.textView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        finalLayout = (ConstraintLayout) findViewById(R.id.finalLayout);
        finalTextView = (TextView) findViewById(R.id.finalTextView);
        resultTextView.setVisibility(View.INVISIBLE);
    }
}
