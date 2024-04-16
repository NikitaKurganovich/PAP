package dev.babananick.pap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
import dev.babananick.pap.buttons.NavigateBorder
import dev.babananick.pap.buttons.NavigateFilled
import dev.babananick.pap.components.InnerNavigation
import dev.babananick.pap.results.ResultsPrimary
import dev.babananick.pap.results.ResultsSecondary
import dev.babananick.pap.snackbar.TopSnackbar
import dev.babananick.pap.tests.Test
import dev.babananick.pap.theme.PAPTypo

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
                                testVM.fetcher(testVM.peekScreen()) {}
                            }
                        }
                    ) {
                        val navigator = LocalNavigator.currentOrThrow
                        val headerShape = remember {
                            RoundedCornerShape(
                                bottomStart = 20.dp,
                                bottomEnd = 20.dp
                            )
                        }
                        val isPreviousEnabled by testVM.isNotInBegging.collectAsState()
                        val isNextEnabled by testVM.isNotInEnd.collectAsState()
                        val previous by testVM.previousQuestionPosition.collectAsState()
                        val next by testVM.nextQuestionPosition.collectAsState()
                        val currentPosition by testVM.currentQuestionPosition.collectAsState()
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    modifier = Modifier
                                        .shadow(elevation = 4.dp, shape = headerShape)
                                        .clip(headerShape),
                                    navigationIcon = {
                                        IconButton(onClick = remember {
                                            {
                                                topNavigator.pop()
                                            }
                                        }) {
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
                                            style = PAPTypo.headerInTestTextStyle,
                                            textAlign = TextAlign.Center
                                        )
                                    },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = Color.White
                                    )
                                )
                            },
                            bottomBar = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = 20.dp,
                                            vertical = 15.dp
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    LeftButton(isPreviousEnabled, navigator, data, previous)
                                    RightButton(isNextEnabled, navigator, data, next) { isShow ->
                                        showSnackbar = isShow
                                    }
                                }
                            }
                        ) { padding ->
                            Column(
                                modifier = Modifier
                                    .padding(padding),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TopSnackbar(
                                    message = "Вы пропустили вопросы!",
                                    show = showSnackbar,
                                    onDismiss = {
                                        showSnackbar = false
                                    }
                                )
                                InnerNavigation(
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
                        val indices = interpretation.indices.drop(1).dropLast(1)
                        interpretation.forEachIndexed { index, preparedInterpretation ->
                            if (index % 2 == 0) {
                                ResultsPrimary {
                                    Column {
                                        Text(
                                            text = preparedInterpretation.message,
                                            style = PAPTypo.resultsTextStyle,
                                            color = Color(0xFF434743),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = preparedInterpretation.result,
                                            style = PAPTypo.resultsTextStyle,
                                            color = Color(0xFF434743),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            } else {
                                ResultsSecondary {
                                    Column {
                                        Text(
                                            text = preparedInterpretation.message,
                                            style = PAPTypo.resultsTextStyle,
                                            color = Color(0xFFEEFDEF),
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = preparedInterpretation.result,
                                            style = PAPTypo.resultsTextStyle,
                                            color = Color(0xFFEEFDEF),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                            if (index in indices) {
                                Box(
                                    Modifier
                                        .size(5.dp)
                                        .background(Color.Red)
                                )
                            }
                        }
                        NavigateFilled(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 20.dp,
                                    vertical = 15.dp
                                ),
                            onClick = {
                                topNavigator.pop()
                            },
                            text = "На главную"
                        )
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
        isNextEnabled: Boolean,
        navigator: Navigator,
        data: Test,
        next: Int,
        showSnackbar: (Boolean) -> Unit,
    ) {
        if (isNextEnabled) {
            NavigateFilled(
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
                text = "Следующий"
            )
        } else {
            NavigateFilled(
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
                text = "Закончить"
            )
        }
    }

    @Composable
    private fun RowScope.LeftButton(
        isPreviousEnabled: Boolean,
        navigator: Navigator,
        data: Test,
        previous: Int,
    ) {
        if (isPreviousEnabled) {
            NavigateBorder(
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
                text = "Предыдущий"
            )
        } else {
            Spacer(Modifier.Companion.weight(1f, true))
        }
    }

}