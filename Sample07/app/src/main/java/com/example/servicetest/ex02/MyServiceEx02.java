package com.example.servicetest.ex02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MyServiceEx02 extends Service {
    private final String TAG = "service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        Toast toast = Toast.makeText(this, "onCreate: Service ex02 work!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
        Toast toast = Toast.makeText(this, "onDestroy: Service ex02 destroy!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(TAG,"onBind: Service ex02 bind!");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        taskThread();
        //taskT();
        return super.onStartCommand(intent, flags, startId);
    }

    public void task(){
        for(int i = 0; i <= 5; i++){
            Log.d(TAG, "i = "+ i);
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
        stopSelf();
    }
    public void taskThread(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i <= 5; i++){
                    Log.d(TAG, "i = "+ i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();


    }
}
