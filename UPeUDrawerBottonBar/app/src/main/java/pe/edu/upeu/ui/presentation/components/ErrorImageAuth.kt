package pe.edu.upeu.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.edu.upeu.R

@Composable
fun ErrorImageAuth(
    modifier: Modifier = Modifier,
    isImageValidate: Boolean
) {
    if (isImageValidate) {
<<<<<<< HEAD
<<<<<<< HEAD
        Box( modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
=======
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
>>>>>>> DevDocent
=======
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
>>>>>>> DevDocent
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_error_imagen),
                contentDescription = "Image Error",
<<<<<<< HEAD
<<<<<<< HEAD
                modifier = modifier.size(250.dp) )
=======
                modifier = modifier.size(250.dp)
            )
>>>>>>> DevDocent
=======
                modifier = modifier.size(250.dp)
            )
>>>>>>> DevDocent
        }
    }
}