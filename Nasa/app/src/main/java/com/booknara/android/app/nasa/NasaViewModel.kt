package com.booknara.android.app.nasa

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.booknara.android.app.nasa.model.Photo
import com.booknara.android.app.nasa.network.RetrofitInstance
import kotlinx.coroutines.launch

class NasaViewModel(application: Application): AndroidViewModel(application) {
    private val _listPhotos = MutableLiveData<List<Photo>>()
    val listPhotos: LiveData<List<Photo>> = _listPhotos

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    
    fun getPhoto() {
        // get photo
        viewModelScope.launch {
            _loading.value = true
            try {
                val result = RetrofitInstance.getNasaApi().getCuriosity(2000)
                if (result.isSuccessful) {
                    _listPhotos.value = result.body()?.photos
                } else {
                    // Toast message
                    Toast.makeText(getApplication(), "fetch failed", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_LONG).show()
            } finally {
                _loading.value = false
            }
        }        
    }
}
