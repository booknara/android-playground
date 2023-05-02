package com.booknara.android.app.retrofit

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: HeroAdapter
    private lateinit var listView: ListView
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listViewHeroes)
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
                    adapter = HeroAdapter(this@MainActivity)
                    recyclerView.adapter = adapter
                    adapter.submitList(list)
                                        
                }
            }

            override fun onFailure(call: Call<List<Hero>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}
