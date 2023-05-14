package pe.edu.upeu.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.edu.upeu.modelo.Persona

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarPersona(persona: Persona)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertarPersonas(persona:List<Persona>)

    @Update
    suspend fun  modificarPersona(persona: Persona)

    @Delete
    suspend fun eliminarPersona(persona: Persona)

    @Query("select * from persona")
    fun reportarPersona(): LiveData<List<Persona>>

    @Query("select * from persona where id=:idx")
    fun buscarPersona(idx:Int): LiveData<Persona>

}