package com.company.example

import androidx.paging.PagingData
import app.cash.turbine.test
import com.company.domain.model.ImageItem
import com.company.domain.usecase.ExamplePagingUseCase
import com.company.domain.usecase.ExampleUseCase
import com.company.testing.rules.MainDispatcherRule
import com.company.ui.common.UiStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 1. 의존성 Mocking
    private val exampleUseCase: ExampleUseCase = mockk()
    private val examplePagingUseCase: ExamplePagingUseCase = mockk()
    private lateinit var viewModel: ExampleViewModel

    @Before
    fun setup() {
        // ViewModel 주입
        viewModel = ExampleViewModel(exampleUseCase, examplePagingUseCase)
    }

    @Test
    fun `초기 상태는 Idle이어야 한다`() {
        assertEquals(UiStatus.Idle, viewModel.currentState.uiStatus)
    }

    @Test
    fun `검색어가 비어있을 때 검색하면 토스트 메시지가 발생해야 한다`() = runTest {
        // Given: 검색어 비우기
        viewModel.handleIntent(ExampleIntent.OnSearchQueryChanged(""))

        // SideEffect 감지 시작 (Turbine 사용 추천)
        viewModel.sideEffect.test {
            // When: 검색 시도
            viewModel.handleIntent(ExampleIntent.OnSearch)

            // Then: Toast 발생 확인
            val effect = awaitItem()
            assertTrue(effect is ExampleSideEffect.ShowToast)
            assertEquals("검색어를 입력해주세요.", (effect as ExampleSideEffect.ShowToast).message)
        }
    }

    @Test
    fun `정상 검색 시 Loading을 거쳐 Content 상태가 되어야 한다`() = runTest {
        // Given
        val query = "test"
        val fakePagingData = PagingData.empty<ImageItem>()

        // Mocking: 정상적으로 데이터 리턴
        coEvery { examplePagingUseCase(query, any()) } returns flowOf(fakePagingData)

        // 미리 검색어 세팅 (StateFlow는 초기값이 있으므로 skip(1) 주의)
        viewModel.handleIntent(ExampleIntent.OnSearchQueryChanged(query))

        // When & Then: 상태 변화 흐름 감지
        // currentState를 구독해서 변화를 지켜봅니다.
        viewModel.uiState.test {
            // 1. 현재 상태 (Query가 바뀐 직후의 Idle 상태) 확인
            val firstState = awaitItem()
            assertEquals(UiStatus.Idle, firstState.uiStatus)
            assertEquals(query, firstState.searchQuery)

            // 2. 검색 시작!
            viewModel.handleIntent(ExampleIntent.OnSearch)

            // 3. Loading 상태 감지
            val loadingState = awaitItem()
            assertEquals(UiStatus.Loading, loadingState.uiStatus)

            // 4. Content 상태 감지 (결과 도착)
            val contentState = awaitItem()
            assertEquals(UiStatus.Content, contentState.uiStatus)

            // 더 이상 변화가 없어야 함
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `데이터를 가져오지 못했을 때 Error 상태가 되어야 한다`() = runTest {
        // Given
        val query = "error_test"
        val errorMessage = "네트워크 오류 발생"

        // Mocking: 호출 시 예외 던지기 (RuntimeException)
        coEvery { examplePagingUseCase(query, any()) } throws RuntimeException(errorMessage)

        viewModel.handleIntent(ExampleIntent.OnSearchQueryChanged(query))

        // When & Then 1: 상태(State) 변화 검증
        viewModel.uiState.test {
            // 초기 상태 (Idle)
            assertEquals(UiStatus.Idle, awaitItem().uiStatus)

            // 검색 시작
            viewModel.handleIntent(ExampleIntent.OnSearch)

            // Loading 상태 확인
            assertEquals(UiStatus.Loading, awaitItem().uiStatus)

            // Error 상태 확인
            val errorState = awaitItem()
            // 상태가 Error 타입인지 확인
            assertTrue(errorState.uiStatus is UiStatus.Error)
            // 에러 메시지는 상태에는 저장 안 했거나(구조따라 다름), SideEffect로만 보냈을 수 있음

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `에러 발생 시 토스트 메시지 SideEffect가 발생해야 한다`() = runTest {
        // Given
        val query = "error"
        val errorMessage = "네트워크 에러"
        coEvery { examplePagingUseCase(query, any()) } throws RuntimeException(errorMessage)
        viewModel.handleIntent(ExampleIntent.OnSearchQueryChanged(query))

        // When & Then
        viewModel.sideEffect.test {
            viewModel.handleIntent(ExampleIntent.OnSearch)

            // 토스트 메시지 검증
            val effect = awaitItem()
            assertTrue(effect is ExampleSideEffect.ShowToast)
            assertEquals(errorMessage, (effect as ExampleSideEffect.ShowToast).message)
        }
    }
}