package pe.edu.upeu.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import pe.edu.upeu.ui.presentation.screens.*

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
            LoginScreen( navigateToHome = {
                navController.navigate(Destinations.Pantalla1.route)
            })
        }
        composable(Destinations.Pantalla1.route) {
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
            Pantalla2(newText, darkMode)
        }
        composable(Destinations.Pantalla3.route) { Pantalla3() }

        composable(Destinations.Pantalla4.route) { Pantalla4() }

    }
}
