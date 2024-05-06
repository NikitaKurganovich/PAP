package dev.babananick.pap.ui.preview.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.results.ResultsPrimary
import dev.babananick.pap.ui.components.results.ResultsSecondary

@Preview
@Composable
fun ResultsPreview(){
    Column {
        ResultsPrimary(modifier = Modifier.padding(5.dp)){
            Text(
                modifier = Modifier.padding(dimensionResource(dev.babananick.pap.ui.theme.R.dimen.screen_content_horizontal_padding)),
                text = "Test"
            )
        }
        ResultsSecondary(modifier = Modifier.padding(5.dp)) {
            Text(text = "Test")
        }
    }

}

@Preview
@Composable
fun ResultsLongPreview(@PreviewParameter(LoremIpsum::class) text: String){
    Column {
        ResultsPrimary(modifier = Modifier.padding(5.dp)){
            Text(
                modifier = Modifier.padding(dimensionResource(dev.babananick.pap.ui.theme.R.dimen.screen_content_horizontal_padding)),
                text = text
            )
        }
        ResultsSecondary(modifier = Modifier.padding(5.dp)) {
            Text(text = text)
        }
    }

}
