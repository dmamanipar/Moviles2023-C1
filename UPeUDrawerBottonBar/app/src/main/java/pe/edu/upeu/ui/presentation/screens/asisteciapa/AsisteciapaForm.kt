package pe.edu.upeu.ui.presentation.screens.asisteciapa

import android.annotation.SuppressLint
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Asisteciapa
import pe.edu.upeu.ui.presentation.components.form.*
import pe.edu.upeu.utils.TokenUtils

@Composable
fun AsisteciapaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: AsisteciapaFormViewModel = hiltViewModel()
) {


    val asisteciapaD: Asisteciapa
    if (text!="0"){
        asisteciapaD = Gson().fromJson(text, Asisteciapa::class.java)
    }else{
        asisteciapaD= Asisteciapa(0,0,"", "","","","","","","","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(asisteciapaD.id_asisteciapa!!,
        darkMode,
        navController,
        asisteciapaD,
        viewModel
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Int,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               asisteciapa: Asisteciapa,
               viewModel: AsisteciapaFormViewModel
){

    Log.i("VERRR", "d: "+asisteciapa?.id!!)
    val person= Asisteciapa(0,1,"", "","","","","","","","")

    val scope = rememberCoroutineScope()

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        TokenUtils.CONTEXTO_APPX)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                Log.e("LATLONX", "Lat: ${lo.latitude} Lon: ${lo.longitude}")
                person.latituda=lo.latitude.toString()
                person.longituda=lo.longitude.toString()
            }
        }
    }
    scope.launch{
        val locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        Log.e("LATLON", "Lat: ${person.latituda} Lon: ${person.longituda}")
        delay(1500L)
        if (fusedLocationClient != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback);
            fusedLocationClient = null;
        }

    }

    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms() { easyForm ->
            Column() {
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = asisteciapa?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = asisteciapa?.hora_reg!!, MyFormKeys.TIME, "HH:mm:ss")

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        asisteciapa.id_actividad=(lista.get(0) as EasyFormsResult.StringResult).value
                        asisteciapa.fecha=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        asisteciapa.hora_reg=splitCadena((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        asisteciapa.latituda=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        asisteciapa.longituda=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        asisteciapa.tipo=(lista.get(5) as EasyFormsResult.GenericStateResult<String>).value
                        asisteciapa.calificacion=(lista.get(6) as EasyFormsResult.GenericStateResult<String>).value
                        asisteciapa.cui=(lista.get(7) as EasyFormsResult.GenericStateResult<String>).value
                        asisteciapa.tipo_cui=(lista.get(8) as EasyFormsResult.GenericStateResult<String>).value

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+asisteciapa)
                            viewModel.addActividad(asisteciapa)
                        }else{
                            asisteciapa.id_asisteciapa=id
                            Log.i("MODIFICAR", "M:"+asisteciapa)
                            viewModel.editAsisteciapa(asisteciapa)
                        }

                        navController.navigate(Destinations.AsisteciapaUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.AsisteciapaUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}