import 'package:asistencia_app/modelo/ActividadModelo.dart';
import 'package:asistencia_app/util/UrlApi.dart';
import 'package:pretty_dio_logger/pretty_dio_logger.dart';
import 'package:retrofit/retrofit.dart';
import 'package:dio/dio.dart' hide Headers;
part 'actividad_api.g.dart';
@RestApi(baseUrl: UrlApi.urlApix)
abstract class ActividadApi {
  factory ActividadApi(Dio dio, {String baseUrl}) = _ActividadApi;
  static ActividadApi create() {
    final dio = Dio();
    dio.interceptors.add(PrettyDioLogger());
    return ActividadApi(dio);
  }

  @GET("/api/act")
  Future<RespActividadModelo> getActividad(@Header("Authorization") String token);

  @POST("/api/act")
  Future<RespActividadModelo> createActividad(@Header("Authorization") String
  token,@Body() ActividadModelo actividad);

  @DELETE("/api/act/{id}")
  Future<RespActividadModelo> deleteActividad(@Header("Authorization") String token,
      @Path("id") int id);

  @PATCH("/api/act/{id}")
  Future<RespActividadModelo> updateActividad(@Header("Authorization") String
  token,@Path("id") int id, @Body() ActividadModelo actividad);

}