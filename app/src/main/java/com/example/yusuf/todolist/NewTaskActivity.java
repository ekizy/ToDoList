package com.example.yusuf.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewTaskActivity extends AppCompatActivity {

    private TextView mDisplayDate;
    private Button  saveButton;
    private TextView deadlineLabel;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText taskName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        saveButton = (Button) findViewById(R.id.save_button);
        deadlineLabel= (TextView) findViewById(R.id.deadline_textbox);
        mDisplayDate = (TextView) findViewById(R.id.pickdate);
        taskName =(EditText) findViewById(R.id.task_text);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        NewTaskActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
              String date= dayOfMonth +"/"+ month +"/" + year;
            mDisplayDate.setText(date);
            }
        };
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void saveButtonClicked(View view)
    {
        DBHelper dbHelper = new DBHelper(this);

        if(mDisplayDate.getText().toString()=="Select A Date") mDisplayDate.setText("1/1/1970");
        if(taskName.getText().toString()=="") taskName.setText("Unnamed Task");

        Task task = new Task();
        task.deadline=convertDateToTimestamp(mDisplayDate.getText().toString());
        task.taskName=taskName.getText().toString();
        task.isCompleted=0;

        dbHelper.insertNewTask(task);
        dbHelper.close();

        Intent newIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(newIntent);
    }

    public Timestamp convertDateToTimestamp(String date)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date tempDate = null;
        try {
            tempDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new Timestamp(tempDate.getTime());
    }

}
