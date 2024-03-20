// Assuming you already have a MainActivity.java to navigate between activities
package my.edu.utar.mathmobileapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ComparingNumbersActivity extends AppCompatActivity {

    private TextView textNumbers;
    private Button btnGreater;
    private Button btnLess;

    private int number1;
    private int number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparing_numbers);

        textNumbers = findViewById(R.id.textNumbers);
        btnGreater = findViewById(R.id.btnGreater);
        btnLess = findViewById(R.id.btnLess);

        generateNumbers();
    }

    private void generateNumbers() {
        Random random = new Random();
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        textNumbers.setText(getString(R.string.compare_numbers, number1, number2));
    }

    public void onSymbolButtonClick(View view) {
        Button clickedButton = (Button) view;
        String userSymbol = clickedButton.getText().toString();
        String correctSymbol = (number1 > number2) ? ">" : "<";

        if (userSymbol.equals(correctSymbol)) {
            // Correct answer
            textNumbers.setText(getString(R.string.correct_answer));

            // Proceed to the next set of numbers after a delay (you can customize this)
            view.postDelayed(this::generateNumbers, 1000);
        } else {
            // Incorrect answer, ask the user to try again
            textNumbers.setText(getString(R.string.try_again,number1,number2));
        }
    }
}
