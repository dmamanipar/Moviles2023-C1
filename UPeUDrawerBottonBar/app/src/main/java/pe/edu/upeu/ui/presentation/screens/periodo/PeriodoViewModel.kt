package pe.edu.upeu.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Periodo
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.PeriodoRepository
import javax.inject.Inject

@HiltViewModel
class PeriodoViewModel @Inject constructor(
    private val perRepo: PeriodoRepository,

//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val peri: LiveData<List<Periodo>> by lazy {
        perRepo.reportarPeriodos()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addPeriodo() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            // userRepo._isLoading.postValue(false)
            }
    }

    fun deletePeriodo(toDelete: Periodo) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            perRepo.deletePeriodo(toDelete);
        }
    }
}