package com.example.hongf.bt;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int times = 0;
    int correctInput = 0;
    int userInput = 0;
    int correct_times = 0;
    TextView question;
    TextView counter;
    Button a;
    Button b;
    Button c;
    Button d;
    boolean Ongoing = true;
    Button again;
    Button Start_b;
    boolean start = false;

    public void choice_1(View view){
        userInput = Integer.parseInt(String.valueOf(view.getTag()));
        if (Ongoing && start) {
            if (correctInput == userInput) {
                correct_times++;
            }
            Log.i("user_choice:", String.valueOf(view.getTag()));
            int reset_num = reset_question();
            Log.i("answer in function",String.valueOf(reset_num));
            correctInput = set_button(reset_num);

            Log.i("correct_choice:", String.valueOf(correctInput));

            times++;
            set_counter(times, correct_times);
        }
    }
    public int reset_question(){
        Random rand = new Random();
        int rand1 = rand.nextInt(100);
        int rand2 = rand.nextInt(100);
        int answer = rand1 + rand2;
        question.setText(rand1 + " + " + rand2);
        Log.i("number:",String.valueOf(answer));
        return answer;
    }
    public int set_button(int answer){
        Random rand = new Random();
        int rand1 = rand.nextInt(198);
        int rand2 = rand.nextInt(198);
        int rand3 = rand.nextInt(198);
        while(true){
            if(rand1 == rand2 || rand1 == rand3 || rand2 == rand3){
                rand1 = rand.nextInt(198);
                rand2 = rand.nextInt(198);
                rand3 = rand.nextInt(198);
                continue;
            }
            else if(rand1 == answer || rand2 == answer || rand3 == answer){
                rand1 = rand.nextInt(198);
                rand2 = rand.nextInt(198);
                rand3 = rand.nextInt(198);
                continue;
            }
            else{break;}
        }
        int order = rand.nextInt(4) + 1;
        Log.i("answer in set_button", String.valueOf(answer));
        Log.i("order",String.valueOf(order));
        if (order == 1){
            a.setText(String.valueOf(answer));
            b.setText(String.valueOf(rand1));
            c.setText(String.valueOf(rand2));
            d.setText(String.valueOf(rand3));
        }
        else if (order == 2){
            a.setText(String.valueOf(rand1));
            b.setText(String.valueOf(answer));
            c.setText(String.valueOf(rand2));
            d.setText(String.valueOf(rand3));
        }
        else if (order == 3){
            a.setText(String.valueOf(rand2));
            b.setText(String.valueOf(rand1));
            c.setText(String.valueOf(answer));
            d.setText(String.valueOf(rand3));
        }
        else if (order == 4){
            a.setText(String.valueOf(rand3));
            b.setText(String.valueOf(rand1));
            c.setText(String.valueOf(rand2));
            d.setText(String.valueOf(answer));
        }
        return order;

    }
    public void set_counter(int times, int correct){
        counter.setText(correct +  "/" +String.valueOf(times));
    }

    public void play_again(View view){
        set_button(reset_question());
        again.setVisibility(View.GONE);
        set_counter(0,0);
        Ongoing = true;
        times = 0;
        correct_times = 0;
        final TextView timer = findViewById(R.id.myTimer);
        CountDownTimer countDownTimer = new CountDownTimer(20000 + 1000,1000) {
            @Override
            public void onTick(long l) {
                timer.setText((int)l / 1000 + "s");
                Log.i("value of l:",String.valueOf((int)l));
            }

            @Override
            public void onFinish() {
                Ongoing = false;
                again.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void Start(View view){
        start = true;
        final TextView timer = findViewById(R.id.myTimer);
        Start_b.setVisibility(View.INVISIBLE);
        CountDownTimer countDownTimer = new CountDownTimer(20000 + 1000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText((int) l / 1000 + "s");
                Log.i("value of l:", String.valueOf((int) l));
            }

            @Override
            public void onFinish() {
                Ongoing = false;
                again.setVisibility(View.VISIBLE);
            }
        }.start();
        if (times == 0) {
            correctInput = set_button(reset_question());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Start_b = (Button) findViewById(R.id.start);
        again = (Button) findViewById(R.id.myPlayAgain);
        a = (Button) findViewById(R.id.choice1);
        b = (Button) findViewById(R.id.choice2);
        c = (Button) findViewById(R.id.choice3);
        d = (Button) findViewById(R.id.choice4);
        final TextView timer = findViewById(R.id.myTimer);
        counter = findViewById(R.id.myCounter);
        question = findViewById(R.id.myQuestion);
    }
}
