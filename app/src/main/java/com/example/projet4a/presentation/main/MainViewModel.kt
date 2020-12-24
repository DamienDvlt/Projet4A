package com.example.projet4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet4a.domain.entity.User
import com.example.projet4a.domain.usecase.CreateUserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase
) :ViewModel() {

    val counter: MutableLiveData<Int> = MutableLiveData()

    init {
        counter.value = 0
    }

    fun onCLickedIncrement(emailUser: String) {
        viewModelScope.launch {
            createUserUseCase.invoke(User(emailUser))
        }
    }
}