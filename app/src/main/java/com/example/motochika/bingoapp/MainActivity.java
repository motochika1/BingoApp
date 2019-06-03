package com.example.motochika.bingoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //max_number
    private int maxNumber = 75;

    private ArrayList<String> history = new ArrayList<>();

    private EditText maxNumberEditText;
    private Button registerMaxNumberButton;
    private Button nextNumberButton;
    private TextView currentNumberTextView;
    private TextView historyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maxNumberEditText = findViewById(R.id.max_number);
        registerMaxNumberButton = findViewById(R.id.register_max_number);
        nextNumberButton = findViewById(R.id.next_number);
        currentNumberTextView = findViewById(R.id.current_number);
        historyTextView = findViewById(R.id.history);

        maxNumberEditText.setText(String.valueOf(maxNumber));

        registerMaxNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力値を文字列で取り出す
                String maxNumberString = maxNumberEditText.getText().toString();
                //int型に変換してから代入
                maxNumber = Integer.valueOf(maxNumberString);

                Log.d("MainActivity", "maxNumber: " + maxNumber);

            }
        });

        nextNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickNextNumber();
            }
        });

    }
    private void onClickNextNumber(){
        Log.d("MainActivity","onClickNextNumber");

        //maxNumberを考慮したランダムな数値
        int nextNumber = createRandomNumber();

        //重複があれば数値の再生成
        while(history.contains("" + nextNumber)){
            Log.d("MainActivity","重複したので再生成");
            nextNumber = createRandomNumber();
        }

        //nextNumberを文字列に変換する
        String nextNumberStr = "" + nextNumber;

        //nextNumberを画面に表示する
        currentNumberTextView.setText(nextNumberStr);

        //履歴を残す
        history.add(nextNumberStr);

        //履歴の表示
        historyTextView.setText(history.toString());

        Log.d("MainActivity",history.toString());

    }

    //maxNumberを考慮したランダムな数値の生成
    private int createRandomNumber(){
        return new Random().nextInt(maxNumber) + 1;
    }
}
