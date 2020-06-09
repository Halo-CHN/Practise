package io.github.halochn.practise.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        initData()
        initDataRx()
    }

    private fun initDataRx() {
        val listReposRx = service.listReposRx("octocat")
        listReposRx.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Repo>> {
                override fun onSuccess(t: List<Repo>?) {
                    tv_msg.text = t?.get(0)?.full_name
                }

                override fun onSubscribe(d: Disposable?) {
                }

                override fun onError(e: Throwable?) {
                    tv_msg.text = e?.message
                }
            })
    }

    private fun initData() {
        val listRepos = service.listRepos("octocat")
        listRepos.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                tv_msg.text = t.message
            }

            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                tv_msg.text = response.body()?.get(0)?.full_name
            }
        })
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    private val service: GitHubService = retrofit.create(GitHubService::class.java)
}
