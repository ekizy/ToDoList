package com.example.yusuf.todolist;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {

    private EditText taskName;
    private TextView taskDeadline;
    private String oldTaskName;
    private Timestamp oldTaskDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        taskName = (EditText) findViewById(R.id.task_text);
        taskDeadline = (TextView) findViewById(R.id.pickdate);

        Intent intent = getIntent();
        oldTaskName=intent.getStringExtra("oldTaskName");
        taskName.setText(oldTaskName);
        taskDeadline.setText(intent.getStringExtra("oldTaskDate"));
        oldTaskDate=convertDateToTimestamp(taskDeadline.getText().toString());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void editButtonClicked(View view) {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(taskName.getText().toString()=="") taskName.setText("Unnamed Task");
        cv.put("TaskName", taskName.getText().toString());
        cv.put("Deadline",convertDateToTimestamp(taskDeadline.getText().toString()).toString());
        db.update("Tasks", cv, "TaskName" + "= ?", new String[] {oldTaskName});
        db.close();

        Intent newIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(newIntent);
    }
}
