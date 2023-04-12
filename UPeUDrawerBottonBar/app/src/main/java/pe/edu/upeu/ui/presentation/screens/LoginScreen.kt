package pe.edu.upeu.ui.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import pe.edu.upeu.ui.common.DefaultButton
import pe.edu.upeu.ui.theme.LightRedColorPalette
import pe.edu.upeu.ui.theme.UPeUDrawerBottonBarTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LoginScreen(
    navigateToHome: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Login Screen", fontSize = 40.sp)
        DefaultButton(
            text = "Log In",
            onClick = navigateToHome
        )
    }
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