import 'package:asistencia_app/apis/actividad_api.dart';
import 'package:asistencia_app/local/db/database.dart';
import 'package:asistencia_app/modelo/ActividadModelo.dart';
import 'package:asistencia_app/util/NetworConnection.dart';
import 'package:asistencia_app/util/TokenUtil.dart';
import 'package:dio/dio.dart';

class ActividadRepository{
  ActividadApi? actividadApi;

  ActividadRepository(){
    Dio _dio=Dio();
    _dio.options.headers["Content-Type"]="application/json";
    actividadApi=ActividadApi(_dio);
  }

  Future<AppDatabase> conection() async{
    return await $FloorAppDatabase.databaseBuilder("app_database.db").build();
  }

  Future<List<ActividadModelo>> getActividad() async {
    final database=await conection();
    final actividadDao = database.actividadDao;
    if(await isConected()) {
      var dato = await actividadApi!.getActividad(TokenUtil.TOKEN).then((
          value) => value.data);
      dato.forEach((el) async {
        await actividadDao.insertarActividad(new ActividadModelo(id: el.id,
            periodoId: el.periodoId,
            nombreActividad: el.nombreActividad,
            fecha: el.fecha,
            horai: el.horai,
            minToler: el.minToler,
            latitud: el.latitud,
            longitud: el.longitud,
            estado: el.estado,
            evaluar: el.evaluar,
            userCreate: el.userCreate));
      });
      return dato;
    }else{
      return await actividadDao.listarActividad();
    }
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