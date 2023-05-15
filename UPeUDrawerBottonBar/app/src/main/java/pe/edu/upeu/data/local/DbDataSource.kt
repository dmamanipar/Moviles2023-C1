package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.EscuelaDao
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Escuela

@Database(entities = [Actividad::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun escuelaDao():EscuelaDao
}
