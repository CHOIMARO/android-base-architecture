package com.company.network.di

import com.company.network.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    // Pixabay API의 기본 URL
    private const val BASE_URL = BuildConfig.PIXABAY_API_URL

    /**
     * Moshi (JSON 파서) 인스턴스를 Hilt에 제공
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .build()
    }

    /**
     * Retrofit이 Moshi를 사용할 수 있도록 Converter.Factory를 Hilt에 제공
     */
    @Provides
    @Singleton
    fun provideConverterFactory(moshi: Moshi): Converter.Factory {
        return MoshiConverterFactory.create(moshi)
    }

    /**
     * API 키를 모든 요청에 자동으로 추가하는 Interceptor 제공
     */
    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url

            val newUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("key", BuildConfig.PIXABAY_API_KEY)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    /**
     * OkHttpClient를 Hilt에 제공합
     * 타임아웃과 로깅 인터셉터 설정
     */
    @Provides
    @Singleton
    fun provideHttpClient(
        authInterceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor) // API 키 Interceptor
            .addInterceptor(getLoggingInterceptor()) // 로깅 Interceptor
            .build()
    }

    /**
     * Retrofit 인스턴스를 Hilt에 제공
     */
    @Provides
    @Singleton
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()

    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}