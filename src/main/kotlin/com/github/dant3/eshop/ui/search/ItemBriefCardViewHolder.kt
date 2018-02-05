package com.github.dant3.eshop.ui.search

import android.arch.lifecycle.MutableLiveData
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.github.dant3.eshop.R
import com.github.dant3.eshop.model.Image
import com.github.dant3.eshop.model.ItemSummary
import com.github.dant3.eshop.model.Price
import com.github.dant3.eshop.ui.util.LayoutBasedViewHolder
import java.lang.Exception
import java.lang.ref.WeakReference

class ItemBriefCardViewHolder(parent: ViewGroup): LayoutBasedViewHolder(parent, R.layout.item_brief_card_view) {
    private val itemPhotoView = itemView.findViewById<ImageView>(R.id.item_photo)
    private val itemTitleView = itemView.findViewById<TextView>(R.id.item_title)
    private val itemPriceView = itemView.findViewById<TextView>(R.id.item_price)
    private val loadingIndicator = itemView.findViewById<ProgressBar>(R.id.loading_indicator)

    private val photoDescriptionTag = R.id.photo_description_tag

    fun bind(itemSummary: ItemSummary, itemClicked: MutableLiveData<ItemSummary>) {
        itemView.setOnClickListener {
            itemClicked.value = itemSummary
        }

        itemTitleView.text = itemSummary.title
        itemPriceView.text = itemSummary.price.printed()

        loadingIndicator.startImageLoadingProgress(itemSummary.image)
        val loadingIndicatorRef = WeakReference(loadingIndicator)

        Glide.with(itemView.context)
                .load(itemSummary.image)
                .listener(object: RequestListener<Image, GlideDrawable> {
                    override fun onException(e: Exception,
                                             model: Image,
                                             target: Target<GlideDrawable>,
                                             isFirstResource: Boolean): Boolean {
                        loadingIndicatorRef.get()?.stopPhotoLoadingProgress(model)
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable,
                                                 model: Image,
                                                 target: Target<GlideDrawable>,
                                                 isFromMemoryCache: Boolean,
                                                 isFirstResource: Boolean): Boolean {
                        loadingIndicatorRef.get()?.stopPhotoLoadingProgress(model)
                        return false
                    }
                })
                .fitCenter()
                .crossFade()
                .into(itemPhotoView)
    }

    private fun ProgressBar.startImageLoadingProgress(image: Image) {
        visibility = View.VISIBLE
        setTag(photoDescriptionTag, image)
    }

    private fun ProgressBar.stopPhotoLoadingProgress(image: Image) {
        if (getTag(photoDescriptionTag) == image) {
            visibility = View.GONE
        }
    }

    private fun Price.printed(): CharSequence = amount.toPlainString() + " " + currency
}