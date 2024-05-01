package dev.babananick.pap.feature.tests.testchoose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.babananick.pap.core.common.BaseScreenStateValues
import dev.babananick.pap.feature.tests.test.TestScreenSpace
import dev.babananick.pap.ui.components.testmodules.Module
import dev.babananick.pap.ui.theme.R

class TestChooseScreen: Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val chooseViewModel: TestChooseViewModel = hiltViewModel()
        val screenState by chooseViewModel.state.collectAsState()
        when(screenState){
            is TestChooseState.ShowingTestsChoose ->{
                val data = (screenState as TestChooseState.ShowingTestsChoose).tests
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(data) { testModule ->
                        Module(
                            modifier = Modifier
                                .padding(
                                    horizontal = dimensionResource(R.dimen.screen_content_horizontal_padding),
                                ),
                            module = testModule,
                            onClick = { id->
                                navigator.push(TestScreenSpace(id))
                            },
                        )
                    }
                }
            }
            is TestChooseState.Base -> {
                BaseScreenStateValues(
                    state = (screenState as TestChooseState.Base).state
                )
            }
        }
    }
}