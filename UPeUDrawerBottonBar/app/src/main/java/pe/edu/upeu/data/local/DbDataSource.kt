package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.MatriculaDao
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Matricula

@Database(entities = [Actividad::class, Matricula::class], version = 2)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao

    abstract fun matriculaDao():MatriculaDao
}