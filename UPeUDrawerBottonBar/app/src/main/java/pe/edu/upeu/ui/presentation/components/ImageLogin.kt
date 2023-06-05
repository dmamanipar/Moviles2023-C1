package pe.edu.upeu.ui.presentation.components

<<<<<<< HEAD
<<<<<<< HEAD
import android.annotation.SuppressLint
=======
>>>>>>> DevDocent
=======
>>>>>>> Dev07-1
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import pe.edu.upeu.R

<<<<<<< HEAD
<<<<<<< HEAD

    @Composable
    fun ImageLogin(modifier: Modifier = Modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_login_image),
            contentDescription = "Image Login",
            modifier = modifier .fillMaxWidth() .size(300.dp),
            alignment = Alignment.Center ) }
=======
=======
>>>>>>> Dev07-1
@Composable
fun ImageLogin(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_login_image),
        contentDescription = "Image Login",
        modifier = modifier
            .fillMaxWidth()
            .size(300.dp),
        alignment = Alignment.Center
    )
<<<<<<< HEAD
}
>>>>>>> DevDocent
=======
}
>>>>>>> Dev07-1
