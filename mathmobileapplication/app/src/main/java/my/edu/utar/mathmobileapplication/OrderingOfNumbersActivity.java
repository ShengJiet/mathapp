package my.edu.utar.mathmobileapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class OrderingOfNumbersActivity extends AppCompatActivity {

    private TextView textOrderMessage;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private EditText editTextOrder;

    private List<Button> numberButtons;
    private Button btnDelete;
    private Button btnClear;

    private List<Integer> numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_of_numbers);

        textOrderMessage = findViewById(R.id.textOrderMessage);
        btnNumber1 = findViewById(R.id.btnNumber1);
        btnNumber2 = findViewById(R.id.btnNumber2);
        btnNumber3 = findViewById(R.id.btnNumber3);
        btnNumber4 = findViewById(R.id.btnNumber4);
        btnNumber5 = findViewById(R.id.btnNumber5);
        editTextOrder = findViewById(R.id.editTextOrder);

        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);

        numberButtons = Arrays.asList(btnNumber1, btnNumber2, btnNumber3, btnNumber4, btnNumber5);

        generateExercise();
    }

    private void generateExercise() {
        Random random = new Random();

        // Generate five random numbers between 1 and 999
        numbers = Arrays.asList(random.nextInt(999) + 1, random.nextInt(999) + 1,
                random.nextInt(999) + 1, random.nextInt(999) + 1, random.nextInt(999) + 1);

        Collections.shuffle(numbers);

        // Display the order message
        textOrderMessage.setText(getString(R.string.make_numbers_ascending_order));

        // Display the shuffled numbers on buttons
        for (int i = 0; i < numberButtons.size(); i++) {
            numberButtons.get(i).setText(String.valueOf(numbers.get(i)));
        }

        // Reset the editTextOrder
        editTextOrder.setText("");
    }

    public void onNumberButtonClick(View view) {
        Button clickedButton = (Button) view;
        String currentOrder = editTextOrder.getText().toString().trim(); // Trim to remove leading/trailing spaces
        String buttonValue = clickedButton.getText().toString();

        // Append the clicked number to the editTextOrder with a space
        if (!currentOrder.isEmpty()) {
            editTextOrder.setText(currentOrder + " " + buttonValue);
        } else {
            editTextOrder.setText(buttonValue);
        }
    }

    public void onDeleteButtonClick(View view) {
        String currentOrder = editTextOrder.getText().toString();

        // Remove the last digit from the editTextOrder
        if (!currentOrder.isEmpty()) {
            String updatedOrder = currentOrder.substring(0, currentOrder.length() - 1);
            editTextOrder.setText(updatedOrder);
        }
    }

    public void onClearButtonClick(View view) {
        // Clear the editTextOrder
        editTextOrder.setText("");
    }

    public void onCheckOrderClick(View view) {
        String userOrder = editTextOrder.getText().toString();
        List<String> userOrderList = Arrays.asList(userOrder.split(" "));

        // Check if the user's order matches the ascending order
        if (isCorrectOrder(userOrderList)) {
            textOrderMessage.setText(getString(R.string.correct_ordering_numbers));
            Toast.makeText(this, R.string.correct_ordering_numbers, Toast.LENGTH_SHORT).show();
            // Display a new exercise
            generateExercise();
        } else {
            textOrderMessage.setText(getString(R.string.incorrect_ordering_numbers));
            Toast.makeText(this, R.string.incorrect_ordering_numbers, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isCorrectOrder(List<String> userOrder) {
        List<Integer> sortedOrder = new ArrayList<>(numbers);
        Collections.sort(sortedOrder);

        List<Integer> userOrderInt = new ArrayList<>();
        for (String s : userOrder) {
            try {
                userOrderInt.add(Integer.parseInt(s.trim()));
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                e.printStackTrace();
            }
        }

        return userOrderInt.equals(sortedOrder);
    }

}
