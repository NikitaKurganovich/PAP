package dev.babananick.pap.feature.academic.lecturechoose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.babananick.pap.core.common.BaseScreenStateValues
import dev.babananick.pap.ui.components.modules.Module
import dev.babananick.pap.ui.theme.R as theme
class AcademicScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeVM: HomeScreenViewModel = hiltViewModel()
        val screenState by homeVM.state.collectAsState()
        var isDialogOpened by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (screenState) {
                is HomeState.ShowingModules -> {
                    val data = (screenState as HomeState.ShowingModules).data
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            if (isDialogOpened){
                                AlertDialog(
                                    onDismissRequest = { isDialogOpened = false },
                                    title = {
                                        Text(text = stringResource(R.string.dialog_header))
                                    },
                                    confirmButton = {
                                        Button(onClick = { isDialogOpened = false }) {
                                            Text(text = stringResource(R.string.dialog_ok))
                                        }
                                    },
                                    text = {
                                        Text(text = stringResource(R.string.dialog_content))
                                    }
                                )
                            }
                        }
                        items(data) { module ->
                            Module(
                                modifier = Modifier
                                .padding(
                                    horizontal = dimensionResource(theme.dimen.screen_content_horizontal_padding),
                                ),
                                module = module,
                                onClick = { isDialogOpened = true }
                            )
                        }
                    }
                }

                is HomeState.Base -> {
                    BaseScreenStateValues((screenState as HomeState.Base).state)
                }
            }
        }
    }
}

