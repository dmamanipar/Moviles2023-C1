part of 'actividad_bloc.dart';

@immutable
abstract class ActividadEvent {}

class ListarActividadEvent extends ActividadEvent{
  ListarActividadEvent();
}
class DeleteActividadEvent extends ActividadEvent{
  ActividadModelo actividad;
  DeleteActividadEvent(this.actividad);
}
class UpdateActividadEvent extends ActividadEvent{
  ActividadModelo actividad;
  UpdateActividadEvent(this.actividad);
}

class CreateActividadEvent extends ActividadEvent{
  ActividadModelo actividad;
  CreateActividadEvent(this.actividad);
}