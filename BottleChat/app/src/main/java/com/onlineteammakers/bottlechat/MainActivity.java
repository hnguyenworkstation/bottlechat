package com.onlineteammakers.bottlechat;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.onlineteammakers.bottlechat.Fragments.CommunicateFragment;
import com.onlineteammakers.bottlechat.Fragments.ExploreFragment;
import com.onlineteammakers.bottlechat.Fragments.HomeFragment;
import com.onlineteammakers.bottlechat.Fragments.ProfileFragment;
import com.onlineteammakers.bottlechat.Fragments.SeekAroundFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static TabLayout tabLayout;
    private Toolbar toolbar;

    private static int[] _unSelTabIcons = {
            R.drawable.ic_home_black,
            R.drawable.ic_update_black,
            R.drawable.ic_track_changes_black,
            R.drawable.ic_explore_black,
            R.drawable.ic_account_circle_black
    };

    private static int[] _selTabIcons = {
            R.drawable.ic_home_white,
            R.drawable.ic_update_white,
            R.drawable.ic_track_changes_white,
            R.drawable.ic_explore_white,
            R.drawable.ic_account_circle_white
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    private void setupTabIcons() {
        for (int position = 0; position < _selTabIcons.length; position++) {
            tabLayout.getTabAt(position).setIcon(_selTabIcons[position]);
        }
    }

    private void setupSelectedTab(int position) {
        tabLayout.getTabAt(position).setIcon(_selTabIcons[position]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getBaseContext(), getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new CommunicateFragment(), "Chat");
        adapter.addFragment(new SeekAroundFragment(), "Seek");
        adapter.addFragment(new ExploreFragment(), "Explore");
        adapter.addFragment(new ProfileFragment(), "Me");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitle = new ArrayList<>();

        public Context mContext;

        public ViewPagerAdapter(Context mContext,FragmentManager manager) {
            super(manager);
            this.mContext = mContext;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment,String title) {
            mFragmentList.add(fragment);
            mFragmentTitle.add(title);
        }
    }
}
