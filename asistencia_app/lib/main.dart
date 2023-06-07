import 'package:flutter/material.dart';
import 'package:asistencia_app/login/login_google.dart';
import 'package:asistencia_app/theme/AppTheme.dart';
import 'package:local_auth/local_auth.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}
class MyApp extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "UPeU",
      theme: AppTheme.themeData,
      debugShowCheckedModeBanner: false,
      home: MainLogin(),
    );
  }
}