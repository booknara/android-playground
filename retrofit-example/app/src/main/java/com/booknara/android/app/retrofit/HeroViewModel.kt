package com.booknara.android.app.retrofit

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.booknara.android.app.retrofit.model.Hero
import com.booknara.android.app.retrofit.network.RetrofitClient
import kotlinx.coroutines.launch

class HeroViewModel(application: Application): AndroidViewModel(application) {
    private val _heroList: MutableLiveData<List<Hero>> = MutableLiveData()
    val heroList: LiveData<List<Hero>> = _heroList

    fun getHeroes() {
        viewModelScope.launch {
            val response = RetrofitClient.heroApi.getHeroes()
            if (response.code() == 200) {
                val heroList = response.body()
                heroList?.let { list ->
                    _heroList.value = list
                }                
            } else {
                Toast.makeText(getApplication(), response.message(), Toast.LENGTH_LONG).show()
            }
        }
    }    
}
