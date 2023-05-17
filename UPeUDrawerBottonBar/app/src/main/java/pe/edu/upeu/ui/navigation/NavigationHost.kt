package pe.edu.upeu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
import androidx.navigation.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import pe.edu.upeu.ui.presentation.screens.*
import pe.edu.upeu.ui.presentation.screens.actividad.ActividadForm
import pe.edu.upeu.ui.presentation.screens.asistencia.AsistenciaForm
import pe.edu.upeu.ui.presentation.screens.actividad.ActividadUI
import pe.edu.upeu.ui.presentation.screens.asistencia.AsistenciaUI
import pe.edu.upeu.ui.presentation.screens.login.LoginScreen
import pe.edu.upeu.utils.ComposeReal

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationHost(
    navController: NavHostController,
    darkMode: MutableState<Boolean>
) {
    NavHost(
        navController = navController, startDestination = Destinations.Login.route
    ) {
        composable(Destinations.Login.route){
            //ComposeReal.TITLE_TOP=Destinations.Login.title
            LoginScreen( navigateToHome = {
                navController.navigate(Destinations.Pantalla1.route)
            })
        }
        composable(Destinations.Pantalla1.route) {
            ComposeReal.TITLE_TOP=Destinations.Pantalla1.title
            Pantalla1(
                navegarPantalla2 = { newText ->navController.navigate(Destinations.Pantalla2.createRoute(newText))
                }
            )
        }
        composable( Destinations.Pantalla2.route,
            arguments = listOf(navArgument("newText") {
                defaultValue = "Pantalla 2"
            })
        ) { navBackStackEntry ->
            var newText =
                navBackStackEntry.arguments?.getString("newText")
            requireNotNull(newText)
            ComposeReal.TITLE_TOP=Destinations.Pantalla2.title
            Pantalla2(newText, darkMode)
        }

        composable(Destinations.Pantalla3.route) {
            ComposeReal.TITLE_TOP=Destinations.Pantalla3.title
            Pantalla3()
        }

        composable(Destinations.Pantalla4.route) {
            ComposeReal.TITLE_TOP=Destinations.Pantalla4.title
            Pantalla4()
        }

        composable(Destinations.ActividadUI.route){
            ComposeReal.TITLE_TOP=Destinations.ActividadUI.title
            ActividadUI(
                navegarEditarAct = { newText ->
                    navController.navigate(Destinations.ActividadForm.passId(newText))
                }
            )
        }
        composable(
            Destinations.ActividadForm.route,
            arguments = listOf(navArgument("actId") {
                defaultValue = "actId"
            })
        ) { navBackStackEntry ->
            var actId = navBackStackEntry.arguments?.getString("actId")
            requireNotNull(actId)
            ComposeReal.TITLE_TOP = Destinations.ActividadForm.title
            ActividadForm(actId, darkMode, navController)
        }

        composable(Destinations.AsistenciaUI.route){
            ComposeReal.TITLE_TOP=Destinations.AsistenciaUI.title
            AsistenciaUI(
                navegarEditarAsist= { newText ->
                    navController.navigate(Destinations.AsistenciaForm.passId(newText))
                }
            )
        }
        composable(
            Destinations.AsistenciaForm.route,
            arguments = listOf(navArgument("asistId") {
                defaultValue = "asistId"
            })
        ) { navBackStackEntry ->
            var asistId = navBackStackEntry.arguments?.getString("asistId")
            requireNotNull(asistId)
            ComposeReal.TITLE_TOP = Destinations.AsistenciaForm.title
            AsistenciaForm(asistId, darkMode, navController)
        }


    }
}
