package com.example.dentifymobile.iam.presentation.di

import android.content.Context
import com.example.dentifymobile.iam.data.di.AuthModule
import com.example.dentifymobile.iam.data.di.ProfileModule
import com.example.dentifymobile.iam.presentation.viewmodel.LoginViewModel
import com.example.dentifymobile.iam.presentation.viewmodel.ProfileViewModel
import com.example.dentifymobile.iam.presentation.viewmodel.RegisterViewModel

object PresentationModule {

    fun getLoginViewModel(): LoginViewModel {
        return LoginViewModel(AuthModule.provideLoginUseCase())
    }

    fun getRegisterViewModel(): RegisterViewModel {
        return RegisterViewModel(AuthModule.provideRegisterUseCase())
    }

    fun getProfileViewModel(context: Context): ProfileViewModel{
        return ProfileViewModel(
            getUserInfoUseCase = ProfileModule.provideGetUserInfoUseCase(context),
            updatePasswordUseCase = ProfileModule.provideUpdatePasswordUseCase(context),
            updateInformationUseCase = ProfileModule.provideUpdateInformationUseCase(context)
        )
    }


}