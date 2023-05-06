package com.booknara.android.app.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.booknara.android.app.retrofit.databinding.FragmentMainBinding
import com.booknara.android.app.retrofit.network.BaseResponse

class MainFragment: Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var heroHeaderAdapter: HeroHeaderAdapter
    private lateinit var heroAdapter: HeroAdapter
    private lateinit var concatAdapter: ConcatAdapter
    
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    
    private val viewModel by viewModels<HeroViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        progressBar = binding.progressBar
        recyclerView = binding.recyclerViewHeroes
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.heroList.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showProgressBar()
                }
                is BaseResponse.Success -> {
                    stopProgressBar()
                    heroHeaderAdapter = HeroHeaderAdapter(listOf(it.data?.size.toString()), requireContext())
                    heroAdapter = HeroAdapter(requireContext())
                    concatAdapter = ConcatAdapter(heroHeaderAdapter, heroAdapter)
                    recyclerView.adapter = concatAdapter
                    heroAdapter.submitList(it.data)
                }
                is BaseResponse.Error -> {
                    stopProgressBar()
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_LONG).show()
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
