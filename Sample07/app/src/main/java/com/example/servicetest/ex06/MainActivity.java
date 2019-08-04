package com.example.servicetest.ex06;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.servicetest.R;

public class MainActivity extends Activity {

    private final String TAG = "binding";

    private Intent intent;
    private boolean bound = false;
    public ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex06);

        intent = new Intent(this,MyServiceEx06.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "MainActivity onServiceConnection");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "MainActivity onServiceDisconnection");
                bound = false;
            }
        };
    }
    public void onClickStart(View view){
        startService(intent);
    }
    public void onClickStop(View view){
        stopService(intent);
    }
    public void onClickBind(View view){
        bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
    }
    public void onClickUnBind(View view){
        if (bound){
            unbindService(serviceConnection);
            bound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onClickUnBind(null);
    }
}
