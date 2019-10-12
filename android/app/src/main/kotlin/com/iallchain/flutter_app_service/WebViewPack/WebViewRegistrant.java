package com.iallchain.flutter_app_service.WebViewPack;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.iallchain.flutter_app_service.MyService;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

import static android.content.Context.BIND_AUTO_CREATE;

public class WebViewRegistrant implements MethodChannel.MethodCallHandler {
    private PluginRegistry.Registrar registrar;
    private Activity activity;
    private MethodChannel channel;

    public WebViewRegistrant() {

    }

    private WebViewRegistrant(PluginRegistry.Registrar registrar, Activity activity, MethodChannel channel) {
        this.registrar = registrar;
        this.activity = activity;
        this.channel = channel;
        initService();
    }

    private void initService() {
        Log.e("1123", "start bind");
        Intent intent = new Intent(activity, MyService.class);
        activity.bindService(intent, conn, BIND_AUTO_CREATE);
        Log.e("1123", "hava bind");
    }

    public void registerWith(Activity activity, PluginRegistry registry) {
        final String key = WebViewRegistrant.class.getCanonicalName();
        if (registry.hasPlugin(key)) return;
        PluginRegistry.Registrar registrar = registry.registrarFor(key);
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "com.iallchain/service");
        channel.setMethodCallHandler(new WebViewRegistrant(registrar, activity, channel));
    }

    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        if (methodCall.method.equals("method")) {
            if (activity != null) {
            }
        } else {
            result.notImplemented();
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("1123", "onServiceConnected");
            MyService.MyBinder binder = (MyService.MyBinder) service;
            MyService ms = binder.getService();
            ms.setListener(new MyService.Listener() {
                @Override
                public void str(String str) {
                    Log.e("1123", str);
                    channel.invokeMethod("callback", str);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public void onDestroy() {
        activity.unbindService(conn);
    }
}