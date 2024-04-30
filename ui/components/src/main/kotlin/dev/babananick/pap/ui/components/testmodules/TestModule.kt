package dev.babananick.pap.ui.components.testmodules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.R
import dev.babananick.pap.ui.components.buttons.TestInModuleButton
import dev.babananick.pap.ui.components.buttons.TestModuleButton

@Composable
fun TestModule(
    modifier: Modifier = Modifier,
    module: TestModule,
    onClick: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        TestModuleButton(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.test_in_module_vertical_padding)
                ),
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
                        .padding(
                            vertical = dimensionResource(R.dimen.test_in_module_vertical_padding)
                        ),
                    module = test,
                    onClick = onClick
                )
            }
        }
    }
}