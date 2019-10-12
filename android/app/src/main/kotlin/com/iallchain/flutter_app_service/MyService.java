package com.iallchain.flutter_app_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

import io.flutter.Log;

public class MyService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("1123", "onBind");
        initTime();
        return new MyBinder();
    }


    private void initTime() {
        Log.e("1123", "inittimer");
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (listener != null) {
                        Log.e("1123", "listener");
                        listener.str(System.currentTimeMillis() + "");
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public interface Listener {
        void str(String str);
    }

    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}
