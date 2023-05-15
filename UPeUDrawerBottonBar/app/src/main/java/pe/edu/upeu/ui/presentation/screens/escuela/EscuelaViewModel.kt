package pe.edu.upeu.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Escuela
import pe.edu.upeu.repository.EscuelaRepository
import javax.inject.Inject

@HiltViewModel
class EscuelaViewModel @Inject constructor(
    private val activRepo: EscuelaRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<Escuela>> by lazy {
        activRepo.reportarEscuelas()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addEscuela() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            // userRepo._isLoading.postValue(false)
            }
    }

    fun deleteActividad(toDelete: Escuela) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            activRepo.deleteEscuela(toDelete);
        }
    }
}