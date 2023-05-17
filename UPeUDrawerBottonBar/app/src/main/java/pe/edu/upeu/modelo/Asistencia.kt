package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistencia")
data class Asistencia(
        @PrimaryKey(autoGenerate = true) var id:Int,
        @ColumnInfo(name = "id_matricula") var id_matricula:Int,
        @ColumnInfo(name = "id_evento") var id_evento:Int,
        var fecha:String,
        var hora:String,
        var latituda:String,
        var longituda:String,
        var tipo:String,
        var tipo_reg:String,
        var id_persona:String,
        var calificacion:String,
        var offlinex:String
)

