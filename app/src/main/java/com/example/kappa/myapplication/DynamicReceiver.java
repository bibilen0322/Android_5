package com.example.kappa.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Created by kappa on 2016/10/23.
 */
public class DynamicReceiver extends BroadcastReceiver{
    private static final String DYNAMICACTION="com.example.myapplication.dynamicreceiver";
    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(DYNAMICACTION)){
            RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.widget_demo);

            Bundle bundle = intent.getExtras();

            rv.setImageViewResource(R.id.imageView,R.mipmap.dynamic);  //接收dynamic數據
            rv.setTextViewText(R.id.appwidget_text,bundle.getString("word"));



            Intent clickint = new Intent(context,MainActivity.class);  //點widget跳回主畫面
            PendingIntent pi = PendingIntent.getActivity(context,0,clickint,0);
            rv.setOnClickPendingIntent(R.id.imageView,pi);

            AppWidgetManager appWidgetManagerr = AppWidgetManager.getInstance(context);  // 更新
            int[] widgetIDs = appWidgetManagerr.getAppWidgetIds(new ComponentName(context,WidgetDemo.class));
            appWidgetManagerr.updateAppWidget( widgetIDs,rv);

            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("動態廣播")
                    .setTicker("您有一條新消息")
                    .setContentText(bundle.getString("word"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.dynamic))
                        .setSmallIcon(R.mipmap.dynamic)
                    .setAutoCancel(true);

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = builder.build();
            manager.notify(0,notify);
            Intent m_intent = new Intent(); //和靜態廣播完全相同
            m_intent.setClass(context,MainActivity.class);
            final PendingIntent Pendingintent = PendingIntent.getActivity(context,0,m_intent,0);
            builder.setContentIntent(Pendingintent);

        /*    Intent mm_intent = new Intent(context,MainActivity.class);
            final PendingIntent Pendingintent = PendingIntent.getActivity(context,0,mm_intent,0);
            builder.setContentIntent(Pendingintent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notify = builder.build();
            manager.notify(0,notify);  */

        }
    }

}
