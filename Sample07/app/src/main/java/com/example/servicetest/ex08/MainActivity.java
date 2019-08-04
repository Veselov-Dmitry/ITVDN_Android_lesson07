package com.example.servicetest.ex08;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.servicetest.R;

public class MainActivity  extends Activity {

    public final static String FILE_NAME = "fileName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex08);

        TextView textView = (TextView) findViewById(R.id.textex08);

        Intent intent = getIntent();

        String fileName = intent.getStringExtra(FILE_NAME);
        if (!TextUtils.isEmpty(fileName)){
            textView.setText(fileName);
        }
    }

    public void onClickStart(View v){

        startService(new Intent(this,MyServiceEx08.class));
    }
    public void onClickStop(View v){
        stopService(new Intent(this,MyServiceEx08.class));
    }
}
