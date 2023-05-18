package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.AsistenciaDao
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Asistencia


@Database(entities = [Actividad::class, Asistencia::class], version =3 )
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun asistenciaDao(): AsistenciaDao
}