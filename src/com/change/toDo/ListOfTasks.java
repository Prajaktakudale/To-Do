package com.change.toDo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import model.Task;
import repository.TaskRepository;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class ListOfTasks extends Activity {
    private TaskRepository taskRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        taskRepository = new TaskRepository(getApplicationContext());
        setContentView(R.layout.list_of_tasks);

        TextView views = (TextView) findViewById(R.id.task);

        List<Task> tasks;
        tasks = taskRepository.getTasks();

        StringBuilder data = new StringBuilder();

        for (Task task : tasks) {
            data.append("\n" + task.getId()+ ". " +task.toString()+"\n"+"Due Date and Time :  "+task.getDueDate() +"\n");
        }
        views.setText(data);
        views.setTextColor(Color.BLACK);


    }

    public void home(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}