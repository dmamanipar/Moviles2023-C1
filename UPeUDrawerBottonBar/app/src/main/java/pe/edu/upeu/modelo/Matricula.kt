package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matricula")
data class Matricula(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var periodo_id:String,
    var persona_id:String,
    var estado:String,

)