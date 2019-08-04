package com.example.servicetest.ex06;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyServiceEx06 extends Service {
    private final String TAG = "binding";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        Toast toast = Toast.makeText(this, "onCreate: Service ex06 work!", Toast.LENGTH_SHORT);
        toast.show();                    /**/
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
        Toast toast = Toast.makeText(this, "onDestroy: Service ex06 destroy!", Toast.LENGTH_SHORT);
        toast.show();                  /**/
    }
    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG,"onBind: Service ex06 bind!");
        Toast toast = Toast.makeText(this, "onBind: Service ex06 bind!", Toast.LENGTH_SHORT);
        toast.show();                  /**/
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG,"onRebind: Service ex06 re bind!");
        Toast toast = Toast.makeText(this, "onRebind: Service ex06 re bind!", Toast.LENGTH_SHORT);
        toast.show();                  /**/
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind: Service ex06 un bind!");
        Toast toast = Toast.makeText(this, "onUnbind: Service ex06 un bind!", Toast.LENGTH_SHORT);
        toast.show();                  /**/
        return true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: Service ex06 start Command");
        return super.onStartCommand(intent, flags, startId);
    }
}
