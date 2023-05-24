package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "escuela")
data class Escuela(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var nombreeap:String,
    var estado:String,
    var inicialeseap:String,
    var facultad_nom:String
)
