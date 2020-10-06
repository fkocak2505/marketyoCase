package com.marketyocase.marketyocase.repositories.userPost

import com.marketyocase.marketyocase.IRequestListener
import com.marketyocase.marketyocase.model.retrofit.MarketyoApisService
import com.marketyocase.marketyocase.model.userPost.Response4UserPost
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class UserPostListDataSourceImpl(private val compositeDisposable: CompositeDisposable) {

    private val marketyoApiService = MarketyoApisService()

    //==============================================================================================
    /**
     * Request With Retrofit User Post with userId parameters
     */
    //==============================================================================================
    fun getUserPostList(
        userId: Int,
        iRequestListener: IRequestListener<MutableList<Response4UserPost>>
    ) {

        val request: Single<Response<MutableList<Response4UserPost>>> =
            marketyoApiService.getUserPostList(userId)

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