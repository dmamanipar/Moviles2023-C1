package pe.edu.upeu.ui.presentation.screens.matricula

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Matricula
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.MatriculaRepository
import javax.inject.Inject

@HiltViewModel
class MatriculaViewModel @Inject constructor(
    private val matRepo: MatriculaRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val matri: LiveData<List<Matricula>> by lazy {
        matRepo.reportarMatriculas()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addMatricula() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
                // userRepo._isLoading.postValue(false)
            }
    }

    fun deleteMatricula(toDelete: Matricula) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            matRepo.deleteMatricula(toDelete);
        }
    }
}