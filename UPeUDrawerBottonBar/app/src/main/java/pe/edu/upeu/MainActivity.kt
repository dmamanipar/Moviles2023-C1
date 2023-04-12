package pe.edu.upeu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.navigation.NavigationHost
import pe.edu.upeu.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.ui.presentation.components.Dialog
import pe.edu.upeu.ui.presentation.components.Drawer
import pe.edu.upeu.ui.presentation.components.TopBar
import pe.edu.upeu.ui.theme.BLUE800
import pe.edu.upeu.ui.theme.LightRedColorPalette
import pe.edu.upeu.ui.theme.UPeUDrawerBottonBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val darkMode = remember { mutableStateOf(false) }
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BLUE800
                )
            }
            val colors = LightRedColorPalette
            UPeUDrawerBottonBarTheme (colors = colors){
                MainScreen(darkMode = darkMode)
            }


        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    darkMode: MutableState<Boolean>
) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue =
        DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    val navigationItems = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
        Destinations.Pantalla4,
    )
    val navigationItems2 = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3,
    )
    Scaffold(
        scaffoldState = scaffoldState,

        bottomBar = {
            BottomNavigationBar(navController = navController, items = navigationItems2) },
        /*floatingActionButton = { FloatingActionButton(onClick = {}) {
            Icon(imageVector = Icons.Default.Add, contentDescription =
            "Fab Icon")
        } },
        isFloatingActionButtonDocked = false,
        floatingActionButtonPosition = FabPosition.End,*/
        topBar = {
            TopBar(
                scope,
                scaffoldState,
                openDialog = { openDialog.value = true },
                displaySnackBar = {
                    scope.launch {
                        val resultado =
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = "Nuevo SnackBar!",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Aceptar"
                            )
                        when(resultado){
                            SnackbarResult.ActionPerformed -> {
                                Log.d("MainActivity", "Snackbar Accionado")
                            }
                            SnackbarResult.Dismissed -> {
                                Log.d("MainActivity", "Snackbar Ignorado")
                            }
                        }
                    }
                }
            )
        },
        drawerContent = { Drawer(scope, scaffoldState, navController,
            items = navigationItems) },
        drawerGesturesEnabled = true
    ){
        NavigationHost(navController, darkMode)
    }
    Dialog(showDialog = openDialog.value, dismissDialog = {
        openDialog.value = false })
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val colors = LightRedColorPalette
    UPeUDrawerBottonBarTheme(colors) {
        Greeting("Android")
    }
}