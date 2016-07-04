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
import android.view.Menu;
import android.view.MenuItem;

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

        /*  Not sure if we need a toolbar here

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            setTitle(null);
        */
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.ac) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
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

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitle.add(title);
        }
    }
}
