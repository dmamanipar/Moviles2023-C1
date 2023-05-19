package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "periodo")
data class Periodo(
   @PrimaryKey(autoGenerate = true) var id:Int,

   var nombre_periodo:String,
   var estado:String,

)
