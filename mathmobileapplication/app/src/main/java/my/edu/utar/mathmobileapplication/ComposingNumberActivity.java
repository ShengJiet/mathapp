package my.edu.utar.mathmobileapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComposingNumberActivity extends AppCompatActivity {

    private TextView textRandomNumber;
    private GridLayout gridNumbers;
    private EditText editTextNumber1;
    private EditText editTextNumber2;

    private List<Button> numberButtons;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;


    private Button btnClear;
    private Button btnCheckAnswer;

    private int targetNumber;
    private int selectedNumber1;
    private int selectedNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_composing_numbers);
        textRandomNumber = findViewById(R.id.textRandomNumber);
        gridNumbers = findViewById(R.id.gridNumbers);
        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);



        // Initialize number buttons
        btnNumber1 = findViewById(R.id.btnNumber1);
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);

        numberButtons = Arrays.asList(btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5);

        // Initialize clear, and check answer buttons
        btnClear = findViewById(R.id.btnClear);
        btnCheckAnswer = findViewById(R.id.btnCheckAnswer);

        generateExercise();
    }

    private void generateExercise() {
        Random random = new Random();
        targetNumber = random.nextInt(100);

        // Generate two random numbers that add up to targetNumber
        int number1 = random.nextInt(targetNumber);
        int number2 = targetNumber - number1;
        selectedNumber1 = -1; // Initialize to -1
        selectedNumber2 = -1; // Initialize to -1

        textRandomNumber.setText(getString(R.string.compose_numbers_exercise, targetNumber));

        // Display the shuffled numbers on buttons
        for (int i = 0; i < numberButtons.size(); i++) {
            numberButtons.get(i).setText(String.valueOf(random.nextInt(100)));
        }

        // Set the generated numbers on two random buttons
        int randomButtonIndex1 = random.nextInt(numberButtons.size());
        int randomButtonIndex2;
        do {
            randomButtonIndex2 = random.nextInt(numberButtons.size());
        } while (randomButtonIndex2 == randomButtonIndex1);

        numberButtons.get(randomButtonIndex1).setText(String.valueOf(number1));
        numberButtons.get(randomButtonIndex2).setText(String.valueOf(number2));

        // Reset the EditText views
        editTextNumber1.setText("");
        editTextNumber2.setText("");
    }



    public void onNumberButtonClick(View view) {
        Button clickedButton = (Button) view;

        if (selectedNumber1 == -1) {
            selectedNumber1 = Integer.parseInt(clickedButton.getText().toString());
            editTextNumber1.setText(String.valueOf(selectedNumber1));
        } else if (selectedNumber2 == -1) {
            selectedNumber2 = Integer.parseInt(clickedButton.getText().toString());
            editTextNumber2.setText(String.valueOf(selectedNumber2));
        }
    }

    public void onClearButtonClick(View view) {
        // Implement logic to clear both selected numbers
        selectedNumber1 = -1;
        selectedNumber2 = -1;
        editTextNumber1.setText("");
        editTextNumber2.setText("");
    }

    public void onCheckAnswerButtonClick(View view) {
        // Check if both numbers are selected
        if (selectedNumber1 != -1 && selectedNumber2 != -1) {
            int sum = selectedNumber1 + selectedNumber2;

            // Check if the sum matches the targetNumber
            if (sum == targetNumber) {
                // Correct Answer
                Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();

                // Display the next question
                generateExercise();
            } else {
                // Incorrect Answer, try again
                Toast.makeText(this, R.string.incorrect_answer_message, Toast.LENGTH_SHORT).show();
            }
        } else {
            // User needs to select two numbers first
            Toast.makeText(this, R.string.select_two_numbers, Toast.LENGTH_SHORT).show();
        }
    }


}
