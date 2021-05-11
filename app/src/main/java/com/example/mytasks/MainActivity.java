package com.example.mytasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void newTask(View v){
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    public  void loadData(){
        //create Db helper with context passed
        TaskHelper dbHelper = new TaskHelper(this);

        // get SQLiteDatabase object
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // create sql query and execute and get the cursor object
        String sql = "SELECT _id, name, date FROM tasks where complete='0'";

        Cursor cursor = db.rawQuery(sql,null);

        //create an empty list object
        List<String> list = new ArrayList<String>();

        //Move cursor and get data to the list
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false ){ // anthimata passe kena
            String name = cursor.getString(1); // coloumn index eka
            String date = cursor.getString(2);
            list.add(name+ "("+date+")");
            cursor.moveToNext();
        }

        //Get list View Object
        ListView lv = findViewById(R.id.task_list);

//        //Create Adapter and set values to the list
//        int layout = android.R.layout.simple_list_item_1;
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, layout, list); // default hambawena array list eka


        int layout = R.layout.one_task;
        String []columns = {"name", "date", "_id"};
        int []lables = {R.id.task_name , R.id.task_date , R.id.task_id};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, layout,cursor,columns,lables);


        //set adapter to list view
        lv.setAdapter(adapter);
    }

    public void complete(View v){
        LinearLayout ll = (LinearLayout) v.getParent();
        TextView tvTaskId = ll.findViewById(R.id.task_id);
        String taskId = tvTaskId.getText().toString();

        //create open helper with context passed
        TaskHelper dbHelper = new TaskHelper(this);
        //Get writable database object
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // create insert sql and exec
        String sql = "UPDATE tasks SET complete = 1 WHERE _id='"+taskId+"'";
        db.execSQL(sql);
        loadData();
        Toast.makeText(this,"Your Task Completed",Toast.LENGTH_SHORT).show();




    }
}

