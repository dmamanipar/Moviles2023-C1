import 'package:floor/floor.dart';

@Entity(tableName: "actividad")
class ActividadModelo {
  @primaryKey
  late int id=0;
  @ColumnInfo(name: "periodo_id")
  late int periodoId;
  @ColumnInfo(name: "nombre_actividad")
  late String nombreActividad;
  late String fecha;
  late String horai;
  @ColumnInfo(name: "min_toler")
  late String minToler;
  late String latitud;
  late String longitud;
  late String estado;
  late String evaluar;
  @ColumnInfo(name: "user_create")
  late String userCreate;
  @ignore
  late List<AsistenciapaModelo> asistenciapas;

  ActividadModelo(
      { required this.id,
        required this.periodoId,
        required this.nombreActividad,
        required this.fecha,
        required this.horai,
        required this.minToler,
        required this.latitud,
        required this.longitud,
        required this.estado,
        required this.evaluar,
        required this.userCreate/*,
        required this.asistenciapas*/});
  ActividadModelo.unlaunched();

  ActividadModelo.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    periodoId = json['periodo_id'];
    nombreActividad = json['nombre_actividad'];
    fecha = json['fecha'];
    horai = json['horai'];
    minToler = json['min_toler'];
    latitud = json['latitud'];
    longitud = json['longitud'];
    estado = json['estado'];
    evaluar = json['evaluar'];
    userCreate = json['user_create'];
    if (json['asistenciapas'] != null) {
      asistenciapas = (json['asistenciapas'] as List).map((e) =>
      AsistenciapaModelo.fromJson(e as Map<String, dynamic>)).toList();
    }else{asistenciapas=[];}
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['periodo_id'] = this.periodoId;
    data['nombre_actividad'] = this.nombreActividad;
    data['fecha'] = this.fecha;
    data['horai'] = this.horai;
    data['min_toler'] = this.minToler;
    data['latitud'] = this.latitud;
    data['longitud'] = this.longitud;
    data['estado'] = this.estado;
    data['evaluar'] = this.evaluar;
    data['user_create'] = this.userCreate;
    if (this.asistenciapas != null) {
      data['asistenciapas'] = this.asistenciapas.map((v) => v.toJson()).toList();
    }
    return data;
  }
}

class RespActividadModelo {
  late bool success;
  late List<ActividadModelo> data;
  late String message;

  RespActividadModelo({ required this.success, required this.data ,required this.message});
  RespActividadModelo.unlaunched();

  RespActividadModelo.fromJson(Map<String, dynamic> json) {
    success = json['success'];
    data = (json['data'] as List).map((e) =>
        ActividadModelo.fromJson(e as Map<String, dynamic>)).toList();
    message = json['message'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['success'] = this.success;
    data['data']=this.data.map((e) => e.toJson()).toList();
    data['message'] = this.message;
    return data;
  }
}


class AsistenciapaModelo {
  late int id;
  late String fecha;
  late String horaReg;
  late String latituda;
  late String longituda;
  late String tipo;
  late int calificacion;
  late String cui;
  late String tipoCui;
  late int actividadId;

  AsistenciapaModelo(
      {required this.id,
        required this.fecha,
        required this.horaReg,
        required this.latituda,
        required this.longituda,
        required this.tipo,
        required this.calificacion,
        required this.cui,
        required this.tipoCui,
        required this.actividadId});
  AsistenciapaModelo.unlaunched();

  AsistenciapaModelo.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    fecha = json['fecha'];
    horaReg = json['hora_reg'];
    latituda = json['latituda'];
    longituda = json['longituda'];
    tipo = json['tipo'];
    calificacion = json['calificacion'];
    cui = json['cui'];
    tipoCui = json['tipo_cui'];
    actividadId = json['actividad_id'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['fecha'] = this.fecha;
    data['hora_reg'] = this.horaReg;
    data['latituda'] = this.latituda;
    data['longituda'] = this.longituda;
    data['tipo'] = this.tipo;
    data['calificacion'] = this.calificacion;
    data['cui'] = this.cui;
    data['tipo_cui'] = this.tipoCui;
    data['actividad_id'] = this.actividadId;
    return data;
  }
}