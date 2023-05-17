package pe.edu.upeu.ui.presentation.screens.escuela

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Escuela
import pe.edu.upeu.repository.EscuelaRepository
import javax.inject.Inject

@HiltViewModel
class EscuelaFormViewModel @Inject constructor(
    private val activRepo: EscuelaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getEscuela(idX: Int): LiveData<Escuela> {
        return activRepo.buscarEscuelaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addEscuela(escuela: Escuela){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", escuela.toString())
            activRepo.insertarEscuela(escuela)
        }
    }
    fun editEscuela(escuela: Escuela){
        viewModelScope.launch(Dispatchers.IO){
            activRepo.modificarRemoteEscuela(Escuela)
        }
    }
}