package com.al_qatawi.theprophet.modle;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ForegroundInfo;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.al_qatawi.theprophet.MainActivity;
import com.al_qatawi.theprophet.R;
import com.al_qatawi.theprophet.db.AccesDataBase;
import com.al_qatawi.theprophet.db.Control_Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyWorker extends Worker {
    private static final String TAG = "MyWorker";
    public static final String CHANNEL_ID = "h_channel_id";



    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        AccesDataBase accesDataBase = AccesDataBase.getInstance(getApplicationContext());
        ArrayList<Content> contents = new ArrayList<>();
        accesDataBase.open();
        contents = accesDataBase.getData(Control_Database.TABLE_H);
        accesDataBase.close();

        int number = contents.size();

        int num = (int) Math.round(Math.random()*number);


            String h = String.valueOf(contents.get(num).getTEXT());


            Log.d(TAG, "hhh doWork: "+num + " :  "+h);

             showNotification(h);

        return Result.success();

    }

    public void showNotification(String text){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"HASSAN",NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
               builder.setSmallIcon(R.drawable.ic_baseline_notifications_24)
                       .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.forgraen))
                       .setContentTitle("حبيب الله")
                       .setContentText(text)
                       .setStyle(new NotificationCompat.BigTextStyle().bigText(text))
                       .setPriority(NotificationCompat.PRIORITY_HIGH)
                       .setContentIntent(pendingIntent)
                       .setAutoCancel(true);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(getApplicationContext());
            nmc.notify(11,builder.build());

    }

}
