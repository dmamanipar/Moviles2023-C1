package pe.edu.upeu.ui.presentation.screens.actividad

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Periodo
import pe.edu.upeu.repository.ActividadRepository
import pe.edu.upeu.repository.PeriodoRepository
import javax.inject.Inject

@HiltViewModel
class PeriodoFormViewModel @Inject constructor(
    private val perRepo: PeriodoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getPeriodo(idX: Int): LiveData<Periodo> {
        return perRepo.buscarPeriodoId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addPeriodo(periodo: Periodo){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", periodo.toString())
            perRepo.insertarPeriodo(periodo)
        }
    }
    fun editPeriodo(periodo: Periodo){
        viewModelScope.launch(Dispatchers.IO){
            perRepo.modificarRemotePeriodo(periodo)
        }
    }
}