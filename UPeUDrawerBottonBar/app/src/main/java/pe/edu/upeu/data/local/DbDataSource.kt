package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.FacultadDao
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Facultad

@Database(entities = [Actividad::class,Facultad::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao

    abstract fun facultadDao():FacultadDao
}
