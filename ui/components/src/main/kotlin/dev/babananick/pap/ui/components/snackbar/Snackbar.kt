package dev.babananick.pap.ui.components.snackbar


import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import dev.babananick.pap.ui.components.R

@Composable
fun TopSnackbar(message: String, show: Boolean, onDismiss: () -> Unit) {

    if (show) {
        Snackbar(
            modifier = Modifier,
            action = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(R.string.snackbar_dismiss_text))
                }
            }
        ) {
            Text(text = message)
        }
    }

}
