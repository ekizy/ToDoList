package com.example.yusuf.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yusuf on 9/11/2017.
 */

public class CompleteTaskAdapter extends BaseAdapter {

    private LayoutInflater cInflater;
    private List<Task> taskList;
    private ImageView imageViewDelete;
    private Activity activity;
    private Context context;
    private CheckBox checkBox;

    public CompleteTaskAdapter(Activity activity, List <Task> tempList,Context context)
    {
        cInflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity=activity;
        taskList= tempList;
        this.context=context;
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
        taskView=cInflater.inflate(R.layout.completed_task_element,null);

        TextView textView = (TextView) taskView.findViewById(R.id.text_custom_element);
        TextView textDeadline=(TextView) taskView.findViewById(R.id.text_task_deadline);

        final Task task =taskList.get(position);

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

        checkBox =(CheckBox) taskView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                {
                    DBHelper dbHelper= new DBHelper(activity);
                    dbHelper.uncheckTask(task);
                    dbHelper.close();
                    Intent newIntent = new Intent(context.getApplicationContext(),MainActivity.class);
                    context.startActivity(newIntent);
                }
            }
        });

        textView.setText(task.taskName);
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
