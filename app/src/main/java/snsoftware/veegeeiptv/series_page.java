package snsoftware.veegeeiptv;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import snsoftware.veegeeiptv.Network_Operations.get_series_by_category;


public class series_page extends AppCompatActivity {
SharedPreferences prefs;
ListView series_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getIntent().getStringExtra("category_name"));
        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        series_list=findViewById(R.id.series_list);
        new get_series_by_category(series_page.this,series_list,prefs.getString("username","")
                ,prefs.getString("password","")
                ,getIntent().getStringExtra("category_id")).Call_Api();
    }

}
