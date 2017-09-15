package com.example.yusuf.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yusuf on 9/10/2017.
 */

public class IncompleteTaskAdapter extends BaseAdapter {

    private LayoutInflater cInflater;
    private List<Task> taskList;
    private CheckBox checkBox;
    private  Activity activity;
    private Context context;
    private ImageView imageViewDelete;
    private ImageView imageViewEdit;
    public IncompleteTaskAdapter(Activity activity, List <Task> tempList,Context context)
    {
        cInflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity=activity;
        this.context=context;
        taskList= tempList;
    }

    @Override
    public int getCount()
    {
        return taskList.size();
    }

    @Override
    public Task getItem(int position)
    {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View taskView;
        taskView=cInflater.inflate(R.layout.incomplete_task_element,null);

        TextView textCustom = (TextView) taskView.findViewById(R.id.text_custom_element);
        TextView textDeadline=(TextView) taskView.findViewById(R.id.text_task_deadline);

        final Task task =taskList.get(position);

        checkBox =(CheckBox) taskView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    DBHelper dbHelper= new DBHelper(activity);
                    dbHelper.checkTask(task);
                    dbHelper.close();
                    Intent newIntent = new Intent(context.getApplicationContext(),MainActivity.class);
                    context.startActivity(newIntent);
                }
            }
        });

        imageViewDelete=(ImageView) taskView.findViewById(R.id.icon_delete);
        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper= new DBHelper(activity);
                dbHelper.deleteTask(task);
                dbHelper.close();
                Intent newIntent = new Intent(context.getApplicationContext(),MainActivity.class);
                context.startActivity(newIntent);
            }
        });

        imageViewEdit=(ImageView) taskView.findViewById(R.id.icon_edit);
        imageViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(context.getApplicationContext(),EditTaskActivity.class);
                newIntent.putExtra("oldTaskName",task.taskName);
                newIntent.putExtra("oldTaskDate",convertTimestampToDate(task.deadline));
                context.startActivity(newIntent);
            }
        });

        textCustom.setText(task.taskName);
        textDeadline.setText(convertTimestampToDate(task.deadline));

        return taskView;
    }

    public String convertTimestampToDate(Timestamp timestamp)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date temp = new Date(timestamp.getTime());
        String  date = dateFormat.format(temp);
        return date;
    }

}
