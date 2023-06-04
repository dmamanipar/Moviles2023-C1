import 'dart:async';

import 'package:asistencia_app/modelo/ActividadModelo.dart';
import 'package:asistencia_app/repository/ActividadRepository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'actividad_event.dart';
part 'actividad_state.dart';

class ActividadBloc extends Bloc<ActividadEvent, ActividadState> {

  late final ActividadRepository _actividadRepository;

  ActividadBloc(this._actividadRepository) : super(ActividadInitialState()) {
    on<ActividadEvent>((event, emit) async{
      // TODO: implement event handler
      if(event is ListarActividadEvent){
        emit(ActividadLoadingState());
        try{
          List<ActividadModelo> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }else if(event is CreateActividadEvent){
        try{
          await _actividadRepository.createActividad(event.actividad);
          emit(ActividadLoadingState());
          List<ActividadModelo> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }else if(event is UpdateActividadEvent){
        try{
          await _actividadRepository.updateActividad(event.actividad.id, event.actividad);
          emit(ActividadLoadingState());
          List<ActividadModelo> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }else if(event is DeleteActividadEvent){
        try{
          await _actividadRepository.deleteActividad(event.actividad.id);
          emit(ActividadLoadingState());
          List<ActividadModelo> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }
    });
  }
}
