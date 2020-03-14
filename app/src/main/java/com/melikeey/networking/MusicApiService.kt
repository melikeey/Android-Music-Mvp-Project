package com.melikeey.networking

import com.melikeey.model.GetAlbumResponse
import com.melikeey.model.GetSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicApiService {

    @GET("/search")
    fun getSearch(@Query("q") q: String? = null): Observable<GetSearchResponse>

    @GET("/artist/{no}/albums")
    fun getAlbums(@Path("no") no: String): Observable<GetAlbumResponse>

    @GET("/album/{no}/tracks")
    fun getTracks(@Path("no") no: String): Observable<GetSearchResponse>

}