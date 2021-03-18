package com.daniyalxdubizzle.androidtakehomeproject.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daniyalxdubizzle.androidtakehomeproject.R
import com.daniyalxdubizzle.androidtakehomeproject.adapters.home.ItemHomeAdapter
import com.daniyalxdubizzle.androidtakehomeproject.data.model.remote.ResponseEvent
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse
import com.daniyalxdubizzle.androidtakehomeproject.databinding.ActivityMainBinding
import com.daniyalxdubizzle.androidtakehomeproject.viewmodels.ItemViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemHomeAdapter: ItemHomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.RVItem.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.RVItem.setHasFixedSize(true)

        itemViewModel.itemState.observe(this, Observer {
            when (it) {

                is ResponseEvent.Loading -> {
                }

                is ResponseEvent.Failure -> {
                }

                is ResponseEvent.Success -> {
                   // itemHomeAdapter = ItemHomeAdapter(it.data!!, it.data.results)

                    binding.RVItem.adapter =
                        ItemHomeAdapter(it.data!!) { itemDto: ItemListResponse, position: Int ->
                            openDetailFragment(ItemDetailFragment.newInstance(itemDto))
                        }

                   // binding.RVItem.adapter = itemHomeAdapter
                    itemHomeAdapter.notifyDataSetChanged()

                }

            }
        })


    }

    private fun openDetailFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container, fragment)
            .addToBackStack(if (supportFragmentManager.backStackEntryCount == 0)  "First" else null)
            .commit()
    }
}