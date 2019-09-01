package snsoftware.veegeeiptv.Network_Operations;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import snsoftware.veegeeiptv.CodeClasses.Variables;
import snsoftware.veegeeiptv.MainActivity;
import snsoftware.veegeeiptv.Model.user_account_detail;


public class login_task{
    ProgressDialog pd;
    StringBuilder link;
    Context context;
    String username,password;
    String json;
    StringBuffer sb;
    SharedPreferences prefs;
    user_account_detail detail;
    public login_task(Context context, String username, String password) {
        this.context = context;
        this.username=username;
        this.password=password;
        prefs=PreferenceManager.getDefaultSharedPreferences(context);
        pd=new ProgressDialog(context);
        pd.setMessage("Please Wait...");
        pd.setCancelable(true);
        sb=new StringBuffer();
        link=new StringBuilder();
        link.append(Variables.domain+"username=");
        link.append(username);
        link.append("&password=");
        link.append(password);
    }


    public void Call_Api(){
        pd.show();
        Log.d("resp",link.toString());

        RequestQueue rq = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, link.toString(), null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String respo=response.toString();
                        Log.d("responce",respo);

                        pd.cancel();

                        detail=new Gson().fromJson(respo.toString(),user_account_detail.class);

                        if(!respo.toString().equals(Variables.errormessage)){
                            Toast.makeText(context,"Login Sucess",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(context, MainActivity.class);
                            i.putExtra("username",username);
                            i.putExtra("password",password);
                            prefs.edit().putString("username",username).apply();
                            prefs.edit().putString("password",password).apply();
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }else{
                            Toast.makeText(context,"Provide Vaild Username and Password",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                        pd.cancel();
                        Log.d("respo",error.toString());
                    }
                });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rq.getCache().clear();
        rq.add(jsonObjectRequest);

    }

}
