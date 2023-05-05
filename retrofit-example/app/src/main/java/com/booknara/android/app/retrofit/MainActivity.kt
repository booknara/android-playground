package com.booknara.android.app.retrofit

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booknara.android.app.retrofit.databinding.ActivityMainBinding
import com.booknara.android.app.retrofit.network.BaseResponse

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var heroHeaderAdapter: HeroHeaderAdapter
    private lateinit var heroAdapter: HeroAdapter
    private lateinit var concatAdapter: ConcatAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private val viewModel by viewModels<HeroViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerViewHeroes
        progressBar = binding.progressBar
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        initObserver()
        progressBar.visibility = View.VISIBLE
        viewModel.getHeroes()
    }

    private fun initObserver() {
        viewModel.heroList.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showProgressBar()
                }
                is BaseResponse.Success -> {
                    stopProgressBar()
                    heroHeaderAdapter = HeroHeaderAdapter(listOf(it.data?.size.toString()), this)
                    heroAdapter = HeroAdapter(this)
                    concatAdapter = ConcatAdapter(heroHeaderAdapter, heroAdapter)
                    recyclerView.adapter = concatAdapter
                    heroAdapter.submitList(it.data)                    
                }
                is BaseResponse.Error -> {
                    stopProgressBar()
                    Toast.makeText(application, it.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }
    
    private fun stopProgressBar() {
        binding.progressBar.visibility = View.GONE
    }    
}
