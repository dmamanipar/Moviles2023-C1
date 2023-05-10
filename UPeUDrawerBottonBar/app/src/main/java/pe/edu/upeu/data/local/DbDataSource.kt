package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.AsisteciapaDao
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Asisteciapa

@Database(entities = [Actividad::class, Asisteciapa::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun asisteciapaDao():AsisteciapaDao
}

