package com.change.toDo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }

    public void add(View view) {
        Intent intent = new Intent(this, AddNewTask.class);
        startActivity(intent);
        finish();
    }

    public void list(View view) {
        Intent intent = new Intent(this, ListOfTasks.class);
        startActivity(intent);
        finish();
    }
}