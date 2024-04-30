package dev.babananick.pap.ui.preview.module

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.babananick.pap.core.model.TestResources
import dev.babananick.pap.core.model.modules.TestInModule
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.testmodules.TestModule

@Preview
@Composable
fun ModulePreview() {
    val module = TestModule(
        test_module = "Module",
        resources = TestResources(
        ),
        tests = listOf(
            TestInModule(
                name = "Submodule",
                question_quantity = 40L
            ),
            TestInModule(
                name = "Методика диагностики социально-психологической адаптации К. Роджерса и Р. Даймонда",
                question_quantity = 40L
            )
        )
    )
    TestModule(module = module) {

    }
}