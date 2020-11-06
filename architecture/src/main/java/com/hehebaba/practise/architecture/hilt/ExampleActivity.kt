package com.hehebaba.practise.architecture.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hehebaba.practise.architecture.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_example.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {

    @Inject
    lateinit var analyticsAdapter: AnalyticsAdapter

    @Inject
    lateinit var exampleAdapter: ExampleAdapter

    @Inject
    lateinit var analyticsModule: AnalyticsModule

    @Inject
    @AuthInterceptorOkHttpClient
    lateinit var okHttpClient: OkHttpClient

    @Inject
    lateinit var githubService: GithubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        button.setOnClickListener {
            githubService.listRepos("octocat").enqueue(object : Callback<List<Repo>> {
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                    button.text = t.message
                }

                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    button.text = response.body()?.get(0)?.full_name
                }
            })
        }
    }
}