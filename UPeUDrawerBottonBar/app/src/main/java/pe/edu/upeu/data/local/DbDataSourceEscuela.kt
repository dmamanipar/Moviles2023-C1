package pe.edu.upeu.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.data.local.dao.EscuelaDao
import pe.edu.upeu.modelo.Escuela


@Database(entities = [Escuela::class], version = 1)
abstract class DbDataSourceEscuela:RoomDatabase() {

    abstract fun escuelaDao():EscuelaDao

}


