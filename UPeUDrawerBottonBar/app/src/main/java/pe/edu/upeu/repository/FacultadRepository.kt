package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.ActividadDao
import pe.edu.upeu.data.local.dao.FacultadDao
import pe.edu.upeu.data.remote.RestActividad
import pe.edu.upeu.data.remote.RestFacultad
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Facultad
import pe.edu.upeu.modelo.User
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface FacultadRepository {
    suspend fun deleteFacultad(facultad: Facultad)
    fun reportarFacultad(): LiveData<List<Facultad>>
    fun buscarFacultadId(id:Int): LiveData<Facultad>
    suspend fun insertarFacultad(facultad: Facultad):Boolean
    suspend fun modificarRemoteFacultad(facultad: Facultad) :Boolean
}

class FacultadRepositoryImp @Inject constructor(
    private val restFacultad: RestFacultad,
    private val facultadDao: FacultadDao
):FacultadRepository{

    override suspend fun deleteFacultad(facultad: Facultad) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+facultad.id)

            restFacultad.deleteFacultad(TokenUtils.TOKEN_CONTENT,facultad.id)
        }
        facultadDao.eliminarFacultad(facultad)
    }

    override fun reportarFacultad(): LiveData<List<Facultad>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restFacultad.reportarFacultad(TokenUtils.TOKEN_CONTENT).body()!!.data
                facultadDao.insertarFacultades(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return facultadDao.reportarFacultad()
    }

    override fun buscarFacultadId(id: Int): LiveData<Facultad> {
        return facultadDao.buscarFacultad(id)
    }

    override suspend fun insertarFacultad(facultad: Facultad): Boolean{
        return restFacultad.insertarFacultad(TokenUtils.TOKEN_CONTENT,facultad).body()?.success!!
    }

    override suspend fun modificarRemoteFacultad(facultad: Facultad): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+facultad.toString())
        }
        return restFacultad.actualizarFacultad(TokenUtils.TOKEN_CONTENT, facultad.id, facultad).body()?.success!!
    }
}
