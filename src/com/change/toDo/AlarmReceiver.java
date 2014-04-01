package com.change.toDo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {


    private final String SOMEACTION = "com.change.toDo.ACTION";
    @Override
    public void onReceive(Context context, Intent intent) {
        generateNotification(context,"Due date is over");
        String action = intent.getAction();
        if (SOMEACTION.equals(action)) {
            generateNotification(context,"Due date is over");
        }
    }

    @SuppressWarnings("deprecation")
    private void generateNotification(Context context, String message) {

        int icon = R.drawable.to_do_icon;
        long  when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        String subTitle = "Task Due Date is over";
        Intent notificationIntent = new Intent(context, ListOfTasks.class);
        notificationIntent.putExtra("content", message);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        notification.setLatestEventInfo(context, title, subTitle, intent);

        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);
    }

}