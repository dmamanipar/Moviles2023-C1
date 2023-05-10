package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistecipa")
data class Asisteciapa(
    @PrimaryKey(autoGenerate = true) var id_asisteciapa:Int,
    var id_actividad:Int,
    var fecha:String,
    var hora_reg:String,
    var latituda:String,
    var longituda:String,
    var tipo:String,
    var calificacion:String,
    var cui:String,
    var tipo_cui:String,
)