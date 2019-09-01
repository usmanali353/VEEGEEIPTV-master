package snsoftware.veegeeiptv.Network_Operations;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.List;

import snsoftware.veegeeiptv.Adapters.series_seasons_adapter;
import snsoftware.veegeeiptv.CodeClasses.Variables;
import snsoftware.veegeeiptv.Model.series_info;


public class get_series_info {
    String username,password;
    ProgressDialog pd;
    StringBuilder link;
    Context context;
    String json;
    StringBuffer sb=new StringBuffer();
    ListView channel_list;
    List<series_info.Season> seriesList;
    String series_id;
    public get_series_info(Context context,ListView channel_list, String username, String password,String series_id) {
        this.context = context;
        this.channel_list=channel_list;
        this.username=username;
        this.series_id=series_id;
        this.password=password;
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

                    series_info info = new Gson().fromJson(response, series_info.class);
                    if (info != null) {
                        if (info.seasons != null && info.seasons.size() > 0) {
                            channel_list.setAdapter(new series_seasons_adapter(info.seasons, context, series_id));
                        } else {
                            Toast.makeText(context, "Seasons Not Found", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(context, "Series info not Found", Toast.LENGTH_LONG).show();
                    }

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
