package com.teguh.sejarahislam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.themes.Book;

import java.util.List;

public class AdapterBookView extends PagerAdapter {

    Context context;
    List<Book> detailList;
    LayoutInflater inflater;


    public AdapterBookView(Context context, List<Book> detailList) {
        this.context = context;
        this.detailList = detailList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return detailList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull @org.jetbrains.annotations.NotNull View view, @NonNull @org.jetbrains.annotations.NotNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        final Book modelMaterial = detailList.get(position);
        int materialIconIv = modelMaterial.getImages();


        View view = inflater.inflate(R.layout.view_pager_detail, container, false);

        ImageView imgDetail = view.findViewById(R.id.detail_material_plant);

        try {
            Picasso.get()
                    .load(materialIconIv)
                    .into(imgDetail);
        } catch (Exception e) {
            imgDetail.setImageResource(R.drawable.abdulqadir_1);
        }

        container.addView(view);
        return view;
    }

}
