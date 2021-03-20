package com.daniyalxdubizzle.androidtakehomeproject.adapters.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.daniyalxdubizzle.androidtakehomeproject.R
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemResponse
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper.Companion.loadImage
import com.daniyalxdubizzle.androidtakehomeproject.utilities.capitalizeWords
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ItemHomeAdapter @Inject constructor(
    private val itemList: ItemResponse,
    val clickListener: (ItemListResponse, Int) -> Unit
) : RecyclerView.Adapter<ItemHomeAdapter.ViewHolder>(), Filterable {

    var itemListFilter: List<ItemListResponse> = ArrayList<ItemListResponse>()

    init {
        itemListFilter = itemList.results
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById(R.id.productName) as TextView
        private val productPrice = itemView.findViewById(R.id.productPrice) as TextView
        private val productImage = itemView.findViewById(R.id.productImage) as ImageView
        private val productDate = itemView.findViewById(R.id.productDate) as TextView
        val CV_ITEM = itemView.findViewById(R.id.CV_ITEM) as CardView

        fun bindItem(data: ItemListResponse) {
            name.text = data.name.capitalizeWords
            productPrice.text = data.price
            productDate.text = GeneralHelper.dateParse(data.created_at)
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
        return itemListFilter.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemListFilter[position])
        holder.CV_ITEM.setOnClickListener { clickListener(itemListFilter[position], position) }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemListFilter = itemList.results
                } else {
                    val resultList = ArrayList<ItemListResponse>()
                    for (row in itemList.results.indices) {
                        if (itemList.results[row].name.toLowerCase(Locale.ROOT)
                                .contains(charSearch.toLowerCase(Locale.ROOT))
                        ) {
                            resultList.add(itemList.results[row])
                        }
                    }
                    itemListFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemListFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemListFilter = results?.values as ArrayList<ItemListResponse>
                notifyDataSetChanged()
            }

        }
    }
}