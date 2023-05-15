package pe.edu.upeu.ui.presentation.screens.escuela

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
import pe.edu.upeu.modelo.Escuela
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.form.*
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.utils.TokenUtils


@Composable
fun EscuelaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: EscuelaFormViewModel= hiltViewModel()
) {


    val escuelaD:Escuela
    if (text!="0"){
        escuelaD = Gson().fromJson(text, Escuela::class.java)
    }else{
        escuelaD= Escuela(0,"", "","","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(escuelaD.id!!,
        darkMode,
        navController,
        escuelaD,
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
               escuela:Escuela,
               viewModel: EscuelaFormViewModel){

    Log.i("VERRR", "d: "+escuela?.id!!)
    val person=Escuela(0,"", "","","")

    val scope = rememberCoroutineScope()

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        TokenUtils.CONTEXTO_APPX)
    locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                Log.e("LATLONX", "Lat: ${lo.latitude} Lon: ${lo.longitude}")
                person.latitud=lo.latitude.toString()
                person.longitud=lo.longitude.toString()
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

        Log.e("LATLON", "Lat: ${person.latitud} Lon: ${person.longitud}")
        delay(1500L)
        if (fusedLocationClient != null) {
            fusedLocationClient!!.removeLocationUpdates(locationCallback);
            fusedLocationClient = null;
        }

    }

    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms { easyForm ->
            Column {
                NameTextField(easyForms = easyForm, text =escuela?.facultad_nom!!,"Facultad. Nom:", MyFormKeys.NAME )
                var listE = listOf(
                    ComboModel("Ingenieria","Ingenieria"),
                    ComboModel("Enfermeria","Enfermeria"),
                )
                ComboBox(easyForm = easyForm, "Estado:", escuela?.estado!!, listE)

                var listEv = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                    )
                ComboBox(easyForm = easyForm, "Inicialeseap:", escuela?.inicialeseap!!, listE)

                var listEv = listOf(
                    ComboModel("Eap","Eap"),
                    ComboModel("NOeap","Noeap"),
                )
                ComboBox(easyForm = easyForm, "Nombreeap:", escuela?.nombreeap!!, listE)

                var listEv = listOf(
                    ComboModel("Ingenieria de sistemas","Ingenieria de sistemas"),
                    ComboModel("Ingenieria","Ingenieria"),
                )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.nombre_actividad=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.estado=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        person.evaluar=splitCadena((lista.get(2) as EasyFormsResult.GenericStateResult<String>).value)
                        person.fecha=(lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                        person.horai=(lista.get(4) as EasyFormsResult.GenericStateResult<String>).value
                        person.min_toler=(lista.get(5) as EasyFormsResult.GenericStateResult<String>).value
                        person.user_create= TokenUtils.USER_LOGIN

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addEscuela(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editEscuela(person)
                        }
                        navController.navigate(Destinations.EscuelaUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.EscuelaUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}