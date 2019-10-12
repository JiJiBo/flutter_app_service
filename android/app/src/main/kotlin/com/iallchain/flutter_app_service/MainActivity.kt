package com.iallchain.flutter_app_service

import android.os.Bundle
import com.iallchain.flutter_app_service.WebViewPack.WebViewRegistrant

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {
    var wr: WebViewRegistrant? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        wr = WebViewRegistrant();
        wr!!.registerWith(this, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        wr!!.onDestroy()
    }
}
