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
import com.google.gson.reflect.TypeToken;

import java.util.List;

import snsoftware.veegeeiptv.Adapters.series_list_adapter;
import snsoftware.veegeeiptv.CodeClasses.Variables;
import snsoftware.veegeeiptv.Model.series;


public class get_series_by_category  {
    String username,password;
    ProgressDialog pd;
    StringBuilder link;
    Context context;
    String json;
    StringBuffer sb=new StringBuffer();
    ListView channel_list;
    List<series> seriesList;
    String category_id;
    public get_series_by_category(Context context,ListView channel_list, String username, String password,String category_id) {
        this.context = context;
        this.channel_list=channel_list;
        this.username=username;
        this.category_id=category_id;
        this.password=password;
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        link=new StringBuilder();
        link.append(Variables.domain+"username=");
        link.append(username);
        link.append("&password=");
        link.append(password);
        link.append("&action=get_series&category_id=");
        link.append(category_id);
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

                    seriesList = new Gson().fromJson(response, new TypeToken<List<series>>() {
                    }.getType());
                    if (seriesList != null && seriesList.size() > 0) {
                        channel_list.setAdapter(new series_list_adapter(seriesList, context));
                    } else {
                        Toast.makeText(context, "Series Not found", Toast.LENGTH_LONG).show();
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
