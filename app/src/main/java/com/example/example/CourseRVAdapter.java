package com.example.example;

import android.content.Context;
import android.content.Intent;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<TaskHandler> courseModalArrayList;
    private Context context;

    // constructor
    public CourseRVAdapter(ArrayList<TaskHandler> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        TaskHandler modal = courseModalArrayList.get(position);
        holder.taskNameTV.setText(modal.getTaskName());
        String formattedTimestamp = DateFormat.getDateTimeInstance().format(new Date(modal.getTimestamp()));
        holder.timestamp.setText(formattedTimestamp);

        holder.deleteIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dbHandler.deleteCourse(modal.getTaskName());
                Toast.makeText(context.getApplicationContext(), "Task Deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context.getApplicationContext(),MainActivity.class);
                context.startActivity(intent);


            }
        });

        holder.doneIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpannableString spannableString = new SpannableString(modal.getTaskName());
                spannableString.setSpan(new StrikethroughSpan(), 0, modal.getTaskName().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.taskNameTV.setText(spannableString);
            }
        });

        holder.editIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context.getApplicationContext(), UpdateTaskActivity.class);

                // below we are passing all our values.
                i.putExtra("taskname", modal.getTaskName());


                // starting our activity.
                context.startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView taskNameTV,timestamp;

        private ImageButton deleteIB, doneIB, editIB;

        private  DBHandler dbHandler;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            // initializing our text views
            taskNameTV = itemView.findViewById(R.id.idtaskName);
            deleteIB = itemView.findViewById(R.id.deleteIB);
            doneIB = itemView.findViewById(R.id.doneIB);
            editIB = itemView.findViewById(R.id.editIB);
            dbHandler = new DBHandler(context);
            timestamp = itemView.findViewById(R.id.timestamp_text_view);

        }
    }
}
