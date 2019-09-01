package snsoftware.veegeeiptv;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import snsoftware.veegeeiptv.Network_Operations.get_series_info;


public class Series_season_page extends AppCompatActivity {
ListView season_list;
SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_season_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        season_list=findViewById(R.id.seasons_list);
           String series_id= String.valueOf(getIntent().getIntExtra("series_id",0));
        new get_series_info(this,season_list,prefs.getString("username","")
                ,prefs.getString("password",""),series_id).Call_Api();
    }

}
