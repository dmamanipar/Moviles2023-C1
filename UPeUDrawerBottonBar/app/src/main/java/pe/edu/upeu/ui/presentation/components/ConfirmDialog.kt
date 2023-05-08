package pe.edu.upeu.ui.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDialog(
    message: String,
    onConfirm: () -> Unit,
    onDimins: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(text = "Confirmación")
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDimins) {
                Text(text = "Cancelar")
            }
        }
    )
}
