package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evento")
data class Evento (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var nom_evento:String,
    var fecha:String,
    var horai:String,
    var min_toler:String,
    var latitud:String,
    var longitud:String,
    var estado:String,
    var evaluar:String,
    var perfil_evento:String,
    var offline:String,

    )