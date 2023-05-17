package pe.edu.upeu.ui.presentation.screens.persona

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
import com.google.android.gms.location.*
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.modelo.Actividad
import pe.edu.upeu.modelo.Persona
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.ui.presentation.components.form.*

import pe.edu.upeu.ui.presentation.screens.persona.PersonaFormViewModel
import pe.edu.upeu.utils.TokenUtils

@Composable
fun PersonaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: PersonaFormViewModel = hiltViewModel()
) {


    val personaD: Persona
    Log.i("DATA", text)
    if (text!="0"){
       //personaD= Persona(0,"","", "","","","","")
        personaD = Gson().fromJson(text, Persona::class.java)
    }else{
        personaD= Persona(0,"","", "","","","","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(personaD.id!!,
        darkMode,
        navController,
        personaD,
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
               persona: Persona,
               viewModel: PersonaFormViewModel
){

    Log.i("VERRR", "d: "+persona?.id!!)
    val person= Persona(0,"","", "","","","","")

    val scope = rememberCoroutineScope()




    Scaffold(modifier = Modifier.padding(8.dp)){
        BuildEasyForms { easyForm ->
            Column {
                NameTextField(easyForms = easyForm, text =persona?.dni!!,"DNI :", MyFormKeys.DNI )
                NameTextField(easyForms = easyForm, text =persona?.nombre!!,"NOMBRE :", MyFormKeys.NAME )
                NameTextField(easyForms = easyForm, text =persona?.apellido_paterno!!,"APELLIDO PATERNO :", MyFormKeys.APE_PAT)
                NameTextField(easyForms = easyForm, text =persona?.apellido_materno!!,"APELLIDO MATERNO :", MyFormKeys.APE_MAT )
                NameTextField(easyForms = easyForm, text =persona?.telefono!!,"TELEFONO :", MyFormKeys.PHONE )
                NameTextField(easyForms = easyForm, text =persona?.genero!!,"GENERO :", MyFormKeys.GENERO )
                NameTextField(easyForms = easyForm, text =persona?.correo!!,"CORREO :", MyFormKeys.EMAIL )


                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        person.dni=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.nombre=(lista.get(1) as EasyFormsResult.StringResult).value
                        person.apellido_paterno=(lista.get(2) as EasyFormsResult.StringResult).value
                        person.apellido_materno=(lista.get(3) as EasyFormsResult.StringResult).value
                        person.telefono=(lista.get(4) as EasyFormsResult.StringResult).value
                        person.genero=(lista.get(5) as EasyFormsResult.StringResult).value
                        person.correo=(lista.get(6) as EasyFormsResult.StringResult).value


                        if (id==0){
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.addPersona(person)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editPersona(person)
                        }
                        navController.navigate(Destinations.PersonaUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.PersonaUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}