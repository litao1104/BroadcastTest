package com.example.broadcasttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;

    private LocalReiceiver localReiceiver;

    private LocalBroadcastManager localBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcasttest.LOCAL_BROADCAST");
        localReiceiver = new LocalReiceiver();
        localBroadcastManager.registerReceiver(localReiceiver, intentFilter);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("litao", "button onClick");

                Intent intent = new Intent("com.example.broadcasttest.LOCAL_BROADCAST");
                intent.setPackage("com.example.broadcasttest");
                localBroadcastManager.sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReiceiver);
    }

    class LocalReiceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}