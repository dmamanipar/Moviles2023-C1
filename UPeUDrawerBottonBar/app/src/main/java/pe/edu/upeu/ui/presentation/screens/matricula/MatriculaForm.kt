package pe.edu.upeu.ui.presentation.screens.matricula

import android.annotation.SuppressLint
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.modelo.Matricula
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.ui.presentation.components.form.ComboBox
import pe.edu.upeu.ui.presentation.components.form.ComboBoxTwo
import pe.edu.upeu.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.ui.presentation.components.form.NameTextField
import pe.edu.upeu.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.ui.presentation.screens.actividad.ActividadFormViewModel
import pe.edu.upeu.utils.TokenUtils

@Composable
fun MatriculaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: MatriculaFormViewModel = hiltViewModel()
) {


    val matriculaD: Matricula
    if (text!="0"){
        matriculaD = Gson().fromJson(text, Matricula::class.java)
    }else{
        matriculaD= Matricula(0, 0, 0, "")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(matriculaD.id!!,
        darkMode,
        navController,
        matriculaD,
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
               matricula: Matricula,
               viewModel: MatriculaFormViewModel
){

    Log.i("VERRR", "d: "+matricula?.id!!)
    val person= Matricula(0,0,0, "")

    val scope = rememberCoroutineScope()


    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms { easyForm ->
            Column {


                var listE = listOf(
                    ComboModel("Matriculado","Matriculado"),
                    ComboModel("Pendiente","Pendiente"),
                )
                ComboBox(easyForm = easyForm, "Estado:", matricula?.estado!!, listE)



                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.estado=splitCadena((lista.get(0) as EasyFormsResult.GenericStateResult<String>).value)

                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addMatricula(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editMatricula(person)
                        }
                        navController.navigate(Destinations.MatriculaUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.MatriculaUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}