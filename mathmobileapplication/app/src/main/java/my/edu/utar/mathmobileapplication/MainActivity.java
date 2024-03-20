package my.edu.utar.mathmobileapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openComparingNumbers(View view) {
        startActivity(new Intent(this, ComparingNumbersActivity.class));
    }

    public void openOrderingOfNumbers(View view) {
        startActivity(new Intent(this, OrderingOfNumbersActivity.class));
    }

    public void openComposingNumbers(View view) {
        startActivity(new Intent(this, ComposingNumberActivity.class));
    }
}
