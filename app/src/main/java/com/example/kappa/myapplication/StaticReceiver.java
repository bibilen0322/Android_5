package com.example.kappa.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by kappa on 2016/10/23.
 */
public class StaticReceiver extends BroadcastReceiver {
    private static final String STATICACTION="com.example.myapplication.staticreceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(STATICACTION)) {

            Bundle bundle = intent.getExtras();
         //   final int id = bundle.getInt("pic");
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("靜態廣播")
                    .setTicker("您有一條新消息")
                    .setContentText(bundle.getString("fruitname"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),bundle.getInt("pic")))
                    //.setLargeIcon(icon).setSmallIcon(id)
                    .setSmallIcon(bundle.getInt("pic"))
                    .setAutoCancel(true);
            Intent m_intent = new Intent();
            m_intent.setClass(context,MainActivity.class);
            final PendingIntent Pendingintent = PendingIntent.getActivity(context,0,m_intent,0);
            builder.setContentIntent(Pendingintent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = builder.build();
                    manager.notify(0,notify);

        }
    }
}
