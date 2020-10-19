package com.tgalinis.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    enum Operation { PLUS, MINUS, MULT, DIV };

    // For convenience, set plus as default operation.
    protected Operation mode = Operation.PLUS;

    // I'd like to use the above operation enum as an index to an array
    // of functions. Did similar things in C but not in Java. A quick Google
    // search pointed me to the Adapter design pattern. Seems like a lot of
    // boilerplate for something so simple, but that's standard Java affair
    // I guess.
    interface CalculatorFn {
        double calculate(double x, double y);
    }

    protected CalculatorFn[] operations = {
        new CalculatorFn() {
            @Override
            public double calculate(double x, double y) {
                return x + y;
            }
        },

        new CalculatorFn() {
            @Override
            public double calculate(double x, double y) {
                return x - y;
            }
        },

        new CalculatorFn() {
            @Override
            public double calculate(double x, double y) {
                return x * y;
            }
        },

        new CalculatorFn() {
            @Override
            public double calculate(double x, double y) {
                return x / y;
            }
        },
    };

    Button[] op_buttons = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        op_buttons[0] = findViewById(R.id.plusBtn);
        op_buttons[1] = findViewById(R.id.minusBtn);
        op_buttons[2] = findViewById(R.id.multBtn);
        op_buttons[3] = findViewById(R.id.divBtn);

        // Addition is default, make sure it's selected.
        op_buttons[mode.ordinal()].setTextColor(Color.BLUE);
    }

    public void onPlusClick(View view) {
        op_buttons[mode.ordinal()].setTextColor(Color.BLACK);
        mode = Operation.PLUS;
        op_buttons[mode.ordinal()].setTextColor(Color.BLUE);
    }

    public void onMinusClick(View view) {
        op_buttons[mode.ordinal()].setTextColor(Color.BLACK);
        mode = Operation.MINUS;
        op_buttons[mode.ordinal()].setTextColor(Color.BLUE);
    }

    public void onMultClick(View view) {
        op_buttons[mode.ordinal()].setTextColor(Color.BLACK);
        mode = Operation.MULT;
        op_buttons[mode.ordinal()].setTextColor(Color.BLUE);
    }

    public void onDivClick(View view) {
        op_buttons[mode.ordinal()].setTextColor(Color.BLACK);
        mode = Operation.DIV;
        op_buttons[mode.ordinal()].setTextColor(Color.BLUE);
    }

    public void onEqualsClick(View view) {
        EditText operand0 = (EditText) findViewById(R.id.operand0);
        EditText operand1 = (EditText) findViewById(R.id.operand1);
        TextView resultView = (TextView) findViewById(R.id.result);
        double result = operations[mode.ordinal()].calculate(
                Double.parseDouble(operand0.getText().toString()),
                Double.parseDouble(operand1.getText().toString())
        );

        resultView.setText(Double.toString(result));
    }
}