package pe.edu.upeu

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*
import pe.edu.upeu.servicio.ServicioPrimerPlano
import pe.edu.upeu.servicio.ServicioSegundoPlano
import pe.edu.upeu.ui.theme.PermisosServiciosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       /* if (!foregroundServiceRunning()) {
            val serviceIntent = Intent(this, ServicioPrimerPlano::class.java)
            this.startForegroundService(serviceIntent)
        }*/

        setContent {
            LaunchedEffect(true){

            }
            PermisosServiciosTheme {
                ContextoActUtil.CONTEXTO_APPX=this
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }


    /*fun foregroundServiceRunning(): Boolean {
        val activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (ServicioPrimerPlano::class.java.name == service.service.className) {
                return true
            }
        }
        return false
    }*/

}

fun sharePreference(){
    var sp = PreferenceManager.getDefaultSharedPreferences(ContextoActUtil.CONTEXTO_APPX);
    var meditor: SharedPreferences.Editor
    meditor=sp.edit()
    meditor.putString("NOMBRE", "David Mamani")
    meditor.commit()
}

fun recuperarSharedf(){
    var ss=PreferenceManager.getDefaultSharedPreferences(ContextoActUtil.CONTEXTO_APPX)
        .getString("NOMBRE", "vacio")
    Log.i("RESULT", ss.toString())
}



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Greeting(name: String) {

    //val otorgarp = rememberPermissionState(android.Manifest.permission.CAMERA)
    val otorgarp = rememberMultiplePermissionsState(permissions = listOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.CAMERA
    ))


    Column() {
        Text(text = "Hello $name!")
        ButtonParticular(texto = "Prueba", onClick = { sharePreference() })
        ButtonParticular(texto = "Recuperar", onClick = { recuperarSharedf() })
        ButtonParticular(texto = "permiso") {
            if (otorgarp.allPermissionsGranted){
                Toast.makeText(ContextoActUtil.CONTEXTO_APPX, "Permiso concedido",Toast.LENGTH_SHORT).show()
            }else{
                if (otorgarp.shouldShowRationale){
                    Toast.makeText(ContextoActUtil.CONTEXTO_APPX, "La aplicacion requiere este permiso",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(ContextoActUtil.CONTEXTO_APPX, "El permiso fue denegado",Toast.LENGTH_SHORT).show()
                }
                otorgarp.launchMultiplePermissionRequest()
            }
        }
        ButtonParticular(texto = "Servicio Primer Plano") {
            if (!foregroundServiceRunning()) {
                val serviceIntent = Intent(ContextoActUtil.CONTEXTO_APPX, ServicioPrimerPlano::class.java)
                ContextoActUtil.CONTEXTO_APPX.startForegroundService(serviceIntent)
            }
        }
        ButtonParticular(texto = "Servicio segundo Plano") {
            if (!backgroundServiceRunning()) {
                val serviceIntent = Intent(ContextoActUtil.CONTEXTO_APPX, ServicioSegundoPlano::class.java)
                ContextoActUtil.CONTEXTO_APPX.startService(serviceIntent)
            }
        }

    }
}

fun foregroundServiceRunning(): Boolean {
    val activityManager = ContextoActUtil.CONTEXTO_APPX.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
        if (ServicioPrimerPlano::class.java.name == service.service.className) {
            return true
        }
    }
    return false
}

fun backgroundServiceRunning(): Boolean {
    val activityManager = ContextoActUtil.CONTEXTO_APPX.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
        if (ServicioSegundoPlano::class.java.name == service.service.className) {
            return true
        }
    }
    return false
}

object ContextoActUtil {
    lateinit var CONTEXTO_APPX:Context
}

@Composable
fun ButtonParticular(
    texto:String,
    onClick:()->Unit
){
    Spacer(modifier = Modifier.height(2.dp))
    Button(onClick = onClick, ) {
        Text(text = texto)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PermisosServiciosTheme {
        Greeting("Android")
    }
}


