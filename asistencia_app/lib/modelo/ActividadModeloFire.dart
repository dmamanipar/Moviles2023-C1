class ActividadModeloFire {
  late String id;
  late int periodoId;
  late String nombreActividad;
  late String fecha;
  late String horai;
  late String minToler;
  late String latitud;
  late String longitud;
  late String estado;
  late String evaluar;
  late String userCreate;

  ActividadModeloFire(
      {required this.id,
      required this.periodoId,
      required this.nombreActividad,
      required this.fecha,
      required this.horai,
      required this.minToler,
      required this.latitud,
      required this.longitud,
      required this.estado,
      required this.evaluar,
      required this.userCreate /*,
        required this.asistenciapas*/
      });
  ActividadModeloFire.unlaunched();

  factory ActividadModeloFire.fromJson(Map<String, dynamic> json) {
    return ActividadModeloFire(
        id: json['id']==null?"0":json['id'],
        periodoId: json['periodo_id'],
        nombreActividad: json['nombre_actividad'],
        fecha: json['fecha'],
        horai: json['horai'],
        minToler: json['min_toler'],
        latitud: json['latitud'],
        longitud: json['longitud'],
        estado: json['estado'],
        evaluar: json['evaluar'],
        userCreate: json['user_create']);
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

    return data;
  }

  Map<String, dynamic> toMap() {
    var data = Map<String, dynamic>();
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
    return data;
  }
}
