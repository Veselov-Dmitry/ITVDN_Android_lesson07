package com.example.servicetest.ex07;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyServiceEx07 extends Service {

    private final String TAG = "bibding";

    private MyBinder binder = new MyBinder();

    private Timer timer;
    private TimerTask timerTask;
    private long interval = 1000;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        Toast toast = Toast.makeText(this, "onCreate: Service ex07 work!", Toast.LENGTH_SHORT);
        toast.show();
        timer = new Timer();
        schedule();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
        Toast toast = Toast.makeText(this, "onDestroy: Service ex07 destroy!", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG,"onBind: Service ex07 bind!");
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: Service ex07 start Command");
        return super.onStartCommand(intent, flags, startId);
    }

    public void schedule(){
        if (timerTask != null) timerTask.cancel();
        if (interval > 0){
            timerTask =  new TimerTask(){
                public void run(){
                    Log.d(TAG, "run");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    public long upInterval(long gap){
        interval += gap;
        schedule();
        return interval;
    }
    public long downInterval(long gap){
        interval -= gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    public class MyBinder extends Binder {
        public MyServiceEx07 getService(){
            return MyServiceEx07.this;
        }
    }
}

