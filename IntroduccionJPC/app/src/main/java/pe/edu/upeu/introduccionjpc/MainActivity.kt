package pe.edu.upeu.introduccionjpc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pe.edu.upeu.introduccionjpc.ui.theme.IntroduccionJPCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntroduccionJPCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
Row(
    verticalAlignment = Alignment.Top,
    horizontalArrangement = Arrangement.SpaceEvenly,
    modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
) {
    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription =null)
    Column() {
        Text(text = stringResource(id = R.string.app_nombre_saludo, "$name!"))
        Row(){
            Text(stringResource(id = R.string.app_nombre_persona))
            Image(painter = painterResource(id = R.mipmap.imagenperu), contentDescription =null)
        }
        Text(stringResource(id = R.string.app_tiempo))
        }

}

}

@Preview(showBackground = true)
@Composable
fun probando(){
    Text(text = "Hello Mundo")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IntroduccionJPCTheme {
        Greeting("Android")
    }
}