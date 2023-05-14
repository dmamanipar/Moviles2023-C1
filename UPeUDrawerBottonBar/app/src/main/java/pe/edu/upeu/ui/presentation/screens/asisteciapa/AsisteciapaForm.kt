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
import androidx.compose.runtime.livedata.observeAsState
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
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Asisteciapa
import pe.edu.upeu.utils.TokenUtils
import kotlinx.coroutines.delay
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.form.*

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
        asisteciapaD= Asisteciapa(0,0,"", "","","","","","","")
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

    Log.i("VERRR", "d: "+asisteciapa?.id_asisteciapa!!)
    val person= Asisteciapa(0,1,"", "","","","","","","")

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
            Column {
                NameTextField(easyForms = easyForm, text =asisteciapa?.id_asisteciapa!!,"ID. Asisteciapa:", MyFormKeys.NAME )
                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )
                ComboBox(easyForm = easyForm, "Calificacion:", asisteciapa?.calificacion!!, listE)

                var listEv = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )
                ComboBoxTwo(easyForm = easyForm, "Cui:", asisteciapa?.cui!!, listEv)


                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = asisteciapa?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = asisteciapa?.hora_reg!!, MyFormKeys.TIME, "HH:mm:ss")

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        //person.id_asisteciapa=(lista.get(0) as EasyFormsResult.Int).value
                        person.fecha=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        person.hora_reg=splitCadena((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        person.latituda=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.longituda=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.tipo=(lista.get(5) as EasyFormsResult.GenericStateResult<String>).value
                        person.cui=(lista.get(6) as EasyFormsResult.GenericStateResult<String>).value
                        person.tipo_cui=(lista.get(7) as EasyFormsResult.GenericStateResult<String>).value
                        person.user_create= TokenUtils.USER_LOGIN

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addAsistecipa(person)
                        }else{
                            person.id_asisteciapa=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editActividad(person)
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