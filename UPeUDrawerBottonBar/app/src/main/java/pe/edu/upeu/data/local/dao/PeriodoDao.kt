package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Periodo

@Dao
interface PeriodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarPeriodo(periodo:Periodo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarPeriodos(periodo:List<Periodo>)

    @Update
    suspend fun  modificarPeriodo(periodo:Periodo)

    @Delete
    suspend fun eliminarPeriodo(periodo: Periodo)

    @Query("select * from periodo")
    fun reportarPeriodo():LiveData<List<Periodo>>

    @Query("select * from periodo where id=:idx")
    fun buscarPeriodo(idx:Int):LiveData<Periodo>

}