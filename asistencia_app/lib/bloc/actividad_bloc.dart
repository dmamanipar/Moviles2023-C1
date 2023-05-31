import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:meta/meta.dart';

part 'actividad_event.dart';
part 'actividad_state.dart';

class ActividadBloc extends Bloc<ActividadEvent, ActividadState> {
  ActividadBloc() : super(ActividadInitial()) {
    on<ActividadEvent>((event, emit) {
      // TODO: implement event handler
    });
  }
}
