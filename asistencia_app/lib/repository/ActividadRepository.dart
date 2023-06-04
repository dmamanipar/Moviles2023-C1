import 'package:asistencia_app/apis/actividad_api.dart';
import 'package:asistencia_app/modelo/ActividadModelo.dart';
import 'package:asistencia_app/util/TokenUtil.dart';
import 'package:dio/dio.dart';

class ActividadRepository{
  ActividadApi? actividadApi;

  ActividadRepository(){
    Dio _dio=Dio();
    _dio.options.headers["Content-Type"]="application/json";
    actividadApi=ActividadApi(_dio);
  }

  Future<List<ActividadModelo>> getActividad() async {
    return await actividadApi!.getActividad(TokenUtil.TOKEN).then((value) => value.data);
  }

  Future<RespActividadModelo> deleteActividad(int id) async{
    return await actividadApi!.deleteActividad(TokenUtil.TOKEN, id);
  }

  Future<RespActividadModelo> updateActividad(int id, ActividadModelo actividad) async{
    return await actividadApi!.updateActividad(TokenUtil.TOKEN, id, actividad);
  }

  Future<RespActividadModelo> createActividad(ActividadModelo actividad) async{
    return await actividadApi!.createActividad(TokenUtil.TOKEN,actividad);
  }

}