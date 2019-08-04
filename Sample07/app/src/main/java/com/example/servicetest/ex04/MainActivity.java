package com.example.servicetest.ex04;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.servicetest.R;

public class MainActivity extends Activity {

    private final String TAG ="pending";

    private final int TASK1_CODE = 1;
    private final int TASK2_CODE = 2;
    private final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String  PARAM_TIME = "time";
    public final static String  PARAM_PINTENT = "pendingIntent";
    public final static String  PARAM_RESULT = "result";

    private TextView textTask1;
    private TextView textTask2;
    private TextView textTask3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex04);
        textTask1 = (TextView)findViewById(R.id.textTask1);
        textTask2 = (TextView)findViewById(R.id.textTask2);
        textTask3 = (TextView)findViewById(R.id.textTask3);
        textTask1.setText("Task1");
        textTask2.setText("Task2");
        textTask3.setText("Task3");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode = "+ requestCode + ", resultCode =" + resultCode) ;

        //ловим сообщение о старте задач
        if (resultCode == STATUS_START){
            switch (requestCode){
                case TASK1_CODE:{
                    textTask1.setText("Task1 stars");
                    break;
                }
                case TASK2_CODE:{
                    textTask2.setText("Task2 stars");
                    break;
                }
                case TASK3_CODE:{
                    textTask3.setText("Task3 stars");
                    break;
                }
            }
        }
        //ловим сообщение об окончании задач
        if (resultCode == STATUS_FINISH){
            int result = data.getIntExtra(PARAM_RESULT,0);
            switch (requestCode){
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
    }

    public void click(View v){
        PendingIntent pi;
        Intent intent = new Intent(this, MyServiceEx04.class);

        //Создаем PendingIntent для Task1
        pi = createPendingResult(TASK1_CODE,intent, 0);
        //Создаем Intent для вызова сервиса, кладем туда параметр времени
        //и созданный PendingIntent
        intent.putExtra(PARAM_TIME, 7)
                .putExtra(PARAM_PINTENT, pi);
        //стартуем сервис
        startService(intent);

        pi = createPendingResult(TASK2_CODE, intent, 0);
        intent.putExtra(PARAM_TIME, 4)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);

        pi = createPendingResult(TASK3_CODE, intent, 0);
        intent.putExtra(PARAM_TIME, 6)
                .putExtra(PARAM_PINTENT, pi);
        startService(intent);
    }
}
