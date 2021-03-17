package com.daniyalxdubizzle.androidtakehomeproject.di

import android.app.Application
import android.content.Context
import com.daniyalxdubizzle.androidtakehomeproject.data.retrofit.RetrofitInterface
import com.daniyalxdubizzle.androidtakehomeproject.utilities.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        val okHttpClient = OkHttpClient().newBuilder().addNetworkInterceptor(interceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun baseApiInterface(retrofit: Retrofit.Builder): RetrofitInterface {
        return retrofit.build().create(RetrofitInterface::class.java)
    }

    @Singleton
    @Provides
    fun getBaseContext(application: Application): Context {
        return application.baseContext
    }

}