package dev.babananick.pap.ui.components.testmodules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import dev.babananick.pap.core.model.modules.LectureModule
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.R
import dev.babananick.pap.ui.components.buttons.SubmoduleButton
import dev.babananick.pap.ui.components.buttons.HeadModuleButton

@Composable
fun Module(
    modifier: Modifier = Modifier,
    module: TestModule,
    onClick: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        HeadModuleButton(
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
                SubmoduleButton(
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

@Composable
fun Module(
    modifier: Modifier = Modifier,
    module: LectureModule,
    onClick: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        HeadModuleButton(
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
        module.lectures!!.forEach { lecture ->
            AnimatedVisibility(visible = expanded) {
                SubmoduleButton(
                    modifier = Modifier
                        .padding(
                            vertical = dimensionResource(R.dimen.test_in_module_vertical_padding)
                        ),
                    module = lecture,
                    onClick = onClick
                )
            }
        }
    }
}