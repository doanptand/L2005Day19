package com.ddona.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.ddona.broadcastreceiver.receiver.MyReceiver;
import com.ddona.broadcastreceiver.receiver.SecondReceiver;
import com.ddona.broadcastreceiver.receiver.ThreeReceiver;

public class MainActivity extends AppCompatActivity {
    private MyReceiver receiver;
    private SecondReceiver secondReceiver;
    private ThreeReceiver threeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver = new MyReceiver();
        secondReceiver = new SecondReceiver();
        threeReceiver = new ThreeReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction("com.ddona.myaction");
        filter.setPriority(100);
        registerReceiver(receiver, filter);

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter2.addAction(Intent.ACTION_SCREEN_OFF);
        filter2.addAction("com.ddona.myaction");
        filter2.setPriority(300);
        registerReceiver(secondReceiver, filter2);

        IntentFilter filter3 = new IntentFilter();
        filter3.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        filter3.setPriority(200);
        filter3.addAction(Intent.ACTION_SCREEN_OFF);
        filter3.addAction("com.ddona.myaction");
        registerReceiver(threeReceiver, filter3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}