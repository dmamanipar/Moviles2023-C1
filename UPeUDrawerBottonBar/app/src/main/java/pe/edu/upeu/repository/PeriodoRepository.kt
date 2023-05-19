package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.PeriodoDao
import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.data.remote.RestPeriodo
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Periodo
import pe.edu.upeu.modelo.User
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface PeriodoRepository {
    suspend fun deletePeriodo(periodo: Periodo)
    fun reportarPeriodos(): LiveData<List<Periodo>>
    fun buscarPeriodoId(id:Int): LiveData<Periodo>
    suspend fun insertarPeriodo(periodo: Periodo):Boolean
    suspend fun modificarRemotePeriodo(periodo: Periodo) :Boolean
}

class PeriodoRepositoryImp @Inject constructor(
    private val restPeriodo: RestPeriodo,
    private val periodoDao:PeriodoDao
):PeriodoRepository{

    override suspend fun deletePeriodo(periodo: Periodo) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+periodo.id)

            restPeriodo.deletePeriodo(TokenUtils.TOKEN_CONTENT,periodo.id)
        }
        periodoDao.eliminarPeriodo(periodo)
    }

    override fun reportarPeriodos(): LiveData<List<Periodo>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restPeriodo.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restPeriodo.reportarPeriodo(TokenUtils.TOKEN_CONTENT).body()!!.data
                periodoDao.insertarPeriodos(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return periodoDao.reportarPeriodo()
    }

    override fun buscarPeriodoId(id: Int): LiveData<Periodo> {
        return periodoDao.buscarPeriodo(id)
    }

    override suspend fun insertarPeriodo(periodo: Periodo): Boolean{
        return restPeriodo.insertarPeriodo(TokenUtils.TOKEN_CONTENT,periodo).body()?.success!!
    }

    override suspend fun modificarRemotePeriodo(periodo: Periodo): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+periodo.toString())
        }
        return restPeriodo.actualizarPeriodo(TokenUtils.TOKEN_CONTENT, periodo.id, periodo).body()?.success!!
    }
}
