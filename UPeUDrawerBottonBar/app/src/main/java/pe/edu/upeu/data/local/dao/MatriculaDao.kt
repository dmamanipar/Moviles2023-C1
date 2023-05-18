package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.upeu.modelo.Matricula

@Dao
interface MatriculaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarMatricula(matricula: Matricula)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarMatriculas(matricula:List<Matricula>)

    @Update
    suspend fun  modificarMatricula(matricula: Matricula)

    @Delete
    suspend fun eliminarMatricula(matricula: Matricula)

    @Query("select * from matricula")
    fun reportarMatricula(): LiveData<List<Matricula>>

    @Query("select * from matricula where id=:idx")
    fun buscarMatricula(idx:Int): LiveData<Matricula>

}