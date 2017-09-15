package com.example.yusuf.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Timestamp;
import java.util.ArrayList;



public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="ToDoListDB";
    private static final int DB_VER=1;
    private static final String DB_TABLE="Tasks";
    private static final String DB_COL1="TaskName";
    private static final String DB_COL2="CreationDate";
    private static final String DB_COL3="Deadline";
    private static final String DB_COL4="isCompleted";


    public DBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VER);
    }


    public void insertNewTask(Task task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COL1,task.taskName);
        values.put(DB_COL3,task.deadline.toString());
        values.put(DB_COL4,task.isCompleted);
        db.insertWithOnConflict(DB_TABLE,null,values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(Task task)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(DB_TABLE,DB_COL1+" =?",new String[]{task.taskName});
        db.close();
    }

    public void checkTask(Task task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isCompleted",1);
        db.update(DB_TABLE, cv, DB_COL1 + "= ?", new String[] {task.taskName});
    }

    public void uncheckTask(Task task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("isCompleted",0);
        db.update(DB_TABLE, cv, DB_COL1 + "= ?", new String[] {task.taskName});
    }

    public ArrayList<Task> getCompletedTasks()
    {
        ArrayList<Task> taskList= new ArrayList<Task>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.query(DB_TABLE,new String[]{"ID",DB_COL1,DB_COL2,DB_COL3,DB_COL4},null,null,null,null,DB_COL3+" ASC");

        while(cursor.moveToNext())
        {
            Task task = new Task();
            task.id=cursor.getInt(0);
            task.taskName=cursor.getString(1);
            task.creationTime= Timestamp.valueOf(cursor.getString(2));
            task.deadline= Timestamp.valueOf(cursor.getString(3));
            task.isCompleted=cursor.getInt(4);
            if(task.isCompleted==1) taskList.add(task);
        }
        return taskList;
    }

    public ArrayList<Task> getInCompleteTasks()
    {
        ArrayList<Task> taskList= new ArrayList<Task>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor= db.query(DB_TABLE,new String[]{"ID",DB_COL1,DB_COL2,DB_COL3,DB_COL4},null,null,null,null,DB_COL3+" ASC");

        while(cursor.moveToNext())
        {
            Task task = new Task();
            task.id=cursor.getInt(0);
            task.taskName=cursor.getString(1);
            task.creationTime= Timestamp.valueOf(cursor.getString(2));
            task.deadline= Timestamp.valueOf(cursor.getString(3));
            task.isCompleted=cursor.getInt(4);
            if(task.isCompleted==0) taskList.add(task);
        }
        return taskList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL, %s TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,%s TIMESTAMP NOT NULL," +
                "%s isCompleted INTEGER);",DB_TABLE,DB_COL1,DB_COL2,DB_COL3,DB_COL4);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query=String.format("DELETE TABLE IF EXISTS %s",DB_TABLE);
        db.execSQL(query);
    }
}
