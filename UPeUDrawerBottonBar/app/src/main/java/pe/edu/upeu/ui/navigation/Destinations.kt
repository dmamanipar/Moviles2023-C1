package pe.edu.upeu.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Login : Destinations( "login", "Login", Icons.Filled.Settings )
    object Logout : Destinations( "logout", "Salir", Icons.Filled.Settings )
    object Pantalla1 : Destinations( "pantalla1", "Pantalla 1", Icons.Filled.Home )
    object Pantalla2 : Destinations("pantalla2/?newText={newText}", "Pantalla 2", Icons.Filled.Settings) {
        fun createRoute(newText: String) = "pantalla2/?newText=$newText"
    }
    object Pantalla3 : Destinations("pantalla3", "Pantalla 3", Icons.Filled.Favorite)
    object Pantalla4 : Destinations("pantalla4", "Pantalla 4dfsx", Icons.Filled.Face )

    object Pantalla5 : Destinations("pantalla5", "Facultades", Icons.Filled.Fastfood )

    object ActividadUI: Destinations("actividadUI", "Adm. Actividad", Icons.Filled.Fastfood)

    object FacultadUI: Destinations("facultadUI", "Adm. Facu", Icons.Filled.Fastfood)

    object ActividadForm: Destinations("actividadForm?actId={actId}",
        "Actividad Form", Icons.Filled.DynamicForm){
        fun passId(actId:String?):String{
            return "actividadForm?actId=$actId"
        }
    }
    object FacultadForm: Destinations("facultadForm?facuId={facuId}",
        "Facultad Form", Icons.Filled.DynamicForm){
        fun passId(facuId:String?):String{
            return "facultadForm?facuId=$facuId"
        }
    }

}