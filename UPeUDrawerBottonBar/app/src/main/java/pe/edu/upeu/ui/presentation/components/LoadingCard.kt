package pe.edu.upeu.ui.presentation.components

<<<<<<< HEAD
<<<<<<< HEAD

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier



import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer


@Composable
fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))
=======
=======
>>>>>>> DevDocent
import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun Spacer(size: Int = 8) = Spacer(
    modifier = Modifier.size(size.dp)
)

<<<<<<< HEAD
>>>>>>> DevDocent
=======
>>>>>>> DevDocent
@Composable
fun ImageLoading() {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray)
        )
    }
}
@Composable
fun LoadingCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
<<<<<<< HEAD
<<<<<<< HEAD
        elevation = 1.dp,
=======
        //elevation = 1.dp,
>>>>>>> DevDocent
=======
        //elevation = 1.dp,
>>>>>>> DevDocent
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ImageLoading()
            Spacer()
            Column {
                Spacer()
                Box(modifier = Modifier.shimmer()) {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}