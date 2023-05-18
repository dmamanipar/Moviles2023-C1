package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.edu.upeu.modelo.Asisteciapa

@Dao
interface AsisteciapaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsisteciapa(asisteciapa: Asisteciapa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsisteciapas(asisteciapa:List<Asisteciapa>)

    @Update
    suspend fun modificarAsisteciapa(asisteciapa: Asisteciapa)

    @Delete
    suspend fun eliminarAsisteciapa(asisteciapa: Asisteciapa)

    @Query("select * from asistecipa")
    fun reportarAsisteciapa():LiveData<List<Asisteciapa>>

    @Query("select * from asistecipa where id_asisteciapa=:idx")
    fun buscarAsisteciapa(idx:Int):LiveData<Asisteciapa>
}