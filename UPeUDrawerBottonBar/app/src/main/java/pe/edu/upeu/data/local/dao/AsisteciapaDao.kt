package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.edu.upeu.modelo.Asisteciapa

@Dao
interface AsisteciapaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsisteciapa(asisteciapa: Asisteciapa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarAsisteciapa(asisteciapa:List<Asisteciapa>)

    @Update
    suspend fun modificarAsisteciapa(asisteciapa: Asisteciapa)

    @Delete
    suspend fun eliminarActividad(asisteciapa: Asisteciapa)

    @Query("select * from asisteciapa")
    fun reportarActividad():LiveData<List<Asisteciapa>>

    @Query("select * from actividad where id=:idx")
    fun buscarActividad(idx:Int):LiveData<Asisteciapa>
}