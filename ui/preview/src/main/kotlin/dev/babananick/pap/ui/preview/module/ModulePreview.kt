package dev.babananick.pap.ui.preview.module

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.babananick.pap.core.model.ModuleResources
import dev.babananick.pap.core.model.modules.TestSubmodule
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.modules.Module

@Preview
@Composable
fun ModulePreview() {
    val module = TestModule(
        test_module = "Module",
        resources = ModuleResources(
        ),
        tests = listOf(
            TestSubmodule(
                name = "Submodule",
                question_quantity = 40L
            ),
            TestSubmodule(
                name = "Методика диагностики социально-психологической адаптации К. Роджерса и Р. Даймонда",
                question_quantity = 40L
            )
        )
    )
    Module(module = module) {

    }
}