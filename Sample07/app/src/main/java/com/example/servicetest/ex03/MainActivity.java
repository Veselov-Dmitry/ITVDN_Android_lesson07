package com.example.servicetest.ex03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.servicetest.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex03);
    }

    public void click(View view) {

        startService(new Intent(this,MyServiceEx03.class).putExtra("time", 6));
        startService(new Intent(this,MyServiceEx03.class).putExtra("time", 1));
        startService(new Intent(this,MyServiceEx03.class).putExtra("time", 2));
    }
}
