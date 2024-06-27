package com.example.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateTaskActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText taskNameEdt;
    private Button edtbtn;
    ImageButton backbtn;
    private DBHandler dbHandler;
    String TaskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        // initializing all our variables.
        taskNameEdt = findViewById(R.id.edittaskET);
        edtbtn =findViewById(R.id.edtbtn);
        backbtn = findViewById(R.id.backbtn);

        // on below line we are initializing our dbhandler class.
        dbHandler = new DBHandler(UpdateTaskActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.



        TaskName = getIntent().getStringExtra("taskname");

        // setting data to edit text
        // of our update activity.
        taskNameEdt.setText(TaskName);

        long timestamp = System.currentTimeMillis(); // Get current timestamp


        // adding on click listener to our update course button.
        edtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UpTask = taskNameEdt.getText().toString();

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateCourse(TaskName, UpTask, timestamp);

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateTaskActivity.this, "Task Edited..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateTaskActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateTaskActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
