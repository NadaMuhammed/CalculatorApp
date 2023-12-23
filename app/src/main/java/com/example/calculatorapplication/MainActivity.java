package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView resultTextView;
    TextView smallTextView;
    String lhs = "";
    String operator = "";
    boolean clear = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.resultTv);
        smallTextView = findViewById(R.id.smallTv);
    }

    public void onClickDigit(View view) {
        Button button = (Button) view;
        if (!clear) {
            resultTextView.append(button.getText().toString());
        } else {
            resultTextView.setText(button.getText().toString());
            clear = false;
        }
    }

    public void onClickDot(View view){
        if (resultTextView.getText().toString().equals("")){
            resultTextView.setText("0.");
        }
        else if (!resultTextView.getText().toString().matches("^\\d+\\.\\d+")){
            resultTextView.append(".");
        }
    }

    public void onClickOperator(View view) {
        Button button = (Button) view;
        if (button.getText().toString().equals("√")){
            resultTextView.setText(String.valueOf(Math.sqrt(Double.parseDouble(resultTextView.getText().toString()))));
            return;
        }
        if (operator.isEmpty()) {
            lhs = resultTextView.getText().toString();
        } else{
            lhs = String.valueOf(calculate());
        }
        operator = button.getText().toString();
        smallTextView.setText(lhs + " " + operator + " ");
        resultTextView.setText("");
    }

    public void onClickEqual(View view) {
        if (resultTextView.getText().toString().isEmpty() && lhs.isEmpty()) {
            resultTextView.setText("");
            return;
        }

        if (resultTextView.getText().toString().isEmpty()) {
            resultTextView.setText(lhs);
            smallTextView.setText("");
            return;
        }

        Double value = calculate();
        resultTextView.setText(value.toString());
        smallTextView.setText("");
        operator = "";
        clear = true;

    }


    public void onClickClear(View view) {
        resultTextView.setText("");
        smallTextView.setText("");
        lhs = "";
        operator = "";
    }

    public Double calculate() {
        if (lhs.isEmpty()) {
            return 0.0;
        }
        if (resultTextView.getText().toString().isEmpty() || resultTextView.getText().toString().equals("=") ||
                resultTextView.getText().toString().equals("+") || resultTextView.getText().toString().equals("-") ||
                resultTextView.getText().toString().equals("/") || resultTextView.getText().toString().equals("x")) {
            return Double.parseDouble(lhs);
        }

        Double result;
        switch (operator) {
            case "+":
                result = Double.parseDouble(lhs) + Double.parseDouble(resultTextView.getText().toString());
                break;
            case "-":
                result = Double.parseDouble(lhs) - Double.parseDouble(resultTextView.getText().toString());
                break;
            case "x":
                result = Double.parseDouble(lhs) * Double.parseDouble(resultTextView.getText().toString());
                break;
            case "/":
                result = Double.parseDouble(lhs) / Double.parseDouble(resultTextView.getText().toString());
                break;
            default:
                result = Double.parseDouble(lhs);
        }
        return result;
    }
}