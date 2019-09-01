package snsoftware.veegeeiptv.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

import java.util.List;

import snsoftware.veegeeiptv.Model.series;
import snsoftware.veegeeiptv.R;
import snsoftware.veegeeiptv.Series_season_page;


public class series_list_adapter extends BaseAdapter {
    List<series> playlist;
    Context context;
    SharedPreferences prefs;
    private ColorGenerator generator = ColorGenerator.MATERIAL;
    public series_list_adapter(List<series> playlist, Context context) {
        this.playlist = playlist;
        this.context = context;
        prefs=PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public int getCount() {
        return playlist.size();
    }

    @Override
    public Object getItem(int position) {
        return playlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        series_list_viewholder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_layout, parent, false);
            holder = new series_list_viewholder();
            holder.channel_name = convertView.findViewById(R.id.name);
            holder.icon = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (series_list_viewholder) convertView.getTag();
        }
        int iconcolor = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(playlist.get(position).getName().charAt(0)).toUpperCase(), iconcolor);
        holder.channel_name.setText(playlist.get(position).getName());
        if (!playlist.get(position).getCover().equals("")) {
            Picasso.get().load(playlist.get(position).getCover()).placeholder(drawable).into(holder.icon);
        } else{
            holder.icon.setImageDrawable(drawable);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Series_season_page.class);
                intent.putExtra("series_id",playlist.get(position).getSeriesId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class series_list_viewholder{
        TextView channel_name;
        ImageView icon;
    }
}
