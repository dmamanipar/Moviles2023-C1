package pe.edu.upeu.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.repository.ActividadRepository
import javax.inject.Inject

@HiltViewModel
class ActividadFormViewModel @Inject constructor(
    private val activRepo: ActividadRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getActividad(idX: Int): LiveData<Actividad> {
        return activRepo.buscarActividadId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addActividad(actividad: Actividad){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", actividad.toString())
            activRepo.insertarActividad(actividad)
        }
    }
    fun editActividad(actividad: Actividad){
        viewModelScope.launch(Dispatchers.IO){
            activRepo.modificarRemoteActividad(actividad)
        }
    }
}