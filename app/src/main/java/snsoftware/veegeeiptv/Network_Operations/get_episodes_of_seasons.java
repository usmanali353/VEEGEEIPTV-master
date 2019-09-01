package snsoftware.veegeeiptv.Network_Operations;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import snsoftware.veegeeiptv.Adapters.episodes_adapter;
import snsoftware.veegeeiptv.CodeClasses.Variables;
import snsoftware.veegeeiptv.Model.Episodes;


public class get_episodes_of_seasons {
    String username,password;
    ProgressDialog pd;
    StringBuilder link;
    Context context;
    String json;
    String season_id;
    SharedPreferences prefs;
    StringBuffer sb=new StringBuffer();
    ListView channel_list;
    List<Episodes> episodes_list;
    String series_id;
    public get_episodes_of_seasons(Context context,ListView channel_list, String username, String password,String series_id,String season_id) {
        this.context = context;
        this.channel_list=channel_list;
        this.username=username;
        this.series_id=series_id;
        this.password=password;
        this.prefs=PreferenceManager.getDefaultSharedPreferences(context);
        this.season_id=season_id;
        episodes_list=new ArrayList<>();
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        link=new StringBuilder();
        link.append(Variables.domain+"username=");
        link.append(username);
        link.append("&password=");
        link.append(password);
        link.append("&action=get_series_info&series_id=");
        link.append(series_id);
    }



    public void Call_Api(){

        Log.d("resp",link.toString());

        pd.show();

        StringRequest postRequest = new StringRequest(Request.Method.GET,
                link.toString(), new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Log.d("resp",response);

                pd.cancel();
                if(!response.equals(Variables.errormessage)) {

                    try {
                        JSONObject obj = new JSONObject(response);
                        JSONObject episodes_obj = obj.getJSONObject("episodes");
                        JSONArray ep_list = episodes_obj.getJSONArray(season_id);
                        for (int i = 0; i < ep_list.length(); i++) {
                            JSONObject ep_ob = ep_list.getJSONObject(i);
                            JSONObject ep_info = ep_ob.getJSONObject("info");
                            Episodes ep = new Episodes(ep_ob.getString("id"), ep_ob.getString("title"), ep_ob.getString("container_extension"), ep_ob.getInt("season"), ep_info.getString("movie_image"), ep_ob.getInt("episode_num"));
                            episodes_list.add(ep);
                        }
                        StringBuffer link = new StringBuffer();
                        link.append("http://metropoliptv.stream:25461/series/");
                        link.append(prefs.getString("username", ""));
                        link.append("/");
                        link.append(prefs.getString("password", ""));
                        link.append("/");
                        link.append(episodes_list.get(0).getId());
                        link.append(".");
                        link.append(episodes_list.get(0).getContainer_extension());
                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("first_episode_stream").putExtra("first_episode_stream", link.toString()));
                        channel_list.setAdapter(new episodes_adapter(episodes_list, context));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
        /*Log.e("link",link.toString());
        seriesList=new Gson().fromJson(s,new TypeToken<List<series>>(){}.getType());
        if(seriesList!=null&&seriesList.size()>0){
            channel_list.setAdapter(new series_list_adapter(seriesList,context));
        }else{
            Toast.makeText(context,"Series Not found",Toast.LENGTH_LONG).show();
        }*/
                }


            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error

                        pd.cancel();
                         Log.d("respo",error.toString());
                    }
                }
        ) ;

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        postRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.getCache().clear();

        requestQueue.add(postRequest);




    }


}
