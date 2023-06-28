import 'dart:async';
import 'package:asistencia_app/modelo/ActividadModeloFire.dart';
import 'package:asistencia_app/repository/ActividadFireRepository.dart';
import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';
import 'package:flutter/material.dart';

part 'actividad_event.dart';
part 'actividad_state.dart';

class ActividadBlocFire extends Bloc<ActividadEvent, ActividadState> {

  late final ActividadFireRepository _actividadRepository;

  ActividadBlocFire(this._actividadRepository) : super(ActividadInitialState()) {
    on<ActividadEvent>((event, emit) async{
      // TODO: implement event handler
      if(event is ListarActividadEvent){
        emit(ActividadLoadingState());
        try{
          List<ActividadModeloFire> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }else if(event is CreateActividadEvent){
        try{
          await _actividadRepository.crearActividad(event.actividad);
          emit(ActividadLoadingState());
          List<ActividadModeloFire> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }else if(event is UpdateActividadEvent){
        try{
          await _actividadRepository.actualizarActividad(event.actividad.id, event.actividad);
          emit(ActividadLoadingState());
          List<ActividadModeloFire> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }else if(event is DeleteActividadEvent){
        try{
          await _actividadRepository.eliminarActividad(event.actividad.id);
          emit(ActividadLoadingState());
          List<ActividadModeloFire> actividadList=await _actividadRepository.getActividad();
          emit(ActividadLoadedState(actividadList));
        } catch(e){
          emit(ActividadError(e as Error));
        }
      }
    });
  }
}
