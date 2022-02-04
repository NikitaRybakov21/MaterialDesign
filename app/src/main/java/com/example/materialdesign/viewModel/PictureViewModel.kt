package com.example.materialdesign.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.BuildConfig
import com.example.materialdesign.repository.RetrofitImpl
import com.example.materialdesign.repository.ResponseData
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofit: RetrofitImpl = RetrofitImpl()
) : ViewModel() {

    fun getData() : LiveData<AppState> = liveData

    fun sendServer(){
        liveData.value = AppState.Loading(0)

        val apikey = BuildConfig.NASA_APi_KEY
        if(apikey.isBlank()){
            liveData.value = AppState.Error(Throwable("wrong key"))
        } else {
            retrofit.getRetrofitIml().getPictureOfTheDay(apikey).enqueue(callback)
        }
    }

    private val publishSubject = PublishSubject.create<String>()

    fun getLiveDataReactive() = LiveDataReactiveStreams.fromPublisher(publishSubject.toFlowable(BackpressureStrategy.BUFFER))

    fun sendRx(string: String){
        publishSubject.doOnSubscribe { print("Подписка")}
        publishSubject.onNext(string)
        publishSubject.onNext(string)

        publishSubject.onComplete()
    }

    private val callback = object : Callback<ResponseData> {
        override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {

            if(response.isSuccessful && response.body() != null){
                liveData.value = AppState.Success(response.body()!!)
            } else {
                liveData.value = AppState.Error(Throwable("not found"))
            }
        }

        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
            TODO("Not yet implemented")
        }
    }

    fun getPODFromServer(date : String){
        retrofit.getRetrofitIml().getPictureOfTheYesterday(date,BuildConfig.NASA_APi_KEY).enqueue(callback)
    }

}