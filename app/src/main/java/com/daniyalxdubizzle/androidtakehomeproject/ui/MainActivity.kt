package com.daniyalxdubizzle.androidtakehomeproject.ui


import android.nfc.Tag
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.SearchView
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
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper
import com.daniyalxdubizzle.androidtakehomeproject.viewmodels.ItemViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar.view.*
import org.jetbrains.anko.toast


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /*
    * This is Home First Screen Activity Where Api hit to fetch records through ViewModel by using Dagger-Hilt
    * */
    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var itemHomeAdapter: ItemHomeAdapter
    private val TAG = "FIRST"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        *  Data Binding
        * */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpInitials()

        /*
        * ViewModel mutable list observer and using sealed class 3 objects
        * */
        itemViewModel.itemState.observe(this, Observer {
            when (it) {

                is ResponseEvent.Loading -> {
                    binding.shimmerViewContainer.startShimmer()
                }

                is ResponseEvent.Failure -> {
                    toast(it.error.toString())
                }

                is ResponseEvent.Success -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.shimmerViewContainer.visibility = View.GONE


                    /*
                    * List item click handle and open detail fragment by setting viewModel position item
                    * */
                    itemHomeAdapter =
                        ItemHomeAdapter(it.data!!) { itemDto: ItemListResponse, position: Int ->
                            openDetailFragment(ItemDetailFragment.newInstance())
                            itemViewModel.setItemPos(itemDto)
                        }
                    binding.RVItem.adapter = itemHomeAdapter


                    // binding.RVItem.adapter = itemHomeAdapter
                    itemHomeAdapter.notifyDataSetChanged()

                }

            }
        })

        /*
        * Search View in Toolbar to sort list items
        * */
        binding.itemSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                itemHomeAdapter.filter.filter(p0)
                return false
            }

        })
    }

    /*
    * Fragment Management
    * */
    private fun openDetailFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.container, fragment)
            .addToBackStack(if (supportFragmentManager.backStackEntryCount == 0) TAG else null)
            .commit()
    }

    /*
    * Views Initialize and Click Handles
    * */
    private fun setUpInitials() {
        binding.RVItem.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.RVItem.setHasFixedSize(true)

        binding.toolbar.home_tb_title.text = resources.getString(R.string.HOME)
        binding.toolbar.home_tb_search_icon.setOnClickListener {
            if (binding.itemSearch.visibility == View.GONE) {
                binding.itemSearch.visibility = View.VISIBLE
                binding.itemSearchBorder.visibility = View.VISIBLE
            } else {
                binding.itemSearch.visibility = View.GONE
                binding.itemSearchBorder.visibility = View.GONE
            }
        }

    }
}