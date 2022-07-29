package com.jrmnds.productlist.ui

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.jrmnds.productlist.R
import com.jrmnds.productlist.databinding.FragmentProductListBinding
import com.jrmnds.productlist.ui.adapters.ProductAdapter
import com.jrmnds.productlist.ui.adapters.ProductLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private lateinit var _binding: FragmentProductListBinding
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productLoadStateAdapter: ProductLoadStateAdapter
    private lateinit var viewModel: ProductListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        productAdapter = ProductAdapter()
        productLoadStateAdapter = ProductLoadStateAdapter()
        configureRecycle()
        observeState()
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeProducts()
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

    private fun observeProducts(){
        lifecycleScope.launch {
            viewModel.productListPagingFlow.collectLatest {
                productAdapter.submitData(it)
            }
        }
    }

    private fun observeState(){
        lifecycleScope.launch{
            productAdapter.loadStateFlow.collect{ loadState ->
                val isEmpty = loadState.source.refresh is LoadState.Error
                _binding.connectionErroString.isVisible = isEmpty
                _binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            }
        }
    }
}