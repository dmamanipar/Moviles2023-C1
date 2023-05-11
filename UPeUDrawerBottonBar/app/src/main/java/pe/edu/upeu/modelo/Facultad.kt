package pe.edu.upeu.modelo


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facultad")
data class Facultad(
    @PrimaryKey(autoGenerate = true) var id:Int,
    var nombrefac:String,
    var estado:String,
    var iniciales:String

)