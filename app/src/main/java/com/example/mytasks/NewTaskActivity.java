package com.example.mytasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewTaskActivity extends AppCompatActivity {

    EditText etName,etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        etName = findViewById(R.id.task_name);
        etDate = findViewById(R.id.task_date);
    }

    public void clear (View v){
        etName.setText("");
        etDate.setText("");
    }

    public void save(View v){
        String name = etName.getText().toString();
        String date = etDate.getText().toString();

        //create open helper with context passed
        TaskHelper dbHelper = new TaskHelper(this);
        //Get writable database object
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // create insert sql and exec
        String sql = "INSERT INTO tasks (name,date, complete) VALUES ('"+name+"', '"+date+"' , '  0'')";
        db.execSQL(sql);
        //give a message
        Toast.makeText(this, "saved successfully." , Toast.LENGTH_SHORT).show();
        clear(v);

    }
}
