package repository;

import Storage.DataStorage;
import android.content.Context;
import model.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRepository {
    private DataStorage dataStorage;
    DateFormat formatter = null;
    Date convertedDate = null;

    public TaskRepository(Context context) {
        dataStorage = new DataStorage(context);
    }

    public void createTask(String name,String date) {
        Task task = new Task(name);
        task.setDueDate(date);
        dataStorage.store(task);
    }

    public List<Task> getTasks() {
        return dataStorage.get();
    }

    public List<String> getTime(){
        return dataStorage.getDateTime();
    }

    public List<Date> giveDateList(){

        List<String> datesList = getTime();

        List<Date> dateAndTimeList = new ArrayList<Date>();
        for (int i = 0; i <datesList.size() ; i++) {
            String eachDate = datesList.get(i);
            formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

            try {
                convertedDate = formatter.parse(eachDate);
                dateAndTimeList.add(convertedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateAndTimeList;
    }

    public List<Long> getDateInLong(){

        List<Date> listOfDate = giveDateList();
        List<Long> longList = new ArrayList<Long>();

        for (int i = 0; i <listOfDate.size(); i++) {
            Date dateinLong = listOfDate.get(i);
            long finalLong = dateinLong.getTime();
            longList.add(finalLong);
    }
        return longList;
    }
}