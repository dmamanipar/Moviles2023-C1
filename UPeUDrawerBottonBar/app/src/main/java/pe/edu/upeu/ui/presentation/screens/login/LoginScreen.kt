package pe.edu.upeu.ui.presentation.screens.login

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.User
import pe.edu.upeu.ui.common.DefaultButton
import pe.edu.upeu.ui.presentation.components.ErrorImageAuth
import pe.edu.upeu.ui.presentation.components.ImageLogin
import pe.edu.upeu.ui.presentation.components.ProgressBarLoading
import pe.edu.upeu.ui.theme.LightRedColorPalette
import pe.edu.upeu.ui.theme.UPeUDrawerBottonBarTheme
import pe.edu.upeu.utils.ComposeReal
import pe.edu.upeu.utils.TokenUtils

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.observeAsState(false)
    val isLogin by viewModel.islogin.observeAsState(false)
    val isError by viewModel.isError.observeAsState(false)
    val loginResul by viewModel.listUser.observeAsState()
    val scope = rememberCoroutineScope()
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ImageLogin()
        Text("Login Screen", fontSize = 40.sp)
        DefaultButton(
            text = "Log In",
            onClick= {
                viewModel.loginSys(User("","mamanipari@gmail.com","12345678"))
                scope.launch {
                    delay(3600)
                    if(isLogin){
                        Log.i("TOKENV", TokenUtils.TOKEN_CONTENT)
                        Log.i("DATA", loginResul!!.message)
                        navigateToHome.invoke()
                    }else{
                        Toast.makeText(TokenUtils.CONTEXTO_APPX,"Error al conectar",Toast.LENGTH_LONG)
                    }
                } }
        )
        ComposeReal.COMPOSE_TOP.invoke()
    }
    ErrorImageAuth(isImageValidate = isError)
    ProgressBarLoading(isLoading = isLoading)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
//val darkMode = remember { mutableStateOf(false) }
    val colors = LightRedColorPalette
    UPeUDrawerBottonBarTheme(colors) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen(
                navigateToHome = {}
            )
        }
    }
}