package com.booknara.android.app.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booknara.android.app.retrofit.databinding.ActivityMainBinding
import com.booknara.android.app.retrofit.model.Hero
import com.booknara.android.app.retrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroHeaderAdapter: HeroHeaderAdapter
    private lateinit var heroAdapter: HeroAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<HeroViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerViewHeroes
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        initObserver()
        viewModel.getHeroes()
    }
    
    private fun initObserver() {
        viewModel.heroList.observe(this) {
            heroHeaderAdapter = HeroHeaderAdapter(listOf(it.size.toString()), this)
            heroAdapter = HeroAdapter(this)
            concatAdapter = ConcatAdapter(heroHeaderAdapter, heroAdapter)
            recyclerView.adapter = concatAdapter
            heroAdapter.submitList(it)
        }
    }
    

}
