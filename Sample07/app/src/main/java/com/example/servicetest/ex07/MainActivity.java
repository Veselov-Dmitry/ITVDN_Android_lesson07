package com.example.servicetest.ex07;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.servicetest.R;

public class MainActivity extends Activity {
    private final String TAG = "binding";

    private  boolean bound;
    private ServiceConnection serviceConnection;
    private Intent intent;
    private MyServiceEx07 myService;
    private TextView internalText;
    private long interval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex07);
        internalText = (TextView) findViewById(R.id.textView);
        intent = new Intent(this, MyServiceEx07.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder binder) {
                Log.d(TAG, "MainActivity onServiceConnected");
                myService = ((MyServiceEx07.MyBinder) binder).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, serviceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bound){
            unbindService(serviceConnection);
            bound = false;
        }
    }

    public void onClickStart(View view){
        startService(intent);
    }
    public void onClickUp(View view){
        if (bound){
            interval = myService.upInterval(500);
            internalText.setText("inteval = " + interval);
        }
    }
    public void onClickDown(View view){
        if (bound){
            interval = myService.downInterval(500);
            internalText.setText("inteval = " + interval);
        }
    }
    public void onClickStop(View view){
        if (bound){
            unbindService(serviceConnection);
            bound = false;
        }
        stopService(intent);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        onClickStop(null);
    }
}

