package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "persona")


    data class Persona(
        @PrimaryKey(autoGenerate = true) var id:Int,
        var dni:String,
        var nombre:String,
        var apellido_paterno:String,
        var apellido_materno:String,
        var telefono:String,
        var genero:String,
        var correo:String
    )