package snsoftware.veegeeiptv.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import snsoftware.veegeeiptv.Network_Operations.get_vod_categories;
import snsoftware.veegeeiptv.R;


public class movies_fragment extends Fragment {
    ListView category_list;
    SharedPreferences prefs;
    ProgressBar pbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.movies_fragment,container,false);

        pbar=v.findViewById(R.id.pbar);

        category_list=v.findViewById(R.id.channel_list);
        prefs=PreferenceManager.getDefaultSharedPreferences(getActivity());
        new get_vod_categories(getActivity(),category_list,
                prefs.getString("username",""),
                prefs.getString(
                        "password",""),
                pbar)
                .Call_Api();
        return v;
    }
}
