package dev.babananick.pap.feature.tests.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import compose.icons.TablerIcons
import compose.icons.tablericons.ArrowLeft
import compose.icons.tablericons.InfoCircle
import dev.babananick.pap.core.common.BaseScreenStateValues
import dev.babananick.pap.core.model.tests.Test
import dev.babananick.pap.feature.tests.test.components.InnerNavigation
import dev.babananick.pap.ui.components.buttons.NavigateBorder
import dev.babananick.pap.ui.components.buttons.NavigateFilled
import dev.babananick.pap.ui.components.results.ResultsPrimary
import dev.babananick.pap.ui.components.results.ResultsSecondary
import dev.babananick.pap.ui.components.snackbar.TopSnackbar
import dev.babananick.pap.ui.theme.R as theme

data class TestScreenSpace(
    val testId: String,
) : Screen {

    private lateinit var testVM: TestScreenViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        testVM = hiltViewModel<TestScreenViewModel,
                TestScreenViewModel.TestScreenViewModelFactory> { factory ->
            factory.create(
                testId = testId
            )
        }
        val topNavigator = LocalNavigator.currentOrThrow
        var showSnackbar by remember { mutableStateOf(false) }
        val state by testVM.state.collectAsState()
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is TestState.ShowTest -> {
                    val data = (state as TestState.ShowTest).data
                    Navigator(
                        QuestionScreen(data, data.questions!!.first()),
                        onBackPressed = {
                            true.also {
                                testVM.popScreen()
                                testVM.fetcher(testVM.peekScreen()){

                                }
                            }
                        }
                    ) {
                        val navigator = LocalNavigator.currentOrThrow
                        val isPreviousEnabled by testVM.isNotInBegging.collectAsState()
                        val isNextEnabled by testVM.isNotInEnd.collectAsState()
                        val previous by testVM.previousQuestionPosition.collectAsState()
                        val next by testVM.nextQuestionPosition.collectAsState()
                        val currentPosition by testVM.currentQuestionPosition.collectAsState()
                        var showDialog by remember { mutableStateOf(true) }
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    modifier = Modifier,
                                    navigationIcon = {
                                        IconButton(onClick = remember { { topNavigator.pop() } }) {
                                            Icon(
                                                imageVector = TablerIcons.ArrowLeft,
                                                contentDescription = null
                                            )
                                        }
                                    },
                                    title = {
                                        Text(
                                            modifier = Modifier.fillMaxWidth(),
                                            text = data.name!!,
                                            style = MaterialTheme.typography.titleLarge,
                                            textAlign = TextAlign.Left
                                        )
                                    },
                                    colors = topAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.surface,
                                        navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                                        titleContentColor = MaterialTheme.colorScheme.onSurface,
                                        actionIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                    ),
                                    actions = {
                                        IconButton(onClick = { showDialog = true }) {
                                            Icon(
                                                painter = rememberVectorPainter(image = TablerIcons.InfoCircle),
                                                contentDescription = null
                                            )
                                        }
                                    }
                                )
                            },
                            bottomBar = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = dimensionResource(theme.dimen.tab_horizontal_padding))
                                        .background(
                                            color = MaterialTheme.colorScheme.surfaceContainer,
                                            shape = MaterialTheme.shapes.large
                                        )
                                        .clip(MaterialTheme.shapes.large),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    LeftButton(
                                        Modifier
                                            .padding(start = dimensionResource(R.dimen.horizontal_button_padding))
                                            .padding(vertical = dimensionResource(R.dimen.vertical_button_padding)),
                                        isPreviousEnabled, navigator, data, previous
                                    )
                                    RightButton(
                                        Modifier
                                            .padding(end = dimensionResource(R.dimen.horizontal_button_padding))
                                            .padding(vertical = dimensionResource(R.dimen.vertical_button_padding)),
                                        isNextEnabled, navigator, data, next
                                    ) { isShow ->
                                        showSnackbar = isShow
                                    }
                                }
                            },
                            snackbarHost = {
                                TopSnackbar(
                                    message = stringResource(R.string.snakbar_message_missed_question),
                                    show = showSnackbar,
                                    onDismiss = {
                                        showSnackbar = false
                                    }
                                )
                            },
                            containerColor = MaterialTheme.colorScheme.surface
                        ) { padding ->
                            Column(
                                modifier = Modifier
                                    .padding(padding),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (showDialog) {
                                    AlertDialog(
                                        onDismissRequest = { showDialog = false },
                                        confirmButton = {
                                            TextButton(onClick = { showDialog = false }) {
                                                Text(
                                                    text = stringResource(R.string.button_ok_text),
                                                    style = MaterialTheme.typography.labelLarge
                                                )
                                            }
                                        },
                                        title = {
                                            Text(
                                                text = stringResource(R.string.test_description_header),
                                                style = MaterialTheme.typography.headlineSmall
                                            )
                                        },
                                        text = {
                                            Text(
                                                text = data.description!!,
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    )
                                }
                                InnerNavigation(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    currentQuestion = currentPosition,
                                    test = data,
                                    navigator = navigator,
                                ) { questionPos ->
                                    testVM.fetcher(questionPos) {
                                        testVM.pushScreen(questionPos)
                                    }
                                }
                                CurrentScreen()
                            }
                        }
                    }
                }

                is TestState.ShowResults -> {
                    val results = (state as TestState.ShowResults).analyzer
                    val interpretation = results.prepareInterpretation()
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val indices = interpretation.indices.drop(0).dropLast(1)
                        interpretation.forEachIndexed { index, preparedInterpretation ->
                            if (index % 2 == 0) {
                                ResultsPrimary {
                                    Column {
                                        Text(
                                            text = preparedInterpretation.message,
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(dimensionResource(dev.babananick.pap.ui.theme.R.dimen.screen_content_horizontal_padding))
                                                .verticalScroll(rememberScrollState()),
                                            text = preparedInterpretation.result,
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            } else {
                                ResultsSecondary {
                                    Column {
                                        Text(
                                            text = preparedInterpretation.message,
                                            color = MaterialTheme.colorScheme.onTertiary,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                        Text(
                                            modifier = Modifier
                                                .padding(dimensionResource(dev.babananick.pap.ui.theme.R.dimen.screen_content_horizontal_padding))
                                                .verticalScroll(rememberScrollState()),
                                            text = preparedInterpretation.result,
                                            color = MaterialTheme.colorScheme.onTertiary,
                                            textAlign = TextAlign.Center,
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                            if (index in indices) {
                                Box(
                                    Modifier
                                        .size(5.dp)
                                        .background(
                                            MaterialTheme.colorScheme.primary,
                                            MaterialTheme.shapes.extraLarge
                                        )
                                )
                            }
                        }
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 20.dp,
                                    vertical = 15.dp
                                ),
                            onClick = {
                                topNavigator.pop()
                            },
                        ){
                            Text(
                                text = stringResource(R.string.button_to_main_text),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    }
                }

                is TestState.Base -> {
                    BaseScreenStateValues((state as TestState.Base).states)
                }
            }
        }
    }

    @Composable
    private fun RightButton(
        modifier: Modifier = Modifier,
        isNextEnabled: Boolean,
        navigator: Navigator,
        data: Test,
        next: Int,
        showSnackbar: (Boolean) -> Unit,
    ) {
        if (isNextEnabled) {
            NavigateFilled(
                modifier = modifier,
                onClick = {
                    navigator.push(
                        QuestionScreen(
                            test = data,
                            question = data.questions!![next]
                        )
                    )
                    testVM.fetcher(next) {
                        testVM.pushScreen(next)
                    }
                },
                text = stringResource(R.string.button_to_next_text)
            )
        } else {
            NavigateFilled(
                modifier = modifier,
                onClick = {
                    if (!testVM.proceedTest(data)) {
                        showSnackbar(true)
                        navigator.push(
                            QuestionScreen(
                                data,
                                testVM.navigateToFirstSkipped(data)
                            )
                        )
                    }
                },
                text = stringResource(R.string.button_finish_text)
            )
        }
    }

    @Composable
    private fun RowScope.LeftButton(
        modifier: Modifier = Modifier,
        isPreviousEnabled: Boolean,
        navigator: Navigator,
        data: Test,
        previous: Int,
    ) {
        if (isPreviousEnabled) {
            NavigateBorder(
                modifier = modifier,
                onClick = {
                    navigator.push(
                        QuestionScreen(
                            test = data,
                            question = data.questions!![previous]
                        )
                    )
                    testVM.fetcher(previous) {
                        testVM.pushScreen(previous)
                    }
                },
                text = stringResource(R.string.button_to_previous_text)
            )
        } else {
            Spacer(Modifier.Companion.weight(1f, true))
        }
    }

}