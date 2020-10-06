package com.marketyocase.marketyocase.repositories.user

import com.marketyocase.marketyocase.IRequestListener
import com.marketyocase.marketyocase.model.retrofit.MarketyoApisService
import com.marketyocase.marketyocase.model.user.Response4Users
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class UserListDataSourceImpl(private val compositeDisposable: CompositeDisposable) {

    private val marketyoApiService = MarketyoApisService()

    //==============================================================================================
    /**
     * Request With Retrofit UserList
     */
    //==============================================================================================
    fun getUserList(
        iRequestListener: IRequestListener<MutableList<Response4Users>>
    ) {

        val request: Single<Response<MutableList<Response4Users>>> =
            marketyoApiService.getUserList()

        compositeDisposable.add(
            request.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->

                    when (response.isSuccessful) {
                        true -> {
                            iRequestListener.onSuccess(response)
                        }
                        else -> {
                            iRequestListener.onUnSuccess(response)
                        }
                    }
                }, { throwable ->
                    iRequestListener.onFail(throwable)
                })
        )
    }
}