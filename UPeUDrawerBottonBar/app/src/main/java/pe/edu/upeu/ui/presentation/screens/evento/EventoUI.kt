package pe.edu.upeu.ui.presentation.screens.evento

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
import pe.edu.upeu.R
import pe.edu.upeu.modelo.Evento
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.ui.presentation.components.ConfirmDialog
import pe.edu.upeu.ui.presentation.components.LoadingCard
import pe.edu.upeu.ui.presentation.screens.evento.EventoViewModel
import pe.edu.upeu.utils.TokenUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun EventoUI (navegarEditarEvento: (String) -> Unit, viewModel:
EventoViewModel = hiltViewModel()
){
    val eve by viewModel.eve.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    Log.i("VERX", ""+eve!!.size )

    MyApp(onAddClick = {
        //viewModel.addUser()
        navegarEditarEvento((0).toString())
    }, onDeleteClick = {
        viewModel.deleteEvento(it)
    }, eve, isLoading,
        onEditClick = {
            val jsonString = Gson().toJson(it)
            navegarEditarEvento(jsonString)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: ((toDelete: Evento) -> Unit)? = null,
    eventos: List<Evento>,
    isLoading: Boolean,
    onEditClick: ((toEvento: Evento) -> Unit)? = null,
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
                var itemCount = eventos.size
                if (isLoading) itemCount++
                items(count = itemCount) { index ->
                    var auxIndex = index;
                    if (isLoading) {
                        if (auxIndex == 0)
                            return@items LoadingCard()
                        auxIndex--
                    }
                    val evento = eventos[auxIndex]
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
                                    data = evento.nom_evento,
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
                                Text("${evento.nom_evento} - ${evento.fecha} -${evento.horai} - ${evento.min_toler} - ${evento.latitud} - ${evento.longitud}- ${evento.estado}- ${evento.evaluar}- ${evento.perfil_evento}- ${evento.offline}", fontWeight = FontWeight.Bold)

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
                                        onDeleteClick?.invoke(evento)
                                        showDialog.value=false
                                    },
                                    onDimins = {
                                        showDialog.value=false
                                    }
                                )
                            }


                            IconButton(onClick = {
                                //onDeleteClick?.invoke(evento)
                                Log.i("VERTOKEN", "Holas")
                                Log.i("VERTOKEN", TokenUtils.TOKEN_CONTENT)
                                Log.i("VERTOKEN", evento.toString())
                                onEditClick?.invoke(evento)
                            }) {
                                Icon(
                                    Icons.Filled.Edit, "Editar", tint =
                                    MaterialTheme.colors.secondary)
                            }
                        }
                    }
                }
            }
        }
    }
}