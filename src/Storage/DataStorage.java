package Storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import model.Task;

import java.util.ArrayList;
import java.util.List;


public class DataStorage extends SQLiteOpenHelper {
    Task task;
    Button setDate, setTime;
    EditText txtDate, txtTime;
    private static final String TABLE_NAME = "tasks";
    private static final String TITLE_COL = "task";
    private static final String TASK_TABLE_COL_ID = "taskId";
    private static final String TASK_DUE_DATE = "taskDueDate";
    private static final String DB_NAME = "todo.db";

    public DataStorage(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME +
                " (" + TASK_TABLE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITLE_COL +" TEXT, " +
                TASK_DUE_DATE +" TEXT NOT NULL);" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void store(Task task) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TITLE_COL, task.toString());
        cv.put(TASK_DUE_DATE, task.getDueDate());
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }

    public  List<Task> get() {

        List<Task> data = new ArrayList();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                data.add(task);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public List<String> getDateTime(){

        List<String> dateTime = new ArrayList();

        for (int i = 0; i < get().size(); i++) {
            Task task1 = get().get(i);
            String date = task1.getDueDate();
            dateTime.add(date);
        }
        return dateTime;
    }
}