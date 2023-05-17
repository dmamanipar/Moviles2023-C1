package pe.edu.upeu.ui.presentation.screens.asistencia

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Asistencia
import pe.edu.upeu.repository.AsistenciaRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciaFormViewModel @Inject constructor(
    private val asistRepo: AsistenciaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getAsistencia(idX: Int): LiveData<Asistencia> {
        return asistRepo.buscarAsistenciaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addAsistencia(asistencia: Asistencia){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", asistencia.toString())
            asistRepo.insertarAsistencia(asistencia)
        }
    }
    fun editAsistencia(asistencia: Asistencia){
        viewModelScope.launch(Dispatchers.IO){
            asistRepo.modificarRemoteAsistencia(asistencia)
        }
    }
}