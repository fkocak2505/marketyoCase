package com.marketyocase.marketyocase

import retrofit2.Response

interface IRequestListener<T> {

    fun onSuccess(response: Response<T>)
    fun onUnSuccess(response: Response<T>)
    fun onFail(t: Throwable?)

}