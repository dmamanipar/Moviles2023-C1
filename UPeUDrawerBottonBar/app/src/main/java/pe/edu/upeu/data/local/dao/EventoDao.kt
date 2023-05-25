package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.edu.upeu.modelo.Evento

@Dao
interface EventoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarEvento(evento: Evento)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarEventos(evento:List<Evento>)

    @Update
    suspend fun  modificarEvento(evento: Evento)

    @Delete
    suspend fun eliminarEvento(evento: Evento)

    @Query("select * from evento")
    fun reportarEvento(): LiveData<List<Evento>>

    @Query("select * from evento where id=:idx")
    fun buscarEvento(idx:Int): LiveData<Evento>
}