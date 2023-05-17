package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.AsistenciaDao
import pe.edu.upeu.data.remote.RestAsistencia
import pe.edu.upeu.modelo.Asistencia
import pe.edu.upeu.modelo.User
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface AsistenciaRepository {
    suspend fun deleteAsistencia(asistencia: Asistencia)
    fun reportarAsistencias(): LiveData<List<Asistencia>>
    fun buscarAsistenciaId(id:Int): LiveData<Asistencia>
    suspend fun insertarAsistencia(asistencia: Asistencia):Boolean
    suspend fun modificarRemoteAsistencia(asistencia: Asistencia) :Boolean
}

class AsistenciaRepositoryImp @Inject constructor(
    private val restAsistencia: RestAsistencia,
    private val asistenciaDao: AsistenciaDao
):AsistenciaRepository{

    override suspend fun deleteAsistencia(asistencia: Asistencia) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+asistencia.id)

            restAsistencia.deleteAsistencia(TokenUtils.TOKEN_CONTENT,asistencia.id)
        }
        asistenciaDao.eliminarAsistencia(asistencia)
    }

    override fun reportarAsistencias(): LiveData<List<Asistencia>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restAsistencia.reportarAsistencia(TokenUtils.TOKEN_CONTENT).body()!!.data
                asistenciaDao.insertarAsistencias(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return asistenciaDao.reportarAsistencia()
    }

    override fun buscarAsistenciaId(id: Int): LiveData<Asistencia> {
        return asistenciaDao.buscarAsistencia(id)
    }

    override suspend fun insertarAsistencia(asistencia: Asistencia): Boolean{
        return restAsistencia.insertarAsistencia(TokenUtils.TOKEN_CONTENT,asistencia).body()?.success!!
    }

    override suspend fun modificarRemoteAsistencia(asistencia: Asistencia): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+asistencia.toString())
        }
        return restAsistencia.actualizarAsistencia(TokenUtils.TOKEN_CONTENT, asistencia.id, asistencia).body()?.success!!
    }
}
