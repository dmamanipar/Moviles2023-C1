package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actividad")
data class Actividad(
   @PrimaryKey(autoGenerate = true) var id:Int,
   @ColumnInfo(name = "periodo_id") var periodo_id:Int,
   var nombre_actividad:String,
   var fecha:String,
   var horai:String,
   var min_toler:String,
   var latitud:String,
   var longitud:String,
   var estado:String,
   var evaluar:String,
   var user_create:String
)
