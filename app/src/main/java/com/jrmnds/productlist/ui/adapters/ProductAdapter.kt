package com.jrmnds.productlist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jrmnds.productlist.databinding.ProductItemBinding
import com.jrmnds.productlist.domain.model.Product
import com.jrmnds.productlist.domain.model.PromoIcon

class ProductAdapter: PagingDataAdapter<Product, RecyclerView.ViewHolder>(DiffCallback){

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product: Product = getItem(position)!!
        bindViews(product, holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ProductViewHolder(
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )


    class ProductViewHolder(val productItemBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(productItemBinding.root)

    companion object DiffCallback: DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    private fun bindViews(
        product: Product,
        holder: RecyclerView.ViewHolder,
    ) {
        product.let {
            val viewHolder = holder as ProductViewHolder
            with(viewHolder.productItemBinding) {
                productName(product.productName)
                reviewTotalCount(product.reviewInformation?.reviewSummary?.reviewCount)
                productPrice(product.salesPriceIncVat)
                hasPromotionPrice(product.listPriceIncVat)
                loadImage(productImageId, product.productImage)
                retailChoiceIconIsVisible(product.promoIcon)
                retailPromotionIconIsVisible(product.promoIcon)
                isNextDayDelivery(product.nextDayDelivery)
                hideAllIcons(product.promoIcon)
                transformeRating(product.reviewInformation?.reviewSummary?.reviewAverage)
                productInfoLabels(product.productLabels)
            }
        }
    }

    private fun ProductItemBinding.loadImage(productImageId: ImageView, productImage: String) {
        Glide.with(productImageId.context).load(productImage).into(
            productImageId
        ).waitForLayout()
    }

    private fun ProductItemBinding.productInfoLabels(productLabels: List<String>) {
        productInfoId.text = breakLinesForProductLabels(productLabels)
    }

    private fun ProductItemBinding.productPrice(productPrice: Double) {
        productPriceInfoId.text = productPrice.toString()
    }

    private fun ProductItemBinding.reviewTotalCount(reviewCount: Int?) {
        productRatingReviewsId.text = (reviewCount ?: 0).toString()
    }

    private fun ProductItemBinding.productName(productName: String) {
        productNameId.text = productName
    }

    private fun ProductItemBinding.hasPromotionPrice(
        productPromotionPrice: Double?
    ){
        when{
            productPromotionPrice != null -> {
                productPreviousPriceId.text = "From: $productPromotionPrice"
                productPreviousPriceId.visibility = View.VISIBLE
            }
        }
    }

    private fun ProductItemBinding.transformeRating(productRating: Float?) {
        if (productRating != null) {
            productRatingBarId.rating = productRating  / 2
        } else{
            productRatingBarId.rating = 0.0F
        }
    }


    private fun ProductItemBinding.retailChoiceIconIsVisible(
        productIcon: PromoIcon?
    ){
        when{
            productIcon != null && productIcon.type.equals("coolblues-choice") -> {
                includeRetailChoiceId.root.visibility = View.VISIBLE
            }
        }
    }

    private fun ProductItemBinding.retailPromotionIconIsVisible(
        productIcon: PromoIcon?
    ){
        when{
            productIcon != null && productIcon.type.equals("action-price") ->{
                includeRetailPromotionId.root.visibility = View.VISIBLE
            }
        }

    }

    private fun ProductItemBinding.hideAllIcons(
        productIcon: PromoIcon?
    ){
        when (productIcon) {
            null -> {
                includeRetailChoiceId.root.visibility = View.GONE
                includeRetailPromotionId.root.visibility = View.GONE
            }
        }
    }

    private fun ProductItemBinding.isNextDayDelivery(
        productIsNextDayDelivery: Boolean
    ){
        when{
            productIsNextDayDelivery -> {
                productDeliveryInfoId.visibility = View.VISIBLE
            }

            !productIsNextDayDelivery -> {
                productDeliveryInfoId.visibility = View.GONE
            }
        }
    }

    private fun breakLinesForProductLabels(productLabels: List<String?>?): String {
        var string = ""
        productLabels?.forEach {
            string = it + "\n" + string
        }
        return string
    }
}