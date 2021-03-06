package com.aster.app.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aster.app.weather.data.local.db.DatabaseService
import com.aster.app.weather.data.model.ListItem
import com.aster.app.weather.data.remote.NetworkService
import com.aster.app.weather.data.repository.WeatherForecastRepository
import com.aster.app.weather.util.createSampleForecastResponse
import io.reactivex.Single
import org.junit.Assert
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class WeatherForecastReposistoryTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var networkService: NetworkService

    @Mock
    lateinit var databaseService: DatabaseService

    @Mock
    lateinit var weatherForecastRepository: WeatherForecastRepository

    @Before
    fun setUp() {
        weatherForecastRepository = WeatherForecastRepository(networkService, databaseService)
    }


    @Test
    fun fetchWeatherForecastFromNetwrok_Success() {
        var list = createSampleForecastResponse().list
        Mockito.`when`(networkService.doWeatherForecastCall("Singapore")).thenReturn(
            Single.just(
                createSampleForecastResponse()
            )
        )

        // Trigger
        var testObserver: Single<List<ListItem>> =
            weatherForecastRepository.fetchWeatherForecast("Singapore")

        testObserver.subscribe(
            {
                Assert.assertArrayEquals(list.toTypedArray(), it.toTypedArray())
            },
            {
                fail()
            }
        )

    }


    @Test(expected = Exception::class)
    fun fetchWeatherForecastFromNetwrok_Error() {
        Mockito.doThrow(Exception("Bad Request"))
            .`when`(networkService.doWeatherForecastCall("Singapore"))

        // Trigger
        var testObserver: Single<List<ListItem>> =
            weatherForecastRepository.fetchWeatherForecast("Singapore")

        testObserver.subscribe(
            {

            },
            {
                Assert.assertEquals("Bad Request", it.message)
            }
        )

    }


}
