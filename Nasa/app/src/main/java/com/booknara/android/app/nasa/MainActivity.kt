package com.booknara.android.app.nasa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booknara.android.app.nasa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: NasaViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: NasaRecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        recyclerView = binding.recyclerView
        progressBar = binding.progressBar
        adapter = NasaRecyclerView()
        recyclerView.adapter = adapter
        // recyclerView.layoutManager = LinearLayoutManager(this)
        
        initObserver()
        viewModel.getPhoto()
    }
    
    private fun initObserver() {
        viewModel.listPhotos.observe(this) { it ->
            // RecyclerView
            Log.d(TAG, "# of Photos: {${it.size}}")
            val filtered = it.filter { it.camera.name == "NAVCAM" }
            Log.d(TAG, "# of filtered Photos: {${filtered.size}}")
            adapter.submitList(filtered)
        }
        
        viewModel.loading.observe(this) {
            // loading handling
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        } 
    }
    
    companion object {
        const val TAG = "MainActivity"
    }
}
