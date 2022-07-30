package com.jrmnds.productlist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.jrmnds.productlist.databinding.FragmentProductListBinding
import com.jrmnds.productlist.ui.adapters.ProductAdapter
import com.jrmnds.productlist.ui.adapters.PageLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var _binding: FragmentProductListBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productLoadStateAdapter: PageLoadStateAdapter
    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        productAdapter = ProductAdapter()
        productLoadStateAdapter = PageLoadStateAdapter()
        configureRecycle()
        observeState()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeProducts()
        observeSearcher()
    }

    private fun configureRecycle() {
        _binding.productRecyclerViewId.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = productAdapter.withLoadStateFooter(
                footer = productLoadStateAdapter
            )
        }
    }

    private fun observeProducts() {
        lifecycleScope.launch {
            viewModel.productListPagingFlow.collectLatest {
                productAdapter.submitData(it)
            }
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            productAdapter.loadStateFlow.collect { loadState ->
                val isEmpty = loadState.source.refresh is LoadState.Error
                _binding.connectionErroString.isVisible = isEmpty
                _binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }
    }

    private fun observeProductByName(){
        lifecycleScope.launch {
            viewModel.productListPagingFlow.collectLatest {
                productAdapter.submitData(it)
            }
        }
    }

    private fun callGetProductByName(productName: String?) {
        clearPagingData()
        viewModel.getProductPagingByNameList(productName)
        observeProductByName()
    }

    private fun clearPagingData() {
        productAdapter.submitData(lifecycle, PagingData.empty())
    }

    private fun observeSearcher() {
        _binding.searchProductId.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                callGetProductByName(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }
}