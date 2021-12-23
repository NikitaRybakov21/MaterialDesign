package com.example.materialdesign.viewModel

import com.example.materialdesign.repository.ResponseData

sealed class AppState {
    data class Success(val responseData: ResponseData): AppState()
    data class Loading(val progress: Int?): AppState()
    data class Error(val error: Throwable): AppState()
}