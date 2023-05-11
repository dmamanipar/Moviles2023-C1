package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.AsisteciapaDao
import pe.edu.upeu.data.remote.RestAsisteciapa
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Asisteciapa
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface AsisteciapaRepository {
    suspend fun deleteAsisteciapa(asisteciapa: Asisteciapa)
    fun reportarAsisteciapas(): LiveData<List<Asisteciapa>>
    fun buscarAsisteciapaId(id:Int): LiveData<Asisteciapa>
    suspend fun insertarAsisteciapa(asisteciapa: Asisteciapa):Boolean
    suspend fun modificarRemoteAsisteciapa(asisteciapa: Asisteciapa) :Boolean
}

class AsisteciapaRepositoryImp @Inject constructor(
    private val restAsisteciapa: RestAsisteciapa,
    private val asisteciapaDao: AsisteciapaDao
):AsisteciapaRepository{

    override suspend fun deleteAsisteciapa(asisteciapa: Asisteciapa) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+asisteciapa.id_asisteciapa)

            restAsisteciapa.deleteAsisteciapa(TokenUtils.TOKEN_CONTENT,asisteciapa.id_asisteciapa)
        }
        asisteciapaDao.eliminarActividad(asisteciapa)
    }

    override fun reportarAsisteciapas(): LiveData<List<Asisteciapa>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restAsisteciapa.reportarAsisteciapa(TokenUtils.TOKEN_CONTENT).body()!!.data
                asisteciapaDao.insertarAsisteciapa(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return asisteciapaDao.reportarActividad()
    }

    override fun buscarAsisteciapaId(id: Int): LiveData<Asisteciapa> {
        return asisteciapaDao.buscarActividad(id)
    }

    override suspend fun insertarAsisteciapa(asisteciapa: Asisteciapa): Boolean{
        return restAsisteciapa.insertarAsisteciapa(TokenUtils.TOKEN_CONTENT, asisteciapa).body()?.success!!
    }

    override suspend fun modificarRemoteAsisteciapa(asisteciapa: Asisteciapa): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+ TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+asisteciapa.toString())
        }
        return restAsisteciapa.actualizarAsisteciapa(TokenUtils.TOKEN_CONTENT, asisteciapa.id_asisteciapa, asisteciapa).body()?.success!!
    }
}