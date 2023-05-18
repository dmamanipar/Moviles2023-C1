package pe.edu.upeu.ui.presentation.screens.asistencia

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.gson.Gson
import pe.edu.upeu.modelo.Asistencia
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.ui.presentation.components.LoadingCard
import java.time.format.DateTimeFormatter
import pe.edu.upeu.R
import pe.edu.upeu.ui.presentation.components.ConfirmDialog
import pe.edu.upeu.utils.TokenUtils
import java.time.LocalDate

@Composable
fun AsistenciaUI (navegarEditarAsistencia: (String) -> Unit, viewModel:
AsistenciaViewModel = hiltViewModel()
){
    val asist by viewModel.asist.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    Log.i("VERX", ""+asist!!.size )

    MyApp(onAddClick = {
        //viewModel.addUser()
        navegarEditarAsistencia((0).toString())
    }, onDeleteClick = {
        viewModel.deleteAsistencia(it)
    }, asist, isLoading,
        onEditClick = {
            val jsonString = Gson().toJson(it)
            navegarEditarAsistencia(jsonString)
        }
    )
}
val formatoFecha:DateTimeFormatter? = DateTimeFormatter.ofPattern("dd-MM-yyyy")
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: ((toDelete: Asistencia) -> Unit)? = null,
    asistencias: List<Asistencia>,
    isLoading: Boolean,
    onEditClick: ((toPersona: Asistencia) -> Unit)? = null,
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    val navigationItems2 = listOf(
        Destinations.Pantalla1,
        Destinations.Pantalla2,
        Destinations.Pantalla3
    )
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue =
        DrawerValue.Closed)
    )
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomNavigationBar(navController = navController, items =
            navigationItems2)
        },
        floatingActionButton = {
            Box(modifier = Modifier.fillMaxSize()) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(all = 16.dp)
                        .align(alignment = Alignment.BottomCenter)
                        .offset(x = (16).dp, y = (-32).dp),
                    onClick = {
                        onAddClick?.invoke()
                    }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription =
                        "Fab Icon"
                    )
                }
            }
        },
        isFloatingActionButtonDocked = false,
//floatingActionButtonPosition = FabPosition.Center,
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            LazyColumn(modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.TopCenter),
                //.offset(x = (16).dp, y = (-32).dp),
                userScrollEnabled= true,
            ){
                var itemCount = asistencias.size
                if (isLoading) itemCount++
                items(count = itemCount) { index ->
                    var auxIndex = index;
                    if (isLoading) {
                        if (auxIndex == 0)
                            return@items LoadingCard()
                        auxIndex--
                    }
                    val asistencia = asistencias[auxIndex]
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 1.dp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(modifier = Modifier.padding(8.dp)) {
                            Image(
                                modifier = Modifier
                                    .size(50.dp)
                                    //.clip(CircleShape)
                                    .clip(RoundedCornerShape(8.dp)),
                                painter = rememberImagePainter(
                                    data = asistencia.calificacion,
                                    builder = {
                                        placeholder(R.drawable.bg)
                                        error(R.drawable.bg)
                                    }
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight
                            )
                            pe.edu.upeu.ui.presentation.components.Spacer()
                            Column(
                                Modifier.weight(1f),
                            ) {
                                Text("${asistencia.fecha} - ${asistencia.hora} - ${asistencia.latituda} - ${asistencia.longituda} - ${asistencia.tipo} - ${asistencia.tipo_reg} - ${asistencia.id_matricula} - ${asistencia.id_evento} - ${asistencia.id_persona} - ${asistencia.calificacion} - ${asistencia.offlinex}", fontWeight = FontWeight.Bold)
                                val datex =LocalDate.parse(asistencia.fecha!!, DateTimeFormatter.ISO_DATE)
                                var fecha=formatoFecha?.format(datex)
                                Text(""+fecha, color =
                                MaterialTheme.colors.primary)
                            }
                            pe.edu.upeu.ui.presentation.components.Spacer()

                            val showDialog = remember { mutableStateOf(false) }
                            IconButton(onClick = {
                                showDialog.value = true
                            }) {
                                Icon(Icons.Filled.Delete, "Remove", tint = MaterialTheme.colors.primary)
                            }
                            if (showDialog.value){
                                ConfirmDialog(
                                    message = "Esta seguro de eliminar?",
                                    onConfirm = {
                                        onDeleteClick?.invoke(asistencia)
                                        showDialog.value=false
                                    },
                                    onDimins = {
                                        showDialog.value=false
                                    }
                                )
                            }


                            IconButton(onClick = {
                                //onDeleteClick?.invoke(persona)
                                Log.i("VERTOKEN", "Holas")
                                Log.i("VERTOKEN", TokenUtils.TOKEN_CONTENT)
                                onEditClick?.invoke(asistencia)
                            }) {
                                Icon(Icons.Filled.Edit, "Editar", tint =
                                MaterialTheme.colors.secondary)
                            }
                        }
                    }
                }
            }
        }
    }
}