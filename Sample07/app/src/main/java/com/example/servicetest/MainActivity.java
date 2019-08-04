package com.example.servicetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        Intent intent = null;
        switch (view.getId()) {
            case R.id.ex01:
                intent = new Intent(this, com.example.servicetest.ex01.MainActivity.class);
                break;
            case R.id.ex02:
                intent = new Intent(this, com.example.servicetest.ex02.MainActivity.class);
                break;
            case R.id.ex03:
                intent = new Intent(this, com.example.servicetest.ex03.MainActivity.class);
                break;
            case R.id.ex04:
                intent = new Intent(this, com.example.servicetest.ex04.MainActivity.class);
                break;
            case R.id.ex05:
                intent = new Intent(this, com.example.servicetest.ex05.MainActivity.class);
                break;
            case R.id.ex06:
                intent = new Intent(this, com.example.servicetest.ex06.MainActivity.class);
                break;
            case R.id.ex07:
                intent = new Intent(this, com.example.servicetest.ex07.MainActivity.class);
                break;
            case R.id.ex08:
                intent = new Intent(this, com.example.servicetest.ex08.MainActivity.class);
                break;
            case R.id.ex09:
                intent = new Intent(this, com.example.servicetest.ex09.MainActivity.class);
                break;
        }
        startActivity(intent);
    }
}
