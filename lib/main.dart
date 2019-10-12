import 'package:flutter/material.dart';

import 'WebViewUtils.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return layout();
  }
}

class layout extends StatefulWidget {
  @override
  view createState() {
    return view();
  }
}

class view extends State<layout> {
  String text = "jijibo";

  @override
  void initState() {
    super.initState();
    WebViewUtils.urlCallback(onResult: (str) {
      setState(() {
        text = str;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "service",
      home: Scaffold(
        appBar: AppBar(
          title: Text("service"),
        ),
        body: Center(
          child: Text(text),
        ),
      ),
    );
  }
}
