package pe.edu.upeu.ui.presentation.screens.evento

import android.annotation.SuppressLint
import android.os.Build
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import pe.edu.upeu.modelo.Evento
import pe.edu.upeu.modelo.ComboModel
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.Spacer
import pe.edu.upeu.ui.presentation.components.form.*

import pe.edu.upeu.ui.presentation.screens.evento.EventoFormViewModel
import pe.edu.upeu.utils.TokenUtils

@Composable
fun EventoForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: EventoFormViewModel = hiltViewModel()
) {


    val eventoD: Evento
    //Log.i("DATA", text)
    if (text!="0"){
        //evento= evento(0,"","", "","","","","")
        eventoD = Gson().fromJson(text, Evento::class.java)
    }else{
        eventoD= Evento(0,"","", "","","","","","","","")
    }
    val isLoading by viewModel.isLoading.observeAsState(false)
    formulario(eventoD.id!!,
        darkMode,
        navController,
        eventoD,
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
               evento: Evento,
               viewModel: EventoFormViewModel
){

    Log.i("VERRR", "d: "+evento?.id!!)
    val even= Evento(0,"","", "","","","","","","","")

    val scope = rememberCoroutineScope()




    Scaffold(modifier = Modifier.padding(8.dp).verticalScroll(rememberScrollState())){
        BuildEasyForms { easyForm ->

            Column {
                NameTextField(easyForms = easyForm, text =evento?.nom_evento!!,"NOM_EVENTO :", MyFormKeys.NOM_EVENTO )
                NameTextField(easyForms = easyForm, text =evento?.fecha!!,"FECHA :", MyFormKeys.FECHA )
                NameTextField(easyForms = easyForm, text =evento?.horai!!,"HORAI :", MyFormKeys.HORAI)
                NameTextField(easyForms = easyForm, text =evento?.min_toler!!,"MIN_TOLER :", MyFormKeys.MIN_TOLER )
                NameTextField(easyForms = easyForm, text =evento?.latitud!!,"LATITUD :", MyFormKeys.LATITUD )
                NameTextField(easyForms = easyForm, text =evento?.longitud!!,"LONGITUD :", MyFormKeys.LONGITUD )
                NameTextField(easyForms = easyForm, text =evento?.estado!!,"ESTADO :", MyFormKeys.ESTADO )
                NameTextField(easyForms = easyForm, text =evento?.evaluar!!,"EVALUAR :", MyFormKeys.EVALUAR )
                NameTextField(easyForms = easyForm, text =evento?.perfil_evento!!,"PERFIL_EVE :", MyFormKeys.PERFIL_EVE )
                NameTextField(easyForms = easyForm, text =evento?.offline!!,"OFFLINE :", MyFormKeys.OFFLINE )

                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()
                        even.nom_evento=(lista.get(0) as EasyFormsResult.StringResult).value
                        even.fecha=(lista.get(1) as EasyFormsResult.StringResult).value
                        even.horai=(lista.get(2) as EasyFormsResult.StringResult).value
                        even.min_toler=(lista.get(3) as EasyFormsResult.StringResult).value
                        even.latitud=(lista.get(4) as EasyFormsResult.StringResult).value
                        even.longitud=(lista.get(5) as EasyFormsResult.StringResult).value
                        even.estado=(lista.get(6) as EasyFormsResult.StringResult).value
                        even.evaluar=(lista.get(7) as EasyFormsResult.StringResult).value
                        even.perfil_evento=(lista.get(8) as EasyFormsResult.StringResult).value
                        even.offline=(lista.get(9) as EasyFormsResult.StringResult).value


                        if (id==0){
                            Log.i("MODIFICAR", "M:"+even)
                            viewModel.addEvento(even)
                        }else{
                            even.id=id
                            Log.i("MODIFICAR", "M:"+even)
                            viewModel.editEvento(even)
                        }
                        navController.navigate(Destinations.EventoUI.route)
                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.EventoUI.route)
                    }
                }
            }
        }
    }

}

fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}