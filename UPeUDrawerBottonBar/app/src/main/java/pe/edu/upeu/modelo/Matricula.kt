package pe.edu.upeu.modelo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matricula")
data class Matricula(
    @PrimaryKey(autoGenerate = true) var id:Int,
    @ColumnInfo(name = "periodo_id") var periodo_id:Int,
    @ColumnInfo(name = "persona_id") var persona_id:Int,
    var estado:String,

)