package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Exam extends AppCompatActivity {
    TextView questions;
    Button next;
    RadioButton option1,option2,option3,option4,correct_answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        questions=(TextView) findViewById(R.id.textView38);
        next =(Button) findViewById(R.id.button3);
        option1=(RadioButton) findViewById(R.id.radioButton);
        option2=(RadioButton) findViewById(R.id.radioButton2);
        option3=(RadioButton) findViewById(R.id.radioButton3);
        option4=(RadioButton) findViewById(R.id.radioButton4);
        correct_answer=(RadioButton) findViewById(R.id.radioButton5);
    }
}