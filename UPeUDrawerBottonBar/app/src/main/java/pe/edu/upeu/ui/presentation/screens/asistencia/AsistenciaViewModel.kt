package pe.edu.upeu.ui.presentation.screens.asistencia

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Asistencia
import pe.edu.upeu.repository.AsistenciaRepository
import javax.inject.Inject

@HiltViewModel
class AsistenciaViewModel @Inject constructor(
    private val asisteRepo: AsistenciaRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val asist: LiveData<List<Asistencia>> by lazy {
        asisteRepo.reportarAsistencias()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addAsistencia() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
                // userRepo._isLoading.postValue(false)
            }
    }

    fun deleteAsistencia(toDelete: Asistencia) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            asisteRepo.deleteAsistencia(toDelete);
        }
    }
}