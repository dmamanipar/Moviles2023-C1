package pe.edu.upeu.ui.presentation.screens.matricula

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Matricula
import pe.edu.upeu.repository.MatriculaRepository
import javax.inject.Inject

@HiltViewModel
class MatriculaFormViewModel @Inject constructor(
    private val matRepo: MatriculaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getMatricula(idX: Int): LiveData<Matricula> {
        return matRepo.buscarMatriculaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addMatricula(matricula: Matricula){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", matricula.toString())
            matRepo.insertarMatricula(matricula)
        }
    }
    fun editMatricula(matricula: Matricula){
        viewModelScope.launch(Dispatchers.IO){
            matRepo.modificarRemoteMatricula(matricula)
        }
    }
}