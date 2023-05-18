package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.modelo.Asistencia

@Dao
interface AsistenciaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarAsistencia(asistencia:Asistencia)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarAsistencias(asistencia:List<Asistencia>)

    @Update
    suspend fun  modificarAsistencia(asistencia:Asistencia)

    @Delete
    suspend fun eliminarAsistencia(asistencia: Asistencia)

    @Query("select * from asistencia")
    fun reportarAsistencia():LiveData<List<Asistencia>>

    @Query("select * from asistencia where id=:idx")
    fun buscarAsistencia(idx:Int):LiveData<Asistencia>

}