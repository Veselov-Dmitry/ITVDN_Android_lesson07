package com.example.servicetest.ex05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyServiceEx05 extends Service {
    private final String TAG = "service";
    private ExecutorService es;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        Toast toast = Toast.makeText(this, "onCreate: Service ex05 work!", Toast.LENGTH_SHORT);
        toast.show();
        es = Executors.newFixedThreadPool(2);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
        Toast toast = Toast.makeText(this, "onDestroy: Service ex05 destroy!", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG,"onBind: Service ex05 bind!");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: Service ex05 start Command");
        int time = intent.getIntExtra(MainActivity.PARAM_TIME,1);
        int task = intent.getIntExtra(MainActivity.PARAM_TASK,1);

        MyRun mr= new MyRun(time,startId, task);
        es.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    private class MyRun implements Runnable{
        private int time;
        private int startId;
        private int task;

        public MyRun(int time,int startId, int task){
            this.time = time;
            this.startId = startId;
            this.task = task;
            Log.d(TAG, "MyRun#"+ startId + " create");
        }
        void stop(){
            Log.d(TAG, "MyRun#"+ startId + " end, stopSelfResult("+ startId +") = "+ stopSelfResult(startId));
        }

        @Override
        public void run() {
            Intent intent = new Intent(MainActivity.BROADCAST_ACTION);
            Log.d(TAG, "MyRun#"+ startId + " start, time =" + time);
            try{
                //сообщаем о старте задачи
                intent.putExtra(MainActivity.PARAM_TASK,task);
                intent.putExtra(MainActivity.PARAM_STATUS,MainActivity.STATUS_START);
                sendBroadcast(intent);

                //начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                //сообщаем об выполнении задачи
                intent.putExtra(MainActivity.PARAM_STATUS,MainActivity.STATUS_FINISH);
                intent.putExtra(MainActivity.PARAM_RESULT,time * 100);
                sendBroadcast(intent);
                
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }catch (Exception ex){
                ex.printStackTrace();
            }

            stop();
        }
    }  /**/
}
