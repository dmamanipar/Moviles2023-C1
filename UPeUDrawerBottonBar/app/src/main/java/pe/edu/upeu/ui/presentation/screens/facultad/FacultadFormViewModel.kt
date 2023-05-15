package pe.edu.upeu.ui.presentation.screens.facultad

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Facultad
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.FacultadRepository
import javax.inject.Inject

@HiltViewModel
class FacultadFormViewModel @Inject constructor(
    private val facuRepo: FacultadRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getFacultad(idX: Int): LiveData<Facultad> {
        return facuRepo.buscarFacultadId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addFacultad(facultad: Facultad){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", facultad.toString())
            facuRepo.insertarFacultad(facultad)
        }
    }
    fun editFacultad(facultad: Facultad){
        viewModelScope.launch(Dispatchers.IO){
            facuRepo.modificarRemoteFacultad(facultad)
        }
    }
}
