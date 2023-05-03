package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.User
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface ActividadRepository {
    suspend fun deleteActividad(actividad: Actividad)
    fun reportarActividads(): LiveData<List<Actividad>>
    fun buscarActividadId(id:Int): LiveData<Actividad>
    suspend fun insertarActividad(actividad: Actividad):Boolean
    suspend fun modificarRemoteActividad(actividad: Actividad) :Boolean
}

class ActividadRepositoryImp @Inject constructor(
    private val restActividad: RestActividad,
    private val actividadDao:ActividadDao
):ActividadRepository{

    override suspend fun deleteActividad(actividad: Actividad) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+actividad.id)

            restActividad.deleteActividad(TokenUtils.TOKEN_CONTENT,actividad.id)
        }
        actividadDao.eliminarActividad(actividad)
    }

    override fun reportarActividads(): LiveData<List<Actividad>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678"))
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)
                val data=restActividad.reportarActividad(TokenUtils.TOKEN_CONTENT).body()!!.data
                actividadDao.insertarActividades(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return actividadDao.reportarActividad()
    }

    override fun buscarActividadId(id: Int): LiveData<Actividad> {
        return actividadDao.buscarActividad(id)
    }

    override suspend fun insertarActividad(actividad: Actividad): Boolean
    {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+actividad.toString())

            dd=restActividad.insertarActividad(TokenUtils.TOKEN_CONTENT,actividad).body()?.success!!
        }
        return dd
    }
    override suspend fun modificarRemoteActividad(actividad: Actividad):
            Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+actividad.toString())

            dd=restActividad.actualizarActividad(TokenUtils.TOKEN_CONTENT,
                actividad.id, actividad).body()?.success!!
        }
        return dd
    }
}
