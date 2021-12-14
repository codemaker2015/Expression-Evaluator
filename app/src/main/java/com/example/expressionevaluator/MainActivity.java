package com.example.expressionevaluator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import de.congrace.exp4j.UnknownFunctionException;
import de.congrace.exp4j.UnparsableExpressionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText expInput = findViewById(R.id.editTextExp);
        TextView resultText = findViewById(R.id.textViewResult);
        Button btnEvaluate = findViewById(R.id.btnEvaluate);
        Button btnClear = findViewById(R.id.btnClear);

        btnEvaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String input = expInput.getText().toString();
                    if(!input.equals("")) {
                        Calculable calc = new ExpressionBuilder(input).build();
                        double result = calc.calculate();
                        resultText.setText("Result: " + String.valueOf(result));
                        Log.d("result", String.valueOf(result));
                    } else
                        Toast.makeText(MainActivity.this, "Express can't be empty", Toast.LENGTH_SHORT).show();
                } catch (UnknownFunctionException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "UnknownFunctionException: \nOops! Something went wrong.", Toast.LENGTH_SHORT).show();
                } catch (UnparsableExpressionException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "UnparsableExpressionException: \nOops! Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expInput.setText("");
                resultText.setText("");
                Toast.makeText(MainActivity.this, "Cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }
}