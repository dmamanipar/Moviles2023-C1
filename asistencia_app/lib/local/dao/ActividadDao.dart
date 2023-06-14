import 'package:asistencia_app/modelo/ActividadModelo.dart';
import 'package:floor/floor.dart';

@dao
abstract class ActividadDao{

  @Query('SELECT * FROM actividad')
  Future<List<ActividadModelo>> listarActividad();

  @Query('SELECT * FROM actividad where id=:id')
  Stream<ActividadModelo?> buscarActividadId(int id);

  @Insert(onConflict: OnConflictStrategy.replace)
  Future<void> insertarActividad(ActividadModelo actividad);

  @update
  Future<void> updateActividad(ActividadModelo actividad);

  @delete
  Future<void> deleteActividad(ActividadModelo actividad);
}