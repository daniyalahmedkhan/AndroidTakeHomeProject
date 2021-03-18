package com.daniyalxdubizzle.androidtakehomeproject.adapters.home

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.daniyalxdubizzle.androidtakehomeproject.R
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemResponse
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper.Companion.loadImage
import javax.inject.Inject


class ItemHomeAdapter @Inject constructor(
    private val itemList: ItemResponse,
    val clickListener: (ItemListResponse, Int) -> Unit
) :
    RecyclerView.Adapter<ItemHomeAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.productName) as TextView
        val productImage = itemView.findViewById(R.id.productImage) as ImageView
        val CV_ITEM = itemView.findViewById(R.id.CV_ITEM) as CardView

        fun bindItem(data: ItemListResponse) {
            name.text = data.name

            loadImage(
                itemView.context,
                data.image_urls_thumbnails.get(0),
                productImage
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return itemList.results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemList.results[position])
        holder.CV_ITEM.setOnClickListener { clickListener(itemList.results[position], position) }
    }
}