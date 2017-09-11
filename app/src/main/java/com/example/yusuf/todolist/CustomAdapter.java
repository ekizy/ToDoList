package com.example.yusuf.todolist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yusuf on 9/10/2017.
 */

public class CustomAdapter extends BaseAdapter {

    private LayoutInflater cInflater;
    private List<Task> taskList;

    public CustomAdapter(Activity activity, List <Task> tempList)
    {
        cInflater =(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        taskView=cInflater.inflate(R.layout.custom_element,null);

        TextView textView = (TextView) taskView.findViewById(R.id.text_custom_element);

        Task task =taskList.get(position);

        textView.setText(task.taskName);


        return taskView;
    }

}
