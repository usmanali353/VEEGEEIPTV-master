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

import snsoftware.veegeeiptv.Model.Episodes;
import snsoftware.veegeeiptv.R;


public class episodes_adapter extends BaseAdapter {
    List<Episodes> playlist;
    Context context;
    SharedPreferences prefs;
    private ColorGenerator generator = ColorGenerator.MATERIAL;
    public episodes_adapter(List<Episodes> playlist, Context context) {
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
        episodes_list_viewholder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_layout, parent, false);
            holder = new episodes_list_viewholder();
            holder.channel_name = convertView.findViewById(R.id.name);
            holder.icon = convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (episodes_list_viewholder) convertView.getTag();
        }
        int iconcolor = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(playlist.get(position).getEpisodeNum()), iconcolor);
        holder.channel_name.setText(playlist.get(position).getTitle());
        if (!playlist.get(position).getMovie_image().equals("")) {
            Picasso.get().load(playlist.get(position).getMovie_image()).placeholder(drawable).into(holder.icon);
        } else{
            holder.icon.setImageDrawable(drawable);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer link=new StringBuffer();
                link.append("http://metropoliptv.stream:25461/series/");
                link.append(prefs.getString("username",""));
                link.append("/");
                link.append(prefs.getString("password",""));
                link.append("/");
                link.append(playlist.get(position).getId());
                link.append(".");
                link.append(playlist.get(position).getContainer_extension());
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("episode_stream_link").putExtra("episode_stream_link",link.toString()));

               /* Intent intent = new Intent(context, media_player.class);
                intent.putExtra("channel_url",link.toString());
                context.startActivity(intent);*/
            }
        });
        return convertView;
    }
    class episodes_list_viewholder{
        TextView channel_name;
        ImageView icon;
    }
}
