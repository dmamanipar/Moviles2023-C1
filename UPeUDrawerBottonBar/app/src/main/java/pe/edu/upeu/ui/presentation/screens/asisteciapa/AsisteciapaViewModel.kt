package pe.edu.upeu.ui.presentation.screens.asisteciapa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Asisteciapa
import pe.edu.upeu.repository.AsisteciapaRepository
import javax.inject.Inject

@HiltViewModel
class AsisteciapaViewModel @Inject constructor(
    private val activAsisteciapaRepository: AsisteciapaRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val Asisteciapa: LiveData<List<Asisteciapa>> by lazy {
        activAsisteciapaRepository.reportarAsisteciapas()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addAsisteciapa() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
// userRepo._isLoading.postValue(false)
            }
    }
    fun deleteAsisteciapa(toDelete: Asisteciapa) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            activAsisteciapaRepository.deleteAsisteciapa(toDelete);
        }
    }
}