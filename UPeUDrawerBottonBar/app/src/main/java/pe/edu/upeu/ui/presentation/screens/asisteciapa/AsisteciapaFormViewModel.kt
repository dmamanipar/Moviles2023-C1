package pe.edu.upeu.ui.presentation.screens.asisteciapa

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Asisteciapa
import pe.edu.upeu.repository.AsisteciapaRepository
import javax.inject.Inject

class AsisteciapaFormViewModel @Inject constructor(
    private val asisRepo: AsisteciapaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getAsisteciapa(idX: Int): LiveData<Asisteciapa> {
        return asisRepo.buscarAsisteciapaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addAsistecipa(asisteciapa: Asisteciapa){
            Log.i("REAL", asisteciapa.toString())
        viewModelScope.launch(Dispatchers.IO){
            asisRepo.insertarAsisteciapa(asisteciapa)
        }

        }

    fun editAsisteciapa(asisteciapa: Asisteciapa){
        viewModelScope.launch(Dispatchers.IO){
            asisRepo.modificarRemoteAsisteciapa(asisteciapa)
        }
    }
}