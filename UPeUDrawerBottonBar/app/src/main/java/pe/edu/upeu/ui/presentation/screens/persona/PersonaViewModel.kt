package pe.edu.upeu.ui.presentation.screens.persona

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Persona
import pe.edu.upeu.repository.PersonaRepository
import javax.inject.Inject

@HiltViewModel
class PersonaViewModel @Inject constructor(
    private val personRepo: PersonaRepository,
//private val matRepo: MatriculaRepository
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val perso: LiveData<List<Persona>> by lazy {
        personRepo.reportarPersonas()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addPersona() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
                // userRepo._isLoading.postValue(false)
            }
    }

    fun deletePersona(toDelete: Persona) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            personRepo.deletePersona(toDelete);
        }
    }
}