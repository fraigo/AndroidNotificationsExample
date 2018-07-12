package me.franciscoigor.notificationsexample.helpers;


import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class NotificationHelper extends ContextWrapper {

    public static final String DEFAULT_CHANNEL = "Default";
    public static final String URGENT_CHANNEL = "Urgent";
    private NotificationManagerCompat manager;
    private PendingIntent appIntent;

    public NotificationHelper(Activity activity) {
        super(activity);
        manager = NotificationManagerCompat.from(getApplicationContext());
        appIntent = getApplicationIntent(activity);
        createNotificationChannel(DEFAULT_CHANNEL,DEFAULT_CHANNEL, NotificationManager.IMPORTANCE_DEFAULT);
        createNotificationChannel(URGENT_CHANNEL,URGENT_CHANNEL, NotificationManager.IMPORTANCE_MAX);

    }

    public PendingIntent getApplicationIntent(Activity activity){
        Intent contentIntent = new Intent(getApplicationContext(), activity.getClass());
        return PendingIntent.getActivity(getApplicationContext(), 0,
                contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public NotificationCompat.Builder getNotification(String title, String body, String channel) {

        return new NotificationCompat.Builder(getApplicationContext(),channel)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(appIntent)
                .setAutoCancel(true);
    }

    public void notify(int id, String title, String body, String channel) {
        notify(id, getNotification(title, body, channel) );
    }

    public void notify(int id, String title, String body) {
        notify(id, getNotification(title, body, DEFAULT_CHANNEL) );
    }

    public void notify(int id, NotificationCompat.Builder notification) {
        manager.notify(id, notification.build());
    }

    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }

    // Implementation based on https://stackoverflow.com/a/48861133/4195981
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationManager notificationManager= (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // Channel importance (3 means default importance)
        int channelImportance = importance;

        try {
            // Get NotificationChannel class via reflection (only available on devices running Android O or newer)
            Class notificationChannelClass = Class.forName("android.app.NotificationChannel");
            // Get NotificationChannel constructor
            Constructor<?> notificationChannelConstructor = notificationChannelClass.getDeclaredConstructor(String.class, CharSequence.class, int.class);
            // Instantiate new notification channel
            Object notificationChannel = notificationChannelConstructor.newInstance(channelId, channelName, channelImportance);
            // Get notification channel creation method via reflection
            Method createNotificationChannelMethod =  notificationManager.getClass().getDeclaredMethod("createNotificationChannel", notificationChannelClass);
            // Invoke method on NotificationManager, passing in the channel object
            createNotificationChannelMethod.invoke(notificationManager, notificationChannel);

        }
        catch (Exception exc) {
            // Log exception to console

        }

    }

    public void showNotificationSettings(Activity activity, String channelId){
        try{
            Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.getPackageName());
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelId);
            activity.startActivity(intent);
        }catch(Exception ex){
            Toast.makeText(activity.getApplicationContext(),"Channel configuration not available", Toast.LENGTH_SHORT).show();
        }

    }


}
