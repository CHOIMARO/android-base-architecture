package com.company.data.di

import com.company.data.api.PixabayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    /**
     * PixabayApiService 인터페이스의 구현체를 Hilt에 제공
     */
    @Provides
    @Singleton
    fun providePixabayApiService(retrofit: Retrofit): PixabayApi {
        return retrofit.create(PixabayApi::class.java)
    }
}