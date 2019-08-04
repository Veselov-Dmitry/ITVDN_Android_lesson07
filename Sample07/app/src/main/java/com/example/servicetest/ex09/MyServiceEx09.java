package com.example.servicetest.ex09;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.servicetest.R;

public class MyServiceEx09 extends Service {
    private MediaPlayer player;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast toast = Toast.makeText(this, "onCreate: Service ex09 work!", Toast.LENGTH_SHORT);
        toast.show();
        player = MediaPlayer.create(this, R.raw.song);
        player.setLooping(false);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy: Service ex09 destroy!", Toast.LENGTH_SHORT).show();
        player.stop();
    }
    @Override
    public IBinder onBind(Intent intent){return null;}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }
}
