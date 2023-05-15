package pe.edu.upeu.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Escuela
import pe.edu.upeu.repository.ActividadRepository
import javax.inject.Inject

@HiltViewModel
class EscuelaFormViewModel @Inject constructor(
    private val activRepo: EscuelaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getActividad(idX: Int): LiveData<Escuela> {
        return activRepo.buscarEscuelaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addActividad(escuela: Escuela){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", escuela.toString())
            activRepo.insertarEscuela(escuela)
        }
    }
    fun editActividad(escuela: Escuela){
        viewModelScope.launch(Dispatchers.IO){
            activRepo.modificarRemoteEscuela(Escuela)
        }
    }
}