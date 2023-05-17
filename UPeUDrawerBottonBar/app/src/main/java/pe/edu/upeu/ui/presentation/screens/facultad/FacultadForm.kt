package pe.edu.upeu.ui.presentation.screens.facultad

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
import pe.edu.upeu.modelo.Facultad
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.form.*
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.utils.TokenUtils


@Composable
fun FacultadForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: FacultadFormViewModel=hiltViewModel()
) {

    val facultadD:Facultad
    if (text!="0"){
        facultadD = Facultad(0,"","", "",)
    //Gson().fromJson(text, Facultad::class.java)
    }else{
        facultadD= Facultad(0,"","", "",)
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(facultadD.id!!,
        darkMode,
        navController,
        facultadD,
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
    facultad: Facultad,
    viewModel: FacultadFormViewModel
){

    Log.i("VERRR", "d: "+facultad?.id!!)
    val person=Facultad(0,"","", "")

    val scope = rememberCoroutineScope()

    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms { easyForm ->
            Column {
                NameTextField(easyForms = easyForm, text =facultad?.nombrefac!!,"Nombre Facultad :", MyFormKeys.NAME )
                var listE = listOf(
                    ComboModel("Activo","Activo"),
                    ComboModel("Desactivo","Desactivo"),
                )
                ComboBox(easyForm = easyForm, "Estado:", facultad?.estado!!, listE)
                NameTextField(easyForms = easyForm, text =facultad?.iniciales!!,"Iniciales :", MyFormKeys.INIC )


                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.nombrefac=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.estado=(lista.get(1) as EasyFormsResult.StringResult).value
                        person.iniciales=(lista.get(2) as EasyFormsResult.StringResult).value

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addFacultad(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editFacultad(person)
                        }
                        navController.navigate(Destinations.FacultadUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.FacultadUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}