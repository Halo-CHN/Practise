package io.github.halochn.coroutines.codelab

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

suspend fun main() {
    val time = measureTimeMillis {
        val one = doSomethingUsefulOne()
        val two = doSomethingUsefulTwo()
        println("The answer is ${one + two}")
    }
    println("Completed in $time ms")

    val time2 = measureTimeMillis {
        coroutineScope {
            val deferreds = listOf(
                async(Dispatchers.Default) { doSomethingUsefulOne() },
                async(Dispatchers.Default) { doSomethingUsefulTwo() }
            )

            println("The answer is ${deferreds.awaitAll().stream().mapToInt { it }.sum()}")
        }
    }
    println("Completed in $time2 ms")
}

class NewsRemoteDataSource(
    private val newsApi: NewsApi,
    private val refreshIntervalMs: Long = 5000
) {
    val latestNews: Flow<List<String>> = flow {
        while (true) {
            val latestNews = newsApi.fetchLatestNews()
            emit(latestNews) // Emits the result of the request to the flow
            delay(refreshIntervalMs) // Suspends the coroutine for some time
        }
    }
}

// Interface that provides a way to make network requests with suspend functions
interface NewsApi {
    suspend fun fetchLatestNews(): List<String>
}

class NewsRepository(
    private val newsRemoteDataSource: NewsRemoteDataSource,
) {
    /**
     * Returns the favorite latest news applying transformations on the flow.
     * These operations are lazy and don't trigger the flow. They just transform
     * the current value emitted by the flow at that point in time.
     */
    val favoriteLatestNews: Flow<List<String>> =
        newsRemoteDataSource.latestNews
            // Intermediate operation to filter the list of favorite topics
//            .map { news -> news.filter { news.contains("7") } }
            // Intermediate operation to save the latest news in the cache
//            .onEach { news -> news.contains("7") }
            .filter { list -> list.stream().anyMatch { it.contains("7") } }
}

class LatestNewsViewModel(
    coroutineScope: CoroutineScope,
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        coroutineScope.launch {
            // Trigger the flow and consume its elements using collect
            newsRepository.favoriteLatestNews.collect { strs ->
                strs.stream().forEach { str -> println(str) }
            }
        }
    }
}