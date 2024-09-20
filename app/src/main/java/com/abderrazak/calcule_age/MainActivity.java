package com.abderrazak.calcule_age;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText editTextDate;
    private TextView resultTextView;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextDate = findViewById(R.id.editTextDate);
        resultTextView = findViewById(R.id.resultTextView);
        submitButton = findViewById(R.id.submitButton);

        // Set click listener for button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dobInput = editTextDate.getText().toString();
                if (!dobInput.isEmpty()) {
                    try {
                        // Call method to calculate age
                        int age = calculateAge(dobInput);
                        resultTextView.setText("Your age is: " + age + " years");
                    } catch (ParseException e) {
                        Toast.makeText(MainActivity.this, "Invalid date format. Please use yyyy-MM-dd", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please enter your date of birth", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to calculate age from a date string in format yyyy-MM-dd
    private int calculateAge(String dobString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dob = sdf.parse(dobString);

        Calendar dobCalendar = Calendar.getInstance();
        dobCalendar.setTime(dob);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dobCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dobCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return age;
    }
}
