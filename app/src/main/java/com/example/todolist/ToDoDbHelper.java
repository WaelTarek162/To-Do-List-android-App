package com.example.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ToDoDbHelper extends SQLiteOpenHelper {

    private static String DBname="toDoDB";
    SQLiteDatabase toDo;

    public ToDoDbHelper( Context context) {
        super(context, DBname, null, 1);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table Activity(activity text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists Acyivity ");
    }

    public void createTime(String t){

        ContentValues row =new ContentValues();
        row.put("activity",t);

        toDo=getWritableDatabase();
        toDo.insert("Activity",null,row);
        toDo.close();

    }
    public Cursor fitchActivities(){
        toDo=getReadableDatabase();
        String []rowDeetails={"activity"};
        Cursor cursor=toDo.query("Activity",rowDeetails,null,null,null,null,null);

        if(cursor!=null){


            cursor.moveToFirst();

        }

        toDo.close();
        return cursor;


    }
    public void deleteTime(String n){
        toDo=getWritableDatabase();
        toDo.delete("Activity","activity='"+n+"'",null);
        toDo.close();

    }
    public void upDateTime(String old,String nEw){
        toDo=getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("activity",nEw);
        toDo.update("Activity",row,"activity like ?",new String[]{old});
        toDo.close();

    }





}

