package com.example.servicetest.ex08;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.servicetest.R;

import java.util.concurrent.TimeUnit;

public class MyServiceEx08 extends Service {
    private final String TAG = "service";
    private NotificationManager nm;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
    }
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: Service ex08 start Command");
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        Toast.makeText(this, "sendNotification: Service ex08 stopSelf", Toast.LENGTH_SHORT).show();
        stopSelf();
        sendNotification();/**/
        return super.onStartCommand(intent, flags, startId);
    }
    private void sendNotification(){
        // 1-я часть
        Notification.Builder builder = new Notification.Builder(this);

        // 3-я часть
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FILE_NAME, "somevalue:" + System.currentTimeMillis());
        PendingIntent pIntent = PendingIntent.getActivity(this, 0 , intent, 0);


        builder.setContentIntent(pIntent)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentTitle("Title")
                .setContentText("Text")
                .setTicker("New notification");
        Notification notificacion = builder.build();

        // 2-я часть

        //ставим флаг, что бы уведомление пропало после нажатия
        notificacion.flags |= Notification.FLAG_AUTO_CANCEL;

        //отправляем
        nm.notify(1, notificacion);/**/

    }
}
