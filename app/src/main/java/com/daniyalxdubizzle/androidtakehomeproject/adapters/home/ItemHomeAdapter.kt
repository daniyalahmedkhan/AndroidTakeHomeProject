package com.daniyalxdubizzle.androidtakehomeproject.adapters.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.daniyalxdubizzle.androidtakehomeproject.R
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemResponse
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ItemHomeAdapter @Inject constructor(private val itemList: ItemResponse ) :
    RecyclerView.Adapter<ItemHomeAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById(R.id.productName) as TextView
        val productImage = itemView.findViewById(R.id.productImage) as ImageView

        fun bindItem(data: ItemListResponse) {
            name.text = data.name
            Glide.with(itemView.context)
                .asBitmap()
                .load(data.image_urls_thumbnails[0])
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        productImage.setImageBitmap(resource)
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })
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
    }
}