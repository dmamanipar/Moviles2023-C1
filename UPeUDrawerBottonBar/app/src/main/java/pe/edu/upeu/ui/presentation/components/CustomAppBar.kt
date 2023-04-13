package pe.edu.upeu.ui.presentation.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import pe.edu.upeu.ui.navigation.Destinations
import pe.edu.upeu.ui.theme.ThemeType

@Composable
fun CustomTopAppBar(darkMode: MutableState<Boolean>,
                    themeType: MutableState<ThemeType>?,
                    navController: NavController,
                    scope: CoroutineScope,
                    scaffoldState: ScaffoldState,
                    openDialog: () -> Unit,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    if (currentRoute == null || currentRoute == Destinations.Login.route)
    {
        return
    }
    Box(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
    ) {
        TopAppBar(
            modifier = Modifier
                .background(MaterialTheme.colors.primaryVariant)
                .fillMaxWidth()
                .statusBarsPadding(),
            backgroundColor = MaterialTheme.colors.primary,
            elevation = 0.dp,
            title = {
                Text(
                    text = "AppBar",
                    color = Color.White,
                    modifier = Modifier.clickable {
                        themeType!!.value = ThemeType.RED
                    }
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription
                        = "Menu Icon"
                    )
                }
            },
            actions = {
                AppBarActions(darkMode, themeType!!, openDialog)
            }
        )
    }
}
@Composable
fun AppBarActions(darkMode: MutableState<Boolean>, themeType:
MutableState<ThemeType>, openDialog: () -> Unit) {
    ChangeMode(darkMode)
    ShareAction(openDialog)
    MoreAction(themeType)
}
@Composable
fun ChangeMode(darkMode: MutableState<Boolean>) {
    IconButton(
        onClick = { darkMode.value = !darkMode.value }
    ) {
        Icon(
            imageVector = if (darkMode.value) {
                Icons.Default.DarkMode
            } else {
                Icons.Default.LightMode
            },
            contentDescription = "Dark mode Icon"
        )
    }
}
@Composable
fun ShareAction( openDialog: () -> Unit) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            openDialog()
            Toast.makeText(context, "Share Clicked!",
                Toast.LENGTH_SHORT).show()
        }
    ) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "search_icon",
            tint = Color.White
        )
    }
}
@Composable
fun MoreAction(themeType: MutableState<ThemeType>) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = "search_icon",
            tint = Color.White
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = { expanded = false }
            ) {
                RadioButton(selected = themeType.value ==
                        ThemeType.PURPLE, onClick = {
                    themeType.value = ThemeType.PURPLE
                })
                Text(text = "Purple")
            }
            DropdownMenuItem(
                onClick = { expanded = false }
            ) {
                RadioButton(selected = themeType.value == ThemeType.RED,
                    onClick = {
                        themeType.value = ThemeType.RED
                    })
                Text(text = "Red")
            }
            DropdownMenuItem(
                onClick = { expanded = false }
            ) {
                RadioButton(selected = themeType.value ==
                        ThemeType.YELLOW, onClick = {
                    themeType.value = ThemeType.YELLOW
                })
                Text(text = "Yellow")
            }
            DropdownMenuItem(
                onClick = { expanded = false }
            ) {
                RadioButton(selected = themeType.value ==
                        ThemeType.GREEN, onClick = {
                    themeType.value = ThemeType.GREEN
                })
                Text(text = "Green")
            }
            DropdownMenuItem(
                onClick = { expanded = false }
            ) {
                RadioButton(selected = themeType.value ==
                        ThemeType.DROWN, onClick = {
                    themeType.value = ThemeType.DROWN
                })
                Text(text = "Drown")
            }
        }
    }
}


@Preview
@Composable
fun CustomAppBarPreview() {
    val isDarkMode = remember {mutableStateOf(false)}
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue =
        DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()
    val openDialog = remember { mutableStateOf(false) }
    CustomTopAppBar(isDarkMode, themeType = null,navController,scope,
        scaffoldState, openDialog = { openDialog.value = true },)
}
