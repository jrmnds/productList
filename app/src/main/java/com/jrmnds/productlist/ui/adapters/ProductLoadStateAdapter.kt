package com.jrmnds.productlist.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jrmnds.productlist.databinding.PageStateItemBinding

class ProductLoadStateAdapter : LoadStateAdapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RecyclerView.ViewHolder =
        PageLoadStateViewHolder(
            PageStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, loadState: LoadState) {
        bindStates(holder, loadState)
    }

    class PageLoadStateViewHolder(val pageStateItemBinding: PageStateItemBinding) :
        RecyclerView.ViewHolder(pageStateItemBinding.root)


    private fun bindStates(
        holder: RecyclerView.ViewHolder,
        loadState: LoadState
    ) {
        val viewHolder = holder as PageLoadStateViewHolder
        with(viewHolder.pageStateItemBinding) {
            isErrorState(loadState)
            isLoadingState(loadState)
        }
    }

    private fun PageStateItemBinding.isLoadingState(loadState: LoadState) {
        progressBar.isVisible = loadState is LoadState.Loading
    }

    private fun PageStateItemBinding.isErrorState(loadState: LoadState) {
        errorMsg.isVisible = loadState is LoadState.Error
    }
}