part of 'actividad_bloc.dart';

@immutable
abstract class ActividadEvent {}

class ListarActividadEvent extends ActividadEvent{
  ListarActividadEvent();
}
class DeleteActividadEvent extends ActividadEvent{
  ActividadModeloFire actividad;
  DeleteActividadEvent(this.actividad);
}
class UpdateActividadEvent extends ActividadEvent{
  ActividadModeloFire actividad;
  UpdateActividadEvent(this.actividad);
}

class CreateActividadEvent extends ActividadEvent{
  ActividadModeloFire actividad;
  CreateActividadEvent(this.actividad);
}