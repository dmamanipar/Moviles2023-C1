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
import androidx.compose.runtime.getValue
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
import pe.edu.upeu.ui.presentation.components.Spacer

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
        asisteciapaD= Asisteciapa(0,0,"", "","","","",2 ,"","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(asisteciapaD.id!!,
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
    val person= Asisteciapa(0,1,"", "","","","",2,"","")

    /*val scope = rememberCoroutineScope()

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

    }*/

    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms() { easyForm ->
            Column {
                DatePickerCustom(easyForm = easyForm, label = "Fecha", texts = asisteciapa?.fecha!!, MyFormKeys.FECHA,"yyyy-MM-dd")
                TimePickerCustom(easyForm = easyForm, label = "Hora", texts = asisteciapa?.hora_reg!!, MyFormKeys.TIME, "HH:mm:ss")
                NameTextField(easyForms = easyForm, text =asisteciapa?.latituda!!,"latituda", MyFormKeys.LATITUDA )
                NameTextField(easyForms = easyForm, text =asisteciapa?.longituda!!,"longituda", MyFormKeys.LONGITUDA )
                NameTextField(easyForms = easyForm, text =asisteciapa?.tipo!!,"tipo", MyFormKeys.TIPO )
                NameTextField(easyForms = easyForm, text =asisteciapa?.cui!!,"cui", MyFormKeys.CUI )
                NameTextField(easyForms = easyForm, text =asisteciapa?.tipo_cui!!,"tipo_cui", MyFormKeys.TIPO_CUI )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()

                        person.fecha=(lista.get(0) as EasyFormsResult.GenericStateResult<String>).value
                        person.hora_reg=(lista.get(1) as EasyFormsResult.GenericStateResult<String>).value
                        person.latituda=(lista.get(2) as EasyFormsResult.StringResult).value
                        person.longituda=(lista.get(3) as EasyFormsResult.StringResult).value
                        person.tipo=(lista.get(4) as EasyFormsResult.StringResult).value
                        person.cui=(lista.get(5) as EasyFormsResult.StringResult).value
                        person.tipo_cui=(lista.get(6) as EasyFormsResult.StringResult).value
                        person.calificacion=4

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addAsistecipa(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editAsisteciapa(person)
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