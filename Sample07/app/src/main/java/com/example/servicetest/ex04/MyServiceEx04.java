package com.example.servicetest.ex04;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyServiceEx04 extends Service {
    private final String TAG = "service";
    private ExecutorService es;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: Service work!");
        Toast toast = Toast.makeText(this, "onCreate: Service ex04 work!", Toast.LENGTH_SHORT);
        toast.show();
        es = Executors.newFixedThreadPool(2);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: Service destroy!");
        Toast toast = Toast.makeText(this, "onDestroy: Service ex04 destroy!", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public IBinder onBind(Intent intent){
        Log.d(TAG,"onBind: Service ex04 bind!");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: Service ex04 start Command");
        int time = intent.getIntExtra(MainActivity.PARAM_TIME,1);
        PendingIntent pi = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);

        MyRun mr= new MyRun(time,startId, pi);
        es.execute(mr);


        return super.onStartCommand(intent, flags, startId);
    }



    private class MyRun implements Runnable{
        private int time;
        private int startId;
        private PendingIntent pi;

        public MyRun(int time,int startId, PendingIntent pi){
            this.time = time;
            this.startId = startId;
            this.pi = pi;
            Log.d(TAG, "MyRun#"+ startId + " create");
        }
        void stop(){
            Log.d(TAG, "MyRun#"+ startId + " end, stopSelfResult("+ startId +") = "+ stopSelfResult(startId));
            ;
        }

        @Override
        public void run() {
            Log.d(TAG, "MyRun#"+ startId + " start, time =" + time);
            try{
                //сообщаем о старте задачи
                pi.send(MainActivity.STATUS_START);
                //начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                //сообщаем об выполнении задачи
                Intent intent = new Intent().putExtra(MainActivity.PARAM_PINTENT, time * 100);
                pi.send(MyServiceEx04.this, MainActivity.STATUS_FINISH, intent);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }catch (Exception ex){
                ex.printStackTrace();
            }

            stop();
        }
    }  /**/
}
