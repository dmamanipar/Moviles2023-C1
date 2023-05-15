package pe.edu.upeu.ui.presentation.screens.facultad

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
import pe.edu.upeu.modelo.Facultad

import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.presentation.components.BottomNavigationBar
import pe.edu.upeu.ui.presentation.components.ConfirmDialog
import pe.edu.upeu.ui.presentation.components.LoadingCard

import pe.edu.upeu.utils.TokenUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun FacultadUI (navegarEditarfac: (String) -> Unit, viewModel:
FacultadViewModel = hiltViewModel()
){
    val facu by viewModel.facultades.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    Log.i("VERX", ""+facu!!.size )

    MyApp(onAddClick = {
        //viewModel.addUser()
        navegarEditarfac((0).toString())
    }, onDeleteClick = {
        viewModel.deleteFacultad(it)
    }, facu, isLoading,
        onEditClick = {
            val jsonString = Gson().toJson(it)
            navegarEditarfac(jsonString)
        }
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(
    onAddClick: (() -> Unit)? = null,
    onDeleteClick: ((toDelete: Facultad) -> Unit)? = null,
    facultades: List<Facultad>,
    isLoading: Boolean,
    onEditClick: ((toPersona: Facultad) -> Unit)? = null,
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
                var itemCount = facultades.size
                if (isLoading) itemCount++
                items(count = itemCount) { index ->
                    var auxIndex = index;
                    if (isLoading) {
                        if (auxIndex == 0)
                            return@items LoadingCard()
                        auxIndex--
                    }
                    val facultad = facultades[auxIndex]
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
                                    data = facultad,
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
                                Text("${facultad.nombrefac} - ${facultad.estado} -${facultad.iniciales} ", fontWeight = FontWeight.Bold)

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
                                        onDeleteClick?.invoke(facultad)
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
                                onEditClick?.invoke(facultad)
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