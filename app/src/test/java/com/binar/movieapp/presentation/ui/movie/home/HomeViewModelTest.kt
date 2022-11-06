package com.binar.movieapp.presentation.ui.movie.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.binar.movieapp.data.network.model.HomeMovie
import com.binar.movieapp.data.repository.MovieRepository
import com.binar.movieapp.data.repository.UserRepository
import com.binar.movieapp.util.getOrAwaitValue
import com.binar.movieapp.wrapper.Resource
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    //private val dummyMovies = DummyData.generateDummyMovies()
    private lateinit var viewModel: HomeViewModel
    private lateinit var movieRepository: MovieRepository
    private lateinit var userRepository: UserRepository


    @Before
    fun setUp() {

    }

    @Test
    fun getHomeMovieListResult() {
        val expectedResult = mockk<LiveData<Resource<List<HomeMovie>>>>()

        every {
            viewModel.homeMovieListResult
        } returns expectedResult

        val actualResult = viewModel.homeMovieListResult.getOrAwaitValue()
        assertEquals(expectedResult, actualResult)
    }

/*    @Test
    fun `when get movies should not be null and return success `() {
        val expectedResult = MutableLiveData<Resource<List<HomeMovie>>>()
        expectedResult.value = Resource.Success(dummyMovies)

        `when`(viewModel.homeMovieListResult).thenReturn(expectedResult)

        val actualResult = viewModel.homeMovieListResult.getOrAwaitValue()
        Mockito.verify(viewModel).homeMovieListResult
        assertNotNull(actualResult)
        assertTrue(actualResult is Resource.Success)
        // assertEquals(dummyMovies.size, (actualResult as Resource.Success).payload?.size)
    }*/
}