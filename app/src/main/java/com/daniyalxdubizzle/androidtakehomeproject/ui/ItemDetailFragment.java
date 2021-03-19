package com.daniyalxdubizzle.androidtakehomeproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.daniyalxdubizzle.androidtakehomeproject.R;
import com.daniyalxdubizzle.androidtakehomeproject.data.model.response.ItemListResponse;
import com.daniyalxdubizzle.androidtakehomeproject.databinding.ItemDetailFragmentBinding;
import com.daniyalxdubizzle.androidtakehomeproject.utilities.CommonExtentionKt;
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper;
import com.daniyalxdubizzle.androidtakehomeproject.viewmodels.ItemViewModel;
import com.daniyalxdubizzle.androidtakehomeproject.viewmodels.ItemViewModelProviderFactory;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ItemDetailFragment extends Fragment {


    public ItemListResponse singleViewItem = null;
    ItemDetailFragmentBinding binding;
    private boolean isFav = false;
    private boolean isCart = false;

    public static ItemDetailFragment newInstance() {

        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        //itemDetailFragment.singleViewItem = singleViewItem;
        return itemDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.item_detail_fragment, container, false);

        ItemViewModel viewModel = new ViewModelProvider(getActivity()).get(ItemViewModel.class);


        GeneralHelper.Companion.hideKeyboardFrom(getActivity(), binding.getRoot());
        viewListeners();
        setUp(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void setUp(ItemViewModel itemViewModel) {
        GeneralHelper.Companion.loadImage(getActivity(), itemViewModel.getItemPosValue().getValue().getImage_urls().get(0), binding.IVITEM);
        binding.TVITEMNAME.setText(CommonExtentionKt.getCapitalizeWords(itemViewModel.getItemPosValue().getValue().getName()));
        binding.TVITEMPRICE.setText(itemViewModel.getItemPosValue().getValue().getPrice());
    }

    private void viewListeners() {
        binding.CVFAV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.views_animation));
                if (isFav) {
                    isFav = false;
                    binding.IVHEART.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                } else {
                    isFav = true;
                    binding.IVHEART.setColorFilter(ContextCompat.getColor(getContext(), R.color.green2));
                }
            }
        });

        binding.CVCART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.views_animation));
                if (isCart) {
                    isCart = false;
                    binding.IVCART.setColorFilter(ContextCompat.getColor(getContext(), R.color.black));
                } else {
                    isCart = true;
                    binding.IVCART.setColorFilter(ContextCompat.getColor(getContext(), R.color.green2));
                }
            }
        });

        binding.IVBACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

}

