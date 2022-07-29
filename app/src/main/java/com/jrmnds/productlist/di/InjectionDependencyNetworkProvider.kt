package com.jrmnds.productlist.di

import com.jrmnds.productlist.common.Constants
import com.jrmnds.productlist.data.remote.ProductApiServices
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InjectionDependencyNetworkProvider {

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
         .addConverterFactory(MoshiConverterFactory.create(moshi))
         .baseUrl(Constants.BASE_URL)
         .build()

    @Provides
    @Singleton
    fun provideProductsApi(retrofit: Retrofit): ProductApiServices =
        retrofit.create(ProductApiServices::class.java)


    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


}