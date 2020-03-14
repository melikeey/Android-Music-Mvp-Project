package com.melikeey.di.module

import android.webkit.CookieManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.melikeey.BuildConfig
import com.melikeey.networking.MusicApiService
import com.melikeey.networking.interceptor.APIKeyInterceptor
import com.melikeey.networking.interceptor.RawResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideHttpClient(apiKeyInterceptor: APIKeyInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        val timeout: Long = 60

        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(RawResponseInterceptor())

        return okHttpClient.build()
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.deezer.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun provideMusicApiService(retrofit: Retrofit): MusicApiService {
        return retrofit.create(MusicApiService::class.java)
    }

    @Provides
    fun provideCookieManager() : CookieManager {
        return CookieManager.getInstance()
    }
}