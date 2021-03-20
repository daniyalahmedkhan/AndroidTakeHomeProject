package com.daniyalxdubizzle.androidtakehomeproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.daniyalxdubizzle.androidtakehomeproject.R;
import com.daniyalxdubizzle.androidtakehomeproject.databinding.ItemDetailFragmentBinding;
import com.daniyalxdubizzle.androidtakehomeproject.utilities.CommonExtentionKt;
import com.daniyalxdubizzle.androidtakehomeproject.utilities.GeneralHelper;
import com.daniyalxdubizzle.androidtakehomeproject.viewmodels.ItemViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ItemDetailFragment extends Fragment {

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

        /*
         * ViewModel factory to initialize
         * */
        ItemViewModel viewModel = new ViewModelProvider(getActivity()).get(ItemViewModel.class);


        GeneralHelper.Companion.hideKeyboardFrom(getActivity(), binding.getRoot());
        viewListeners();

        /*
         * Setting up views data by fetching from ViewModel to make lifecycle aware
         * */
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


    /*
     * Handle view click listeners
     * */
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

        binding.BTNOrderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.views_animation));
                Toast.makeText(getActivity(), "Ordered", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();

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

