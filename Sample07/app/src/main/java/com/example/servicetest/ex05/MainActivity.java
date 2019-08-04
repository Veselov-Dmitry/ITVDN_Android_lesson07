package com.example.servicetest.ex05;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.servicetest.R;

public class MainActivity extends Activity {
    private final String TAG ="broadcast";

    private final int TASK1_CODE = 1;
    private final int TASK2_CODE = 2;
    private final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String  PARAM_TIME = "time";
    public final static String  PARAM_TASK = "task";
    public final static String  PARAM_RESULT = "result";
    public final static String  PARAM_STATUS = "status";

    public final static String  BROADCAST_ACTION = "com.example.servicetest";

    private TextView textTask1;
    private TextView textTask2;
    private TextView textTask3;
    private BroadcastReceiver broadcast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex05);
        textTask1 = (TextView)findViewById(R.id.textTask1);
        textTask2 = (TextView)findViewById(R.id.textTask2);
        textTask3 = (TextView)findViewById(R.id.textTask3);
        textTask1.setText("Task1");
        textTask2.setText("Task2");
        textTask3.setText("Task3");
        //создаем BroadcastReceiver
        broadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(TAG, "onReceive: task = " + task + ", status = " + status);
                //ловим сообщение о старте задач
                if(status == STATUS_START){
                    switch (task){
                        case TASK1_CODE:{
                            textTask1.setText("Task1 start");
                            break;
                        }
                        case TASK2_CODE:{
                            textTask1.setText("Task2 start");
                            break;
                        }
                        case TASK3_CODE:{
                            textTask1.setText("Task3 start");
                            break;
                        }
                    }

                }
                //ловим сообщение об окончании задач
                if (status == STATUS_FINISH){
                    int result = intent.getIntExtra(PARAM_RESULT,0);
                    switch (task){
                        case TASK1_CODE:{
                            textTask1.setText("Task1 finish, result = " + result);
                            break;
                        }
                        case TASK2_CODE:{
                            textTask2.setText("Task2 finish, result = " + result);
                            break;
                        }
                        case TASK3_CODE:{
                            textTask3.setText("Task3 finish, result = " + result);
                            break;
                        }
                    }
                }
                 //создаем фильтр для  BroadcastReceiver
                IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
                // регистрируем (включаем) BroadcastReceiver
                registerReceiver(broadcast,intentFilter);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // дерегистрируем (выключаем) BroadcastReceiver
        unregisterReceiver(broadcast);
    }

    public void click(View v){
        //Создаем Intent для вызова сервиса,
        // кладем туда параметр времени и код задачи
        Intent intent = new Intent(this, MyServiceEx05.class).putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_TASK,TASK1_CODE);
        //стартуем сервис
        startService(intent);

        intent = new Intent(this, MyServiceEx05.class).putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_TASK,TASK2_CODE);
        startService(intent);

        intent = new Intent(this, MyServiceEx05.class).putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_TASK,TASK3_CODE);
        startService(intent);

    }
}
