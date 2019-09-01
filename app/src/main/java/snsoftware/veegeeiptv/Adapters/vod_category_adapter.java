package snsoftware.veegeeiptv.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.List;

import snsoftware.veegeeiptv.Model.category;
import snsoftware.veegeeiptv.R;
import snsoftware.veegeeiptv.movies_page;


public class vod_category_adapter extends BaseAdapter {
    public vod_category_adapter(List<category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
    }

    List<category> categoryList;
    Context context;
    private ColorGenerator generator = ColorGenerator.MATERIAL;
    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        vod_category_viewholder holder;
        if(convertView==null){
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_layout,parent,false);
            holder=new vod_category_viewholder();
            holder.category_name=convertView.findViewById(R.id.name);
            holder.icon=convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else{
            holder=(vod_category_viewholder) convertView.getTag();
        }
        int iconcolor=generator.getRandomColor();
        TextDrawable drawable=TextDrawable.builder().buildRound(String.valueOf(categoryList.get(position).getCategory_name().charAt(0)),iconcolor);
        holder.category_name.setText(categoryList.get(position).getCategory_name());
        holder.icon.setImageDrawable(drawable);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, movies_page.class);
                i.putExtra("category_name",categoryList.get(position).getCategory_name());
                i.putExtra("category_id",categoryList.get(position).getCategory_id());
                context.startActivity(i);
            }
        });
        return convertView;
    }
    class vod_category_viewholder{
        TextView category_name;
        ImageView icon;
    }
}
