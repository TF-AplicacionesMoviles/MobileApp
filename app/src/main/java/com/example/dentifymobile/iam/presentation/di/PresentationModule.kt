package com.example.dentifymobile.iam.presentation.di

import com.example.dentifymobile.iam.data.di.AuthModule
import com.example.dentifymobile.iam.presentation.viewmodel.LoginViewModel
import com.example.dentifymobile.iam.presentation.viewmodel.RegisterViewModel

object PresentationModule {

    fun getLoginViewModel(): LoginViewModel {
        return LoginViewModel(AuthModule.provideLoginUseCase())
    }

    fun getRegisterViewModel(): RegisterViewModel {
        return RegisterViewModel(AuthModule.provideRegisterUseCase())
    }
}