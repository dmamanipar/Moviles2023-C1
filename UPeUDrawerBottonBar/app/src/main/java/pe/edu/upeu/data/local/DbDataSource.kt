package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.PersonaDao
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Persona

@Database(entities = [Actividad::class, Persona::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao():ActividadDao
    abstract fun personaDao():PersonaDao
}
