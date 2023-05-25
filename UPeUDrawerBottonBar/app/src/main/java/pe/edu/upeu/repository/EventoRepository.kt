package pe.edu.upeu.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.data.local.dao.EventoDao
import pe.edu.upeu.data.remote.RestEvento

import pe.edu.upeu.modelo.Evento
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

interface EventoRepository {
    suspend fun deleteEvento(evento: Evento)
    fun reportarEventos(): LiveData<List<Evento>>
    fun buscarEventoId(id:Int): LiveData<Evento>
    suspend fun insertarEvento(evento: Evento):Boolean
    suspend fun modificarRemoteEvento(evento: Evento) :Boolean
}
class EventoRepositoryImp @Inject constructor(
    private val restEvento: RestEvento,
    private val eventoDao: EventoDao
):EventoRepository{

    override suspend fun deleteEvento(evento: Evento) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DELETX", ""+evento.id)

            restEvento.deleteEvento(TokenUtils.TOKEN_CONTENT,evento.id)
        }
        eventoDao.eliminarEvento(evento)
    }

    override fun reportarEventos(): LiveData<List<Evento>> {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                /*val totek=restActividad.login(User("",
                    "davidmp@upeu.edu.pe", "12345678")).body()
                TokenUtils.TOKEN_CONTENT=totek?.token_type+""+totek?.access_token
                Log.i("VERX", "Token:"+TokenUtils.TOKEN_CONTENT)*/

                val data=restEvento.reportarEvento(TokenUtils.TOKEN_CONTENT).body()!!.data
                eventoDao.insertarEventos(data)
            }
        }catch (e:Exception){
            Log.i("ERRORX", "Error:"+e.message)
        }
        return eventoDao.reportarEvento()
    }

    override fun buscarEventoId(id: Int): LiveData<Evento> {
        return eventoDao.buscarEvento(id)
    }

    override suspend fun insertarEvento(evento: Evento): Boolean{
        return restEvento.insertarEvento(TokenUtils.TOKEN_CONTENT,evento).body()?.success!!
    }

    override suspend fun modificarRemoteEvento(evento: Evento): Boolean {
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("DATA", "T:"+ TokenUtils.TOKEN_CONTENT)
            Log.i("DATA", "D:"+evento.toString())
        }
        return restEvento.actualizarEvento(TokenUtils.TOKEN_CONTENT, evento.id, evento).body()?.success!!
    }
}
