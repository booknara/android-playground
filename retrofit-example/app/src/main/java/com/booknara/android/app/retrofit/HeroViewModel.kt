package com.booknara.android.app.retrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.booknara.android.app.retrofit.model.Hero
import com.booknara.android.app.retrofit.network.BaseResponse
import com.booknara.android.app.retrofit.network.HeroApi
import com.booknara.android.app.retrofit.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val heroApi: HeroApi, 
                                        application: Application): AndroidViewModel(application) {
    private val _heroList: MutableLiveData<BaseResponse<List<Hero>>> = MutableLiveData()
    val heroList: LiveData<BaseResponse<List<Hero>>> = _heroList

    init {
        getHeroes()
    }
    
    // TODO Implement HeroRepository
    private fun getHeroes() {
        _heroList.value = BaseResponse.Loading()
        viewModelScope.launch {
            val response = heroApi.getHeroes()
            if (response.code() == 200) {
                val heroList = response.body()
                heroList?.let { list ->
                    _heroList.value = BaseResponse.Success(list)
                }
            } else {
                _heroList.value = BaseResponse.Error(response.message())
            }
        }
    }    
}
