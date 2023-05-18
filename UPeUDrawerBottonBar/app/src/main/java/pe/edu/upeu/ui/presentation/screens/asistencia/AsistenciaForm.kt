package pe.edu.upeu.ui.presentation.screens.asistencia

import android.annotation.SuppressLint
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import pe.edu.upeu.modelo.Asistencia
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.form.*
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.utils.TokenUtils


@Composable
fun AsistenciaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: AsistenciaFormViewModel = hiltViewModel()
) {


    val asistenciaD:Asistencia
    if (text!="0"){
        asistenciaD = Gson().fromJson(text, Asistencia::class.java)
    }else{
        asistenciaD= Asistencia(0,0,0,"","","","","","","",0,"" )
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(asistenciaD.id!!,
        darkMode,
        navController,
        asistenciaD,
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
               asistencia:Asistencia,
               viewModel: AsistenciaFormViewModel){

    Log.i("VERRR", "d: "+asistencia?.id!!)
    val person=Asistencia(0,1,1, "","","","","","","",0,"")

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
        BuildEasyForms { easyForm ->
            Column {
                NameTextField(easyForms = easyForm, text =asistencia?.tipo!!, "Tipo:", MyFormKeys.TIPO)
                NameTextField(easyForms = easyForm, text =asistencia?.tipo_reg!!, "Tipo de Registro:", MyFormKeys.TIPO_REG)
                NameTextField(easyForms = easyForm, text =(asistencia?.calificacion!!).toString(), "Tipo de Registro:", MyFormKeys.CAL)
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = asistencia?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = asistencia?.hora!!, MyFormKeys.TIME, "HH:mm:ss")
                NameTextField(easyForms = easyForm, text =asistencia?.id_persona!!, "Persona:",MyFormKeys.PERSON)
                NameTextField(easyForms = easyForm, text =asistencia?.offlinex!!, "Offlinex:", MyFormKeys.OFFLIN)

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.tipo=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.tipo_reg=(lista.get(1) as EasyFormsResult.StringResult).value
                        person.calificacion=((lista.get(2) as EasyFormsResult.StringResult).value).toInt()
                        person.fecha=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.hora=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.id_persona=(lista.get(5) as EasyFormsResult.StringResult).value
                        person.offlinex=(lista.get(6) as EasyFormsResult.StringResult).value
                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addAsistencia(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editAsistencia(person)
                        }
                        navController.navigate(Destinations.AsistenciaUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.AsistenciaUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}