package dev.babananick.pap.ui.components.testmodules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.buttons.TestInModuleButton
import dev.babananick.pap.ui.components.buttons.TestModuleButton

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