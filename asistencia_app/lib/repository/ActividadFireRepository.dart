
import 'package:asistencia_app/modelo/ActividadModeloFire.dart';
import 'package:asistencia_app/util/NetworConnection.dart';
import 'package:cloud_firestore/cloud_firestore.dart';

class ActividadFireRepository{
  CollectionReference db=FirebaseFirestore.instance.collection("actividad");

  Future<List<ActividadModeloFire>> getActividad() async{
    if(await isConected()){
      var data=await db.get();
      var actividad=data.docs.map((e){
        var datax=e.data() as ActividadModeloFire;
        datax.id=e.id;
        return datax;
      }).toList();
      return actividad;
    }else{
      return null!;
    }
  }

  Future<void> crearActividad(ActividadModeloFire act) async{
    if(await isConected()){
      return await db.add(act.toMap())
          .then((value) => print("Holas: ${value.id}"))
      .catchError((onError)=>print("Error: $onError"));
    }else{
      print("No hay Internet...");
    }
  }

  Future<void> eliminarActividad(String id) async{
    if(await isConected()){
      return await db.doc(id).delete()
          .then((value) => print("Actividad eliminada"))
          .catchError((onError)=>print("Error: $onError"));
    }else{
      print("No hay Internet...");
    }
  }

  Future<void> actualizarActividad(String id, ActividadModeloFire act) async{
    if(await isConected()){
      return await db.doc(id).update(act.toMap())
          .then((value) => print("Actualizo correctamente"))
          .catchError((onError)=>print("Error: $onError"));
    }else{
      print("No hay Internet...");
    }
  }




}