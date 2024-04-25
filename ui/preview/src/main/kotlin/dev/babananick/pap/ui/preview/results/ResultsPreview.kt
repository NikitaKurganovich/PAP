package dev.babananick.pap.ui.preview.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.babananick.pap.results.ResultsPrimary
import dev.babananick.pap.results.ResultsSecondary

@Preview
@Composable
fun ResultsPreview(){
    Column {
        ResultsPrimary(modifier = Modifier.padding(5.dp)){
            Text(text = "Test")
        }
        ResultsSecondary {
            Text(text = "Test")
        }
    }

}