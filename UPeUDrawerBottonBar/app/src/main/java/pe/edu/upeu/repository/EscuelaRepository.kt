package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.EscuelaDao
import pe.edu.upeu.data.remote.RestEscuela
import pe.edu.upeu.modelo.Escuela
import pe.edu.upeu.modelo.User
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface EscuelaRepository {
    suspend fun deleteEscuela(escuela: Escuela)
    fun reportarEscuelas(): LiveData<List<Escuela>>
    fun buscarEscuelaId(id:Int): LiveData<Escuela>
    suspend fun insertarEscuela(escuela: Escuela):Boolean
    suspend fun modificarRemoteEscuela(escuela: Escuela) :Boolean
}

class EscuelaRepositoryImp @Inject constructor(
    private val restEscuela: RestEscuela,
    private val escuelaDao:EscuelaDao
):EscuelaRepository{

    override suspend fun deleteEscuela(escuela: Escuela) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+escuela.id)

            restEscuela.deleteEscuela(TokenUtils.TOKEN_CONTENT,escuela.id)
        }
        escuela.eliminarEscuela(escuela)
    }

    override fun reportarEscuelas(): LiveData<List<Escuela>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restEscuela.reportarEscuela(TokenUtils.TOKEN_CONTENT).body()!!.data
                actividadDao.insertarEscuelas(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return escuelaDao.reportarEscuela()
    }

    override fun buscarEscuelaId(id: Int): LiveData<Escuela> {
        return escuelaDao.buscarEscuela(id)
    }

    override suspend fun insertarEscuela(escuela: Escuela): Boolean{
        return restEscuela.insertarEscuela(TokenUtils.TOKEN_CONTENT,escuela).body()?.success!!
    }

    override suspend fun modificarRemoteEscuela(escuela: Escuela): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+escuela.toString())
        }
        return restEscuela.actualizarEscuela(TokenUtils.TOKEN_CONTENT, escuela.id, escuela).body()?.success!!
    }
}
