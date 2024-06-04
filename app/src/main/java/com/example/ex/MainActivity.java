package com.example.ex;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText operand1EditText;
    private EditText operand2EditText;
    private Spinner operatorSpinner;
    private TextView resultTextView;
    private String selectedOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operand1EditText = findViewById(R.id.operand1);
        operand2EditText = findViewById(R.id.operand2);
        operatorSpinner = findViewById(R.id.operatorSpinner);
        resultTextView = findViewById(R.id.resultTextView);
        Button calculateButton = findViewById(R.id.calculateButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operators_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorSpinner.setAdapter(adapter);

        operatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedOperator = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        calculateButton.setOnClickListener(v -> calculateResult());
    }

    private void calculateResult() {
        try {
            int operand1 = Integer.parseInt(operand1EditText.getText().toString());
            int operand2 = Integer.parseInt(operand2EditText.getText().toString());
            int result = 0;

            switch (selectedOperator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_LONG).show();
                        return;
                    }
                    break;
                case "^":
                    result = (int) Math.pow(operand1, operand2);
                    break;
                default:
                    Toast.makeText(this, "Invalid operator selected", Toast.LENGTH_LONG).show();
                    return;
            }

            resultTextView.setText(String.valueOf(result));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show();
        }
    }
}
