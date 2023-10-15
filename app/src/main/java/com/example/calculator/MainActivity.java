package com.example.calculator;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;


public class MainActivity extends AppCompatActivity {

    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input_value);
        display.setShowSoftInputOnFocus(false);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.text).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });


    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.text).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }

        else {


            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));

            display.setSelection(cursorPos + 1);

        }

    }

    public void ZERO(View view){
        updateText("0");
    }
    public void ONE(View view){
        updateText("1");
    }
    public void two(View view){
        updateText("2");
    }
    public void three(View view){
        updateText("3");
    }
    public void FOUR(View view){
        updateText("4");
    }
    public void FIVE(View view){
        updateText("5");
    }
    public void SIX(View view){
        updateText("6");
    }
    public void SEVEN(View view){
        updateText("7");
    }
    public void EIGHT(View view){
        updateText("8");
    }
    public void NINE(View view){
        updateText("9");
    }
    public void clear(View view){
        display.setText("");

    }
    public void parentheisi(View view) {
        int cursorPos = display.getSelectionStart();
        int openpar = 0;
        int closedpar = 0;

        int textlen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i + 1).equals("(")) {
                openpar += 1;
            }
            if (display.getText().toString().substring(i, i + 1).equals(")")) {
                closedpar += 1;

            }

        }

        if (openpar == closedpar || display.getText().toString().substring(textlen - 1, textlen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closedpar < openpar && !display.getText().toString().substring(textlen - 1, textlen).equals("(")) {
            updateText(")");
            display.setSelection(cursorPos + 1);
        }

    }

    public void percentage(View view){
        updateText("%");
        ;    }
    public void substraction(View view){
        updateText("-");
    }
    public void addition(View view){
        updateText("+");
    }
    public void DIVIDE(View view){
        updateText("/");
    }
    public void MULTIPLICATION(View view){
        updateText("*");
    }
    public void equal_to(View view){

        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");


        Expression exp =new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());




    }

    public void plus_minus(View view){
        updateText("+/-");

    }
    public void back(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0){
            SpannableStringBuilder Selection = (SpannableStringBuilder) display.getText();
            Selection.replace(cursorPos - 1, cursorPos,"");
            display.setText(Selection);
            display.setSelection(cursorPos - 1);

        }

    }
    public void dot(View view){
        updateText(".");
    }
}