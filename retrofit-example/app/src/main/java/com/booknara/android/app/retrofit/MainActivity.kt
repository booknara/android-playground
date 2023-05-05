package com.booknara.android.app.retrofit

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booknara.android.app.retrofit.databinding.ActivityMainBinding

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
            heroHeaderAdapter = HeroHeaderAdapter(listOf(it.size.toString()), this)
            heroAdapter = HeroAdapter(this)
            concatAdapter = ConcatAdapter(heroHeaderAdapter, heroAdapter)
            recyclerView.adapter = concatAdapter
            heroAdapter.submitList(it)
            // TODO: To dismiss progress bard in case of Error using seal class
            progressBar.visibility = View.GONE
        }
    }
}
