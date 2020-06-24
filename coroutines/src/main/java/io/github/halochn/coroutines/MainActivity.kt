package io.github.halochn.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        doRetrofit()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val gitHubService = retrofit.create(GitHubService::class.java)

    private fun doRetrofit() {
        GlobalScope.launch(Dispatchers.Main) {
            val one = async { gitHubService.listRepos("octocat") }
            val two = async { gitHubService.listRepos("octocat") }
            val same = one.await()[0].name == two.await()[0].name
            textView.text = "$same"
        }
    }

    private fun invokeStepByStep() {
        GlobalScope.launch(Dispatchers.Main) {
            ioCode1()
            uiCode1()
            ioCode2()
            uiCode2()
            ioCode3()
            uiCode3()
        }
    }

    private suspend fun ioCode1() {
        withContext(Dispatchers.IO) {
            delay(1000L)
            println("ioCode1")
            println("current thread in ioCode1: ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode2(): Unit {
        withContext(Dispatchers.IO) {
            delay(1500L)
            println("ioCode2")
            println("current thread in ioCode2: ${Thread.currentThread().name}")
        }
    }

    private suspend fun ioCode3(): Unit {
        withContext(Dispatchers.IO) {
            delay(800L)
            println("ioCode3")
            println("current thread in ioCode3: ${Thread.currentThread().name}")
        }
    }

    private fun uiCode1(): Unit {
        println("uiCode1")
        println("current thread in uiCode1: ${Thread.currentThread().name}")
    }

    private fun uiCode2(): Unit {
        println("uiCode2")
        println("current thread in uiCode2: ${Thread.currentThread().name}")
    }

    private fun uiCode3(): Unit {
        println("uiCode3")
        println("current thread in uiCode3: ${Thread.currentThread().name}")
    }
}