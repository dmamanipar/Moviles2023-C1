package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistecipa")
data class Asisteciapa(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_asisteciapa") var id:Int,
    var actividad_id: Int,
    var fecha:String,
    var hora_reg:String,
    var latituda:String,
    var longituda:String,
    var tipo:String,
    var calificacion:Int,
    var cui:String,
    var tipo_cui:String,
) {

}