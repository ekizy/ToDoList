package com.example.yusuf.todolist;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Yusuf on 9/10/2017.
 */

public class Task {

    public int id;
    public String taskName;
    public Timestamp deadline;
    public Timestamp creationTime;
    public int isCompleted;

    public Task(String task_name)
    {
        taskName =task_name;
    }

    public Task(int id, String task_name, Timestamp deadline)
    {
        this.id=id;
        taskName=task_name;
        this.deadline=deadline;
        isCompleted=0;
    }
    public Task()
    {

    }
}
