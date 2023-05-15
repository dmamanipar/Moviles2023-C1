package pe.edu.upeu.ui.presentation.screens.facultad

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import pe.edu.upeu.modelo.Facultad

import pe.edu.upeu.repository.FacultadRepository
import javax.inject.Inject

@HiltViewModel
class FacultadViewModel @Inject constructor(
    private val facuRepo: FacultadRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val facultades: LiveData<List<Facultad>> by lazy {
        facuRepo.reportarFacultad()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addFacultad() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
                // userRepo._isLoading.postValue(false)
            }
    }

    fun deleteFacultad(toDelete: Facultad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            facuRepo.deleteFacultad(toDelete);
        }
    }
}