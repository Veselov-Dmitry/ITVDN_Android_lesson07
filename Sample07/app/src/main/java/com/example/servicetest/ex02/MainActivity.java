package com.example.servicetest.ex02;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.example.servicetest.R;

public class MainActivity extends Activity {



    public void click(View view) {

        switch (view.getId()){
            case R.id.start_service:
                startService(new Intent(this, MyServiceEx02.class));
                break;
            case R.id.stop_service:
                stopService(new Intent(this, MyServiceEx02.class));
                break;
            default:

        }
    }
}
