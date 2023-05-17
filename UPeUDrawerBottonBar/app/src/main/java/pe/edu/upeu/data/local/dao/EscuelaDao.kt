package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.modelo.Escuela

@Dao
interface EscuelaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarEscuela(escuela:Escuela)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarEscuela(escuela:List<Escuela>)

    @Update
    suspend fun  modificarEscuela(escuela:Escuela)

    @Delete
    suspend fun eliminarEscuela(escuela: Escuela)

    @Query("select * from escuela")
    fun reportarEscuela():LiveData<List<Escuela>>

    @Query("select * from escuela where id=:idx")
    fun buscarEscuela(idx:Int):LiveData<Escuela>

}