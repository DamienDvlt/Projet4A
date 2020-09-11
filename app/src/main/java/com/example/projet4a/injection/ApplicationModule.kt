package com.example.projet4a.injection

import androidx.lifecycle.MutableLiveData
import com.example.projet4a.MainViewModel
import org.koin.dsl.module

val presentationModule = module {

    factory { MainViewModel() }

}