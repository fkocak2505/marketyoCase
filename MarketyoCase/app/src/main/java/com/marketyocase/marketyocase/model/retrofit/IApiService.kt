package com.marketyocase.marketyocase.model.retrofit

import com.marketyocase.marketyocase.model.commentsPost.Response4CommentsPost
import com.marketyocase.marketyocase.model.user.Response4Users
import com.marketyocase.marketyocase.model.userPost.Response4UserPost
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {

    @GET("/users")
    fun getUserList(): Single<Response<MutableList<Response4Users>>>

    @GET("/posts")
    fun getUserPostList(@Query("userId") userId: Int): Single<Response<MutableList<Response4UserPost>>>

    @GET("/comments")
    fun getCommentsPost(@Query("postId") postId: Int): Single<Response<MutableList<Response4CommentsPost>>>


}