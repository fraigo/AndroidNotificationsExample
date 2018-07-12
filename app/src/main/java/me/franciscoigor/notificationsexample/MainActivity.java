package me.franciscoigor.notificationsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import me.franciscoigor.notificationsexample.helpers.NotificationHelper;

public class MainActivity extends AppCompatActivity {

    NotificationHelper notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifications = new NotificationHelper(this);

        //notifications.notify(1001, "Test","Message", true);



        Button notifyButton = findViewById(R.id.notifyButton);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifications.notify(1001, "Test1","Message1", notifications.DEFAULT_CHANNEL);
            }
        });
        Button notifyButton2 = findViewById(R.id.notifyButton2);
        notifyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifications.showNotificationSettings(MainActivity.this,notifications.DEFAULT_CHANNEL);
            }
        });
        Button notifyButton3 = findViewById(R.id.notifyButton3);
        notifyButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifications.notify(1002, "Test1","Message1", notifications.URGENT_CHANNEL);
            }
        });
        Button notifyButton4 = findViewById(R.id.notifyButton4);
        notifyButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifications.showNotificationSettings(MainActivity.this,notifications.URGENT_CHANNEL);
            }
        });

    }
}
