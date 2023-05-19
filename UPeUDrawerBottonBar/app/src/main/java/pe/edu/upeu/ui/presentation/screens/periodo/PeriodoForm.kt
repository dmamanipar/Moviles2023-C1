package pe.edu.upeu.ui.presentation.screens.actividad

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
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.modelo.Periodo
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.form.*
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.utils.TokenUtils


@Composable
fun PeriodoForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: PeriodoFormViewModel= hiltViewModel()
) {


    val periododD:Periodo
    if (text!="0"){
        periododD = Gson().fromJson(text, Periodo::class.java)
    }else{
        periododD= Periodo(0,"","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(periododD.id!!,
        darkMode,
        navController,
        periododD,
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
               periodo: Periodo,
               viewModel: PeriodoFormViewModel){

    Log.i("VERRR", "d: "+periodo?.id!!)
    val person=Periodo(0,"","",)

    val scope = rememberCoroutineScope()


    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms { easyForm ->
            Column {
                NameTextField(easyForms = easyForm, text =periodo?.nombre_periodo!!,"Nomb. Periodo:", MyFormKeys.NAME )
                NameTextField(easyForms = easyForm, text =periodo?.estado!!,"Estado:", MyFormKeys.ESTA )

                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.nombre_periodo=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.estado=((lista.get(1) as EasyFormsResult.StringResult).value)

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addPeriodo(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editPeriodo(person)
                        }
                        navController.navigate(Destinations.PeriodoUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.PeriodoUI.route)
                    }
                }
            }
        }
    }

}

//fun splitCadena(data:String):String{
    //return if(data!="") data.split("-")[0] else ""
//}