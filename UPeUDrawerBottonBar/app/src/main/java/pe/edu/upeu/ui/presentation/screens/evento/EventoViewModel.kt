package pe.edu.upeu.ui.presentation.screens.evento

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Evento
import pe.edu.upeu.repository.EventoRepository
import javax.inject.Inject

@HiltViewModel
class EventoViewModel @Inject constructor(
    private val evenRepo: EventoRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val eve: LiveData<List<Evento>> by lazy {
        evenRepo.reportarEventos()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addEvento() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
                // userRepo._isLoading.postValue(false)
            }
    }

    fun deleteEvento(toDelete: Evento) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            evenRepo.deleteEvento(toDelete);
        }
    }
}