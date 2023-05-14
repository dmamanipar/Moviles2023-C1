package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.PersonaDao
import pe.edu.upeu.data.remote.RestPersona

import pe.edu.upeu.modelo.Persona
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface PersonaRepository {
    suspend fun deletePersona(persona: Persona)
    fun reportarPersonas(): LiveData<List<Persona>>
    fun buscarPersonaId(id:Int): LiveData<Persona>
    suspend fun insertarPersona(persona: Persona):Boolean
    suspend fun modificarRemotePersona(persona: Persona) :Boolean
}
class PersonaRepositoryImp @Inject constructor(
    private val restPersona: RestPersona,
    private val personaDao: PersonaDao
):PersonaRepository{

    override suspend fun deletePersona(persona: Persona) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+persona.id)

            restPersona.deletePersona(TokenUtils.TOKEN_CONTENT,persona.id)
        }
        personaDao.eliminarPersona(persona)
    }

    override fun reportarPersonas(): LiveData<List<Persona>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restPersona.reportarPersona(TokenUtils.TOKEN_CONTENT).body()!!.data
                personaDao.insertarPersonas(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return personaDao.reportarPersona()
    }

    override fun buscarPersonaId(id: Int): LiveData<Persona> {
        return personaDao.buscarPersona(id)
    }

    override suspend fun insertarPersona(persona: Persona): Boolean{
        return restPersona.insertarPersona(TokenUtils.TOKEN_CONTENT,persona).body()?.success!!
    }

    override suspend fun modificarRemotePersona(persona: Persona): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+ TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+persona.toString())
        }
        return restPersona.actualizarPersona(TokenUtils.TOKEN_CONTENT, persona.id, persona).body()?.success!!
    }
}
