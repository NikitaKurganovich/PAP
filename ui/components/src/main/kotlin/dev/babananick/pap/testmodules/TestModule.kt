package dev.babananick.pap.testmodules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.babananick.pap.buttons.TestInModuleButton
import dev.babananick.pap.buttons.TestModuleButton
import dev.babananick.pap.modules.TestModule

@Composable
fun TestModule(
    modifier: Modifier = Modifier,
    module: TestModule,
    onClick: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TestModuleButton(
            modifier = Modifier,
            module = module,
            expanded = expanded,
            onClick = {
                expanded = !expanded
            }
        )
        module.tests!!.forEach { test ->
            AnimatedVisibility(visible = expanded) {
                TestInModuleButton(
                    modifier = Modifier
                        .padding(vertical = (2.5).dp),
                    module = test,
                    onClick = onClick
                )
            }
        }
    }
}