package pe.edu.upeu.ui.presentation.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.User
import pe.edu.upeu.modelo.UserResponse
import pe.edu.upeu.repository.UserRepository
import pe.edu.upeu.utils.TokenUtils
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepo: UserRepository
) : ViewModel(){


    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading


    private val _islogin: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val islogin: LiveData<Boolean> get() = _islogin


    val listUser = MutableLiveData<UserResponse>()

    val isError=MutableLiveData<Boolean>(false)

    fun loginSys(toData: User) {
        Log.i("LOGIN", toData.email)
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            _islogin.postValue(false)

            val totek=userRepo.loginUser(toData).body()

            delay(1500L)
            TokenUtils.TOKEN_CONTENT=totek?.token_type+" "+totek?.access_token
            Log.i("DATAXDMP", "Holas")
            listUser.postValue(totek)
            Log.i("DATAXDMP", TokenUtils.TOKEN_CONTENT)
            if(TokenUtils.TOKEN_CONTENT!="null null"){
                TokenUtils.USER_LOGIN=toData.email
                _islogin.postValue(true)
            }else{
                isError.postValue(true)
                delay(1500L)
                isError.postValue(false)
            }
            _isLoading.postValue(false)
        }
    }
}