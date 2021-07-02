package com.teguh.sejarahislam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.teguh.sejarahislam.adapter.AdapterBookView;
import com.teguh.sejarahislam.adapter.DepthPageTransformer;
import com.teguh.sejarahislam.common.Shared;
import com.teguh.sejarahislam.services.BackgroundMusicBookService;
import com.teguh.sejarahislam.themes.Book;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_SCALE;

public class BukuActivity extends FragmentActivity {

    private Button btnPrev, btnNext;
    private ViewPager viewPager;
    private int mCurrentPage;
    private AdapterBookView adapterBookView;
    private List<Book> mList;
    private Animation scaleUp, scaleDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buku);
        ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPage = i;

                if (i == 0) {

                    btnNext.setEnabled(true);
                    btnPrev.setEnabled(false);
                    btnPrev.setVisibility(View.INVISIBLE);

                } else if (i == mList.size() - 1) {

                    btnNext.setEnabled(false);
                    btnPrev.setEnabled(true);
                    btnPrev.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.INVISIBLE);

                } else {

                    btnNext.setEnabled(true);
                    btnPrev.setEnabled(true);
                    btnPrev.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);


                }

            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        };

        mList = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            String drawableResourceName = String.format(Locale.US,"abdulqadir_%d", i);
            int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
            mList.add(new Book(drawableResourceId));

        }
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        adapterBookView = new AdapterBookView(this, mList);
        viewPager = findViewById(R.id.viewPagerDetail);

        viewPager.setAdapter(adapterBookView);
        viewPager.addOnPageChangeListener(viewListener);

        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnPrev.setVisibility(View.INVISIBLE);


        PushDownAnim.setPushDownAnimTo(btnNext)
                .setScale( MODE_SCALE,
                        1.2f)
                .setDurationPush( 100 )
                .setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
                .setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(mCurrentPage + 1);
                    }
                });

        PushDownAnim.setPushDownAnimTo(btnPrev)
                .setScale( MODE_SCALE,
                        1.2f)
                .setDurationPush( 100 )
                .setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
                .setInterpolatorPush( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setInterpolatorRelease( PushDownAnim.DEFAULT_INTERPOLATOR )
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(mCurrentPage - 1);
                    }
                });

        viewPager.setPageTransformer(true, new DepthPageTransformer());

    }

    @Override
    protected void onResume() {
        super.onResume();
        playBackgroundMusic();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(BukuActivity.this, BackgroundMusicBookService.class));
    }


    public void playBackgroundMusic() {
        Intent intent = new Intent(BukuActivity.this, BackgroundMusicBookService.class);
        startService(intent);
    }
}