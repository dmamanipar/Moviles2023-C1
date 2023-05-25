package pe.edu.upeu.ui.presentation.screens.evento

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import pe.edu.upeu.modelo.Evento
import pe.edu.upeu.repository.EventoRepository
import javax.inject.Inject

@HiltViewModel
class EventoFormViewModel @Inject constructor(
    private val evenRepo: EventoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getEvento(idX: Int): LiveData<Evento> {
        return evenRepo.buscarEventoId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addEvento(evento: Evento){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", evento.toString())
            evenRepo.insertarEvento(evento)
        }
    }
    fun editEvento(evento: Evento){
        viewModelScope.launch(Dispatchers.IO){
            evenRepo.modificarRemoteEvento(evento)
        }
    }
}