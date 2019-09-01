package snsoftware.veegeeiptv.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
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

import snsoftware.veegeeiptv.Model.channels;
import snsoftware.veegeeiptv.R;


public class channel_list_adapter extends BaseAdapter {
    List<channels> playlist;
    Context context;
    SharedPreferences prefs;
    private ColorGenerator generator = ColorGenerator.MATERIAL;
    public channel_list_adapter(List<channels> playlist, Context context) {
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
        channel_list_viewholder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_layout, parent, false);
            holder = new channel_list_viewholder();
            holder.channel_name = convertView.findViewById(R.id.name);
            holder.icon = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (channel_list_viewholder) convertView.getTag();
        }
        int iconcolor = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(playlist.get(position).getName().charAt(playlist.get(position).getName().indexOf("|")+2)).toUpperCase(), iconcolor);
        holder.channel_name.setText(playlist.get(position).getName());
        if (!playlist.get(position).getStream_icon().equals("")) {
            Picasso.get().load(playlist.get(position).getStream_icon()).placeholder(drawable).into(holder.icon);
        } else{
        holder.icon.setImageDrawable(drawable);
    }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer link=new StringBuffer();
                link.append("http://metropoliptv.stream:25461/live/");
                link.append(prefs.getString("username",""));
                link.append("/");
                link.append(prefs.getString("password",""));
                link.append("/");
                link.append(playlist.get(position).getStream_id());
                link.append(".ts");
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("link").putExtra("link",link.toString()));
            }
        });
        return convertView;
    }
    class channel_list_viewholder{
        TextView channel_name;
        ImageView icon;
    }
}
