package com.teguh.sejarahislam.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.teguh.sejarahislam.R;
import com.teguh.sejarahislam.common.About;

import java.util.List;

public class AdapterAboutView extends PagerAdapter {

    Context context;
    List<About> detailList;
    LayoutInflater inflater;


    public AdapterAboutView(Context context, List<About> detailList) {
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
        final About modelMaterial = detailList.get(position);
        int materialIconIv = modelMaterial.getImages();
        String tvNama = modelMaterial.getName();
        String tvNIM = modelMaterial.getNim();
        String tvEmail = modelMaterial.getEmail();
        String tvJob = modelMaterial.getJob();


        View view = inflater.inflate(R.layout.view_pager_about, container, false);
        Typeface typeface = Typeface.createFromAsset(view.getContext().getAssets(), "fonts/FredokaOneRegular.ttf");

        ImageView imgDetail = view.findViewById(R.id.profile_pic);
        TextView txtName = view.findViewById(R.id.tv_name);
        TextView txtNIM = view.findViewById(R.id.tv_nim);
        TextView txtEmail = view.findViewById(R.id.tv_email);
        TextView txtJob = view.findViewById(R.id.job_projek);
        TextView txtNama_Nama = view.findViewById(R.id.nama_nama);
        TextView txtNIM_NIM = view.findViewById(R.id.nim_nim);
        TextView txtEmail_Email = view.findViewById(R.id.email_email);

        txtName.setTypeface(typeface);
        txtNIM.setTypeface(typeface);
        txtEmail.setTypeface(typeface);
        txtJob.setTypeface(typeface);
        txtNama_Nama.setTypeface(typeface);
        txtNIM_NIM.setTypeface(typeface);
        txtEmail_Email.setTypeface(typeface);

        try {
            Picasso.get()
                    .load(materialIconIv)
                    .into(imgDetail);
        } catch (Exception e) {
            imgDetail.setImageResource(R.drawable.abdulqadir_1);
        }

        txtName.setText(tvNama);
        txtNIM.setText(tvNIM);
        txtEmail.setText(tvEmail);
        txtJob.setText(tvJob);

        container.addView(view);
        return view;
    }

}
