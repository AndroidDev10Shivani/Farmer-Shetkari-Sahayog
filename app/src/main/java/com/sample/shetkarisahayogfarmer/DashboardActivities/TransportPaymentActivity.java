package com.sample.shetkarisahayogfarmer.DashboardActivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.sample.shetkarisahayogfarmer.DealActivities.FragmentPayment;
import com.sample.shetkarisahayogfarmer.DealActivities.FragmentTransport;
import com.sample.shetkarisahayogfarmer.R;

import java.util.ArrayList;
import java.util.List;

public class TransportPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_payment);
        TabLayout tabLayout;
        findViewById(R.id.imageView_back_transportpayment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyDealsActivity.class));
            }
        });

        setupViewpager((ViewPager) findViewById(R.id.viewpager));

        tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager((ViewPager) findViewById(R.id.viewpager));
    }

    private void setupViewpager(ViewPager viewPager) {
        ViewpagerAdapter adapter = new ViewpagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTransport(), "Transport");
        adapter.addFragment(new FragmentPayment(), "Payment");
        viewPager.setAdapter(adapter);
    }

    public class ViewpagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewpagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}