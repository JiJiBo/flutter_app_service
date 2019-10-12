import 'dart:io';

import 'package:flutter/services.dart';

class WebViewUtils {
  ///私有构造方法
  WebViewUtils._();


  ///其中com.example/share用于唯一识别通道名称，
  /// 这个名字后边会在Android端使用到，必须跟Android端对应调用方法中的通道名称保持一致，否则无法调用
  static const MethodChannel _channel =
      const MethodChannel("com.iallchain/service");

  static urlCallback({Function onResult(String url)}) {
    _channel.setMethodCallHandler(
      (MethodCall methodCall) => Future<String>(() {
        //响应原生的调用
        if (methodCall.method == "callback") {
          if (onResult != null) onResult(methodCall.arguments);
        }
      }),
    );
  }
}
