package com.change.toDo;

import android.app.*;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import repository.TaskRepository;

import java.util.Calendar;
import java.util.List;

public class AddNewTask extends Activity implements View.OnClickListener {

    Button setDate, setTime;
    EditText txtDate, txtTime;
//    static PendingIntent pendingIntent;
    static AlarmManager alarmManager;
    private TaskRepository taskRepository;
//    Context context;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        setTime = (Button) findViewById(R.id.setTime);
        setDate = (Button) findViewById(R.id.setDate);

        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);

        setDate.setOnClickListener(this);
        setTime.setOnClickListener(this);
    }

    public void addTask(View view) {
        taskRepository = new TaskRepository(getApplicationContext());
        EditText title = (EditText) findViewById(R.id.titleText);
        String taskTitle = title.getText().toString();

        String  date =  txtDate.getText().toString();
        String time = txtTime.getText().toString();
        String dateWithTime = date+" "+time;
        String taskDate = dateWithTime;

        taskRepository.createTask(taskTitle,taskDate);

        List<Long> dateInLong = taskRepository.getDateInLong();
        if (dateInLong.size() > 0) {
            for (int i = 0; i < dateInLong.size(); i++)
            {
                Intent intent = new Intent(this,
                        AlarmReceiver.class);
                intent.setData(Uri.parse("timer:" + dateInLong.get(i)));

                PendingIntent sender = PendingIntent.getBroadcast(
                        AddNewTask.this, 0, intent,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION);

                intent.setAction("com.change.toDo.ACTION");
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                alarmManager.set(AlarmManager.RTC_WAKEUP, dateInLong.get(i), sender);
            }
            Intent intent1 = new Intent(this, HomeActivity.class);
            startActivity(intent1);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == setDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    txtDate.setText(dayOfMonth + "-"
                            + (monthOfYear + 1) + "-" + year);
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (view == setTime) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}