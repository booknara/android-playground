package com.booknara.android.app.login.ui.login

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.*
import com.booknara.android.app.login.repository.UserRepository
import com.booknara.android.app.login.network.BaseResponse
import com.booknara.android.app.login.network.LoginRequest
import com.booknara.android.app.login.network.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(application: Application)
    : AndroidViewModel(application) {

    private val userRepo = UserRepository()
    private val _loginResult: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()
    val loginResult: LiveData<BaseResponse<LoginResponse>> = _loginResult
    
    fun loginUser(email: String, pwd: String) {
        _loginResult.value = BaseResponse.Loading()
        viewModelScope.launch { 
            try {
                val loginRequest = LoginRequest(email, pwd)
                val response = userRepo.loginUser(loginRequest)
                if (response?.code() == 200) {
                    _loginResult.value = BaseResponse.Success(response.body())
                } else {
                    _loginResult.value = BaseResponse.Error(response?.message())
                }
            } catch (ex: Exception) {
                _loginResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
