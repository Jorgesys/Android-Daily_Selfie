package com.jorgesys.dailyselfie;


import com.jorgesys2.dailyselfie.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

	private static final String TAG = "AlarmReceiver";
	public static final int NOTIFICATION_ID = 12345;

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG,"received intent broadcast from alarm");
		PendingIntent pendingIntent = PendingIntent.getActivity(
				context.getApplicationContext(), 
				0, 
				new Intent(context.getApplicationContext(), MainActivity.class), 
				Intent.FLAG_ACTIVITY_NEW_TASK );
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
		Notification notification = new Notification.Builder(context.getApplicationContext())
		.setContentTitle("Selfie")
		.setContentText("Time for a daily selfie")
		.setSmallIcon(R.drawable.ic_launcher)
		.setTicker("selfie time")
		.setContentIntent(pendingIntent)
		.setAutoCancel(true)
		.build();
		notification.defaults |= Notification.DEFAULT_SOUND;
		notificationManager.notify(NOTIFICATION_ID, notification);

	}

	/*
    	// Prepare intent which is triggered if the notification is selected
        Intent selfiesActivityIntent = new Intent(context, MainActivity.class);
        selfiesActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, selfiesActivityIntent, 0);

        // Build the notification
        Notification notification = new Notification.Builder(context)

            .setContentTitle(context.getString(R.string.app_name))
            .setContentText(context.getString(R.string.notification_message))
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentIntent(pendingIntent)
            .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // hide the notification after its selected
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }*/
}
