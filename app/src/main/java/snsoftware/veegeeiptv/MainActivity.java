package snsoftware.veegeeiptv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import snsoftware.veegeeiptv.Fragments.live_stream_fragment;
import snsoftware.veegeeiptv.Fragments.movies_fragment;
import snsoftware.veegeeiptv.Fragments.recent_fragment;


public class MainActivity extends AppCompatActivity {
TabLayout tabLayout;
ViewPager viewPager;
SharedPreferences prefs;
    private int[] tabIcons = {
            R.drawable.livetv2,
            R.drawable.movies2,
            R.drawable.series2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Live TV");
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
         prefs=PreferenceManager.getDefaultSharedPreferences(this);
       tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        View view1 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.livetv2);
        tabLayout.getTabAt(0).setCustomView(view1);
        View view2 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.movies2);
        tabLayout.getTabAt(1).setCustomView(view2);
        View view3 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.series2);
        tabLayout.getTabAt(2).setCustomView(view3);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    getSupportActionBar().setTitle("Live TV");
                }else if(tab.getPosition()==1){
                    getSupportActionBar().setTitle("Movies");
                }else if(tab.getPosition()==2){
                    getSupportActionBar().setTitle("Series");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                 getSupportActionBar().setTitle("Live Tv");
                }else if(tab.getPosition()==1){
                    getSupportActionBar().setTitle("Movies");
                }else if(tab.getPosition()==2){
                    getSupportActionBar().setTitle("Series");
                }
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new live_stream_fragment());
        adapter.addFragment(new movies_fragment());
        adapter.addFragment(new recent_fragment());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            // return null to display only the icon
            return null;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_settings){
            prefs.edit().remove("username").apply();
            prefs.edit().remove("password").apply();
            startActivity(new Intent(MainActivity.this,login_page.class));
            finish();
        }/*else if(item.getItemId()==R.id.contact_us){
            boolean installed = appInstalledOrNot("com.whatsapp");
            if(installed) {
                String url = "https://api.whatsapp.com/send?phone=+4915166341860";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            } else {
                Toast.makeText(this,"Whatsapp need to be installed on your Device to perform this operation",Toast.LENGTH_LONG).show();
            }

        }*/
        return super.onOptionsItemSelected(item);
        }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
