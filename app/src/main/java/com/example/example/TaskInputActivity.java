package com.example.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TaskInputActivity extends AppCompatActivity {

    TextView tasktv;
    EditText tasket;
    Button addbtn;
    ImageButton backbtn;
    private DBHandler dbHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_input);

        dbHandler = new DBHandler(TaskInputActivity.this);


        tasktv = findViewById(R.id.taskTV);
        tasket = findViewById(R.id.taskET);
        addbtn = findViewById(R.id.addbtn);
        backbtn = findViewById(R.id.backbtn);




        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = tasket.getText().toString();
                long timestamp = System.currentTimeMillis(); // Get current timestamp


                if (task.isEmpty()){
                    Toast.makeText(TaskInputActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    dbHandler.addNewTask(task, timestamp);

                    Toast.makeText(TaskInputActivity.this, " Task added.", Toast.LENGTH_SHORT).show();
                    tasket.setText("");
                    Intent intent = new Intent(TaskInputActivity.this,MainActivity.class);
                    startActivity(intent);

                }




            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskInputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}