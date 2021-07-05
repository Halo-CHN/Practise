package io.github.halochn.coroutines

import io.github.halochn.coroutines.codelab.LatestNewsViewModel
import io.github.halochn.coroutines.codelab.NewsApi
import io.github.halochn.coroutines.codelab.NewsRemoteDataSource
import io.github.halochn.coroutines.codelab.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun testSomeUI() {
        runBlocking {
            launch(Dispatchers.Main) {  // Will be launched in the mainThreadSurrogate dispatcher
                val newsRemoteDataSource = NewsRemoteDataSource(newsApi = object : NewsApi {
                    override suspend fun fetchLatestNews(): List<String> {
                        return listOf("${System.currentTimeMillis()}")
                    }
                })

                LatestNewsViewModel(this, NewsRepository(newsRemoteDataSource))
            }
        }
    }
}