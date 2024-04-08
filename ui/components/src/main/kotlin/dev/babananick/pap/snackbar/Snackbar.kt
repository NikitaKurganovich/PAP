package dev.babananick.pap.snackbar


import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TopSnackbar(message: String, show: Boolean, onDismiss: () -> Unit) {

    if (show) {
        Snackbar(
            modifier = Modifier,
            action = {
                TextButton(onClick = onDismiss) {
                    Text(text = "Dismiss")
                }
            }
        ) {
            Text(text = message)
        }
    }

}
