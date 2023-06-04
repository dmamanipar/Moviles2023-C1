import 'package:json_annotation/json_annotation.dart';

@JsonSerializable()
class UsuarioModelo {
  String name;
  String email;
  String password;

  UsuarioModelo(
      {required this.name, required this.email, required this.password});

  factory UsuarioModelo.fromJson(Map<String, dynamic> json) {
    return UsuarioModelo(
      name: json['name'],
      email: json['email'],
      password: json['password'],
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['email'] = this.email;
    data['password'] = this.password;
    return data;
  }
}

class TokenModelo {
  bool status;
  String message;
  String accessToken;
  String tokenType;
  String expiresAt;

  TokenModelo(
      {required this.status,
      required this.message,
      required this.accessToken,
      required this.tokenType,
      required this.expiresAt});

  factory TokenModelo.fromJson(Map<String, dynamic> json) {
    return TokenModelo(
      status: json['status'],
      message: json['message'],
      accessToken: json['access_token'],
      tokenType: json['token_type'],
      expiresAt: json['expires_at'],
    );
  }

  Map<String, dynamic> toJson() {
    return{
    'status':status,
    'message':message,
    'access_token':accessToken,
    'token_type':tokenType,
    'expires_at':expiresAt,
    };
  }
}
