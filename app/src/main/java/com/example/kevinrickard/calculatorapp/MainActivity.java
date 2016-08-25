package com.example.kevinrickard.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText operandField1;
    private EditText operandField2;
    private Button buttonFunction;
    private Button buttonCalculate;
    private Button buttonClear;
    private TextView answerText;
    private int calculatorMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        operandField1 = (EditText) findViewById(R.id.editOperandField1);
        operandField2 = (EditText) findViewById(R.id.editOperandField2);
        buttonFunction = (Button) findViewById(R.id.buttonFunction);
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonClear = (Button) findViewById(R.id.buttonClear);
        answerText = (TextView) findViewById(R.id.textAnswer);

        setCalculatorMode(0);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((operandField1.getText().length() < 1) || (operandField2.getText().length() < 1))
                {
                    Toast.makeText(MainActivity.this, "Please enter two numbers", Toast.LENGTH_LONG).show();
                }
                else {
                    double operand1 = Double.parseDouble(operandField1.getText().toString());
                    double operand2 = Double.parseDouble(operandField2.getText().toString());
                    switch (calculatorMode) {
                        case 0:
                            answerText.setText(Double.toString(operand1 + operand2));
                            break;
                        case 1:
                            answerText.setText(Double.toString(operand1 - operand2));
                            break;
                        case 2:
                            answerText.setText(Double.toString(operand1 * operand2));
                            break;
                        case 3:
                            answerText.setText(Double.toString(operand1 / operand2));
                            break;
                        case 4:
                            answerText.setText(Double.toString(operand1 % operand2));
                            break;
                    }
                }
            }
        });

        buttonFunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCalculatorMode((calculatorMode + 1) % 5);
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operandField1.setText("");
                operandField2.setText("");
                answerText.setText("0.0");
                operandField1.requestFocus();
            }
        });
    }

    private void setCalculatorMode(int i) {
        switch(i)
        {
            case 0:
                calculatorMode = 0;
                buttonFunction.setText("+");
                break;
            case 1:
                calculatorMode = 1;
                buttonFunction.setText("-");
                break;
            case 2:
                calculatorMode = 2;
                buttonFunction.setText("*");
                break;
            case 3:
                calculatorMode = 3;
                buttonFunction.setText("/");
                break;
            case 4:
                calculatorMode = 4;
                buttonFunction.setText("%");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
