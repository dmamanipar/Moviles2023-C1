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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.navigation.NavigationHost
import pe.edu.upeu.ui.presentation.components.*
import pe.edu.upeu.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        setContent {
            val systemUiController = rememberSystemUiController()
            val darkMode = remember { mutableStateOf(false) }
            val themeType = remember {mutableStateOf(ThemeType.PURPLE)}
            val isDarkMode = remember {mutableStateOf(false)}

            val themeFunction: @Composable (isDarkMode: Boolean, content:
            @Composable () -> Unit) -> Unit =
                when (themeType.value) {
                    ThemeType.PURPLE -> { isDarkMode, content ->
                        PurpleTheme(isDarkMode, content) }
                    ThemeType.RED -> { isDarkMode, content ->
                        RedTheme(isDarkMode, content) }
                    ThemeType.YELLOW -> { isDarkMode, content ->
                        YellowTheme(isDarkMode, content) }
                    ThemeType.GREEN->{isDarkMode, content->
                        GreenTheme(isDarkMode, content) }
                    ThemeType.DROWN->{isDarkMode, content->
                        DrownTheme(isDarkMode, content)}
                }
            themeFunction.invoke(isDarkMode.value) {
                MainScreen(darkMode = isDarkMode, themeType = themeType)
            }

            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(Color.LightGray,
                    darkIcons = useDarkIcons)
            }

            /*SideEffect {
                systemUiController.setStatusBarColor(
                    color = BLUE800
                )
            }
            val colors = LightRedColorPalette
            UPeUDrawerBottonBarTheme (colors = colors){
                MainScreen(darkMode = darkMode)
            }*/


        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    darkMode: MutableState<Boolean>,
    themeType: MutableState<ThemeType>
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
            CustomTopAppBar(
                darkMode = darkMode,
                themeType = themeType,
                navController = navController,
                scope = scope,
                scaffoldState = scaffoldState,
                openDialog = { openDialog.value = true },
            )

            /*TopBar(
                navController,
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
            )*/
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