package com.daniyalxdubizzle.androidtakehomeproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.daniyalxdubizzle.androidtakehomeproject.R;
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse;
import com.daniyalxdubizzle.androidtakehomeproject.databinding.ItemDetailFragmentBinding;
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper;


public class ItemDetailFragment extends Fragment {


    public ItemListResponse singleViewItem = null;
    ItemDetailFragmentBinding binding;

    public static ItemDetailFragment newInstance(ItemListResponse singleViewItem) {

        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.singleViewItem = singleViewItem;
        return itemDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.item_detail_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (singleViewItem != null) {
            setUp(singleViewItem);
        }
    }


    private void setUp(ItemListResponse singleViewItem) {
        GeneralHelper.Companion.loadImage(getActivity(), singleViewItem.getImage_urls().get(0), binding.IVITEM);
        binding.TVITEMNAME.setText(singleViewItem.getName());
        binding.TVITEMPRICE.setText(singleViewItem.getPrice());

    }

}

