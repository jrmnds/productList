package com.jrmnds.productlist.di

import com.jrmnds.productlist.data.repository.ProductRepositoryImpl
import com.jrmnds.productlist.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InjectionDependencyRepository {

    @Binds
    abstract fun bindProductsRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

}