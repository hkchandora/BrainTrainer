package com.himanshu.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView,questionTextView,answerTextView,resultTextView;
    Button b1,b2,b3,b4,playAgainButton,startButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(51);
        int b = rand.nextInt(51);

        questionTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answer.clear();

        int incorrectAnswer;

        for(int i=0; i<4; i++) {

            if(i == locationOfCorrectAnswer) {

                answer.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(101);
                while(incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(101);
                }
                answer.add(incorrectAnswer);

            }
        }

        b1.setText(Integer.toString(answer.get(0)));
        b2.setText(Integer.toString(answer.get(1)));
        b3.setText(Integer.toString(answer.get(2)));
        b4.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view) {

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct");
        } else {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestion++;
        answerTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void playAgain(View view) {

        score = 0;
        numberOfQuestion = 0;

        timerTextView.setText("30s");
        answerTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.timerTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        startButton = (Button) findViewById(R.id.startButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
    }
}
