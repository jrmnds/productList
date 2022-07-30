package com.jrmnds.productlist.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jrmnds.productlist.data.paging.ProductsPagingSource
import com.jrmnds.productlist.domain.model.Product
import com.jrmnds.productlist.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    private lateinit var _productListPagingFlow: Flow<PagingData<Product>>
    val productListPagingFlow: Flow<PagingData<Product>>
        get() = _productListPagingFlow


    init {
        getProductPaging()
    }

    private fun getProductPaging() {
        viewModelScope.launch {
            _productListPagingFlow = getPagingProductList().cachedIn(viewModelScope)
        }
    }

    fun getProductPagingByNameList(productName: String?){
        viewModelScope.launch {
            _productListPagingFlow = getProductPagingByName(productName).cachedIn(viewModelScope)
        }
    }

   private fun getProductPagingByName(productName: String?): Flow<PagingData<Product>>{
        return Pager(PagingConfig(pageSize = 20)){
            ProductsPagingSource(repository, productName)
        }.flow
    }


    private fun getPagingProductList(): Flow<PagingData<Product>> {
        return Pager(PagingConfig(pageSize = 20)) {
            ProductsPagingSource(repository, null)
        }.flow
    }
}