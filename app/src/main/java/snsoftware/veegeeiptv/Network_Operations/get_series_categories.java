package snsoftware.veegeeiptv.Network_Operations;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
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

import snsoftware.veegeeiptv.Adapters.series_category_adapter;
import snsoftware.veegeeiptv.CodeClasses.Variables;
import snsoftware.veegeeiptv.Model.category;


public class get_series_categories {
    String username,password;
    ProgressDialog pd;
    StringBuilder link;
    Context context;
    String json;
    StringBuffer sb=new StringBuffer();
    ListView category_list;
    List<category> categoryList;
    ProgressBar pbar;
    public get_series_categories(Context context, ListView category_list, String username, String password, ProgressBar progressBar) {
        this.context = context;
        this.category_list=category_list;
        this.username=username;
        this.password=password;
        pbar=progressBar;
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        link=new StringBuilder();
        link.append(Variables.domain+"username=");
        link.append(username);
        link.append("&password=");
        link.append(password);
        link.append("&action=get_series_categories");
    }





    public void Call_Api(){

        Log.d("resp",link.toString());


        pbar.setVisibility(View.VISIBLE);

        StringRequest postRequest = new StringRequest(Request.Method.GET,
                link.toString(), new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                Log.d("resp",response);
                pbar.setVisibility(View.GONE);

                if(!response.equals(Variables.errormessage)) {

                    categoryList = new Gson().fromJson(response, new TypeToken<List<category>>() {
                    }.getType());
                    if (categoryList != null && categoryList.size() > 0) {
                        category_list.setAdapter(new series_category_adapter(categoryList, context));
                    } else {
                        Toast.makeText(context, "Unable to fetch categories", Toast.LENGTH_LONG).show();
                    }

                }

            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        pbar.setVisibility(View.GONE);
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
