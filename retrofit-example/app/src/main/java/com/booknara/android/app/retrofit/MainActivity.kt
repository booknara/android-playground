package com.booknara.android.app.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var heroHeaderAdapter: HeroHeaderAdapter
    private lateinit var heroAdapter: HeroAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerViewHeroes)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        getHeroes()
    }
    
    private fun getHeroes() {
        val call = RetrofitClient.heroApi.getHeroes()
        
        call.enqueue(object : Callback<List<Hero>> {
            override fun onResponse(call: Call<List<Hero>>, response: Response<List<Hero>>) {
                val heroList = response.body()
                heroList?.let { list ->
                    heroAdapter = HeroAdapter(this@MainActivity)
                    heroHeaderAdapter = HeroHeaderAdapter(listOf(list.size.toString()), this@MainActivity)
                    concatAdapter = ConcatAdapter(heroHeaderAdapter, heroAdapter)
                    recyclerView.adapter = concatAdapter
                    heroAdapter.submitList(list)
                }
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
