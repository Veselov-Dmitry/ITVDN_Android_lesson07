package com.example.servicetest.ex03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyServiceEx03 extends Service {
    private final String TAG = "service";
    private Object someObject;
    private ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        Toast toast = Toast.makeText(this, "onCreate: Service ex03 work!", Toast.LENGTH_SHORT);
        toast.show();
        someObject = new Object();
        executorService = Executors.newFixedThreadPool(3);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
        Toast toast = Toast.makeText(this, "onDestroy: Service ex03 destroy!", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG,"onBind: Service ex03 bind!");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: Service ex03 start Command");
        int time = intent.getIntExtra("time",1);
        MyRun mr= new MyRun(time,startId);
        executorService.execute(mr);
        return super.onStartCommand(intent, flags, startId);
    }
    private class MyRun implements Runnable{
        private int time;
        private int startId;

        public MyRun(int time,int startId){
            this.time = time;
            this.startId = startId;
            Log.d(TAG, "MyRun#"+ startId + " create");
        }
        void stop(){
            Log.d(TAG, "MyRun#"+ startId + " end, stopSelf("+ startId +")");
            stopSelf(startId);
        }

        @Override
        public void run() {
            Log.d(TAG, "MyRun#"+ startId + " start, time =" + time);
            try{
                TimeUnit.SECONDS.sleep(time);
                Log.d(TAG, "MyRun#"+ startId + " someObject =" + someObject.getClass());
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }catch (NullPointerException ex){
                Log.d(TAG, "MyRun#"+ startId + " error, null pointer");
            }
            
            stop();
        }
    }  /**/

}
