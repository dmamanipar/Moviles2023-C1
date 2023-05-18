package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.MatriculaDao
import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.data.remote.RestMatricula
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Matricula
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface MatriculaRepository {
    suspend fun deleteMatricula(matricula: Matricula)
    fun reportarMatriculas(): LiveData<List<Matricula>>
    fun buscarMatriculaId(id:Int): LiveData<Matricula>
    suspend fun insertarMatricula(matricula: Matricula):Boolean
    suspend fun modificarRemoteMatricula(matricula: Matricula) :Boolean
}

class MatriculaRepositoryImp @Inject constructor(
    private val restMatricula: RestMatricula,
    private val matriculaDao: MatriculaDao
):MatriculaRepository{

    override suspend fun deleteMatricula(matricula: Matricula) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+matricula.id)

            restMatricula.deleteMatricula(TokenUtils.TOKEN_CONTENT,matricula.id)
        }
        matriculaDao.eliminarMatricula(matricula)
    }

    override fun reportarMatriculas(): LiveData<List<Matricula>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restMatricula.reportarMatricula(TokenUtils.TOKEN_CONTENT).body()!!.data
                matriculaDao.insertarMatriculas(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return matriculaDao.reportarMatricula()
    }

    override fun buscarMatriculaId(id: Int): LiveData<Matricula> {
        return matriculaDao.buscarMatricula(id)
    }

    override suspend fun insertarMatricula(matricula: Matricula): Boolean{
        return restMatricula.insertarMatricula(TokenUtils.TOKEN_CONTENT,matricula).body()?.success!!
    }

    override suspend fun modificarRemoteMatricula(matricula: Matricula): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+ TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+matricula.toString())
        }
        return restMatricula.actualizarMatricula(TokenUtils.TOKEN_CONTENT, matricula.id, matricula).body()?.success!!
    }
}