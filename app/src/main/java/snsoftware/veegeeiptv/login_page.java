package snsoftware.veegeeiptv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import snsoftware.veegeeiptv.Network_Operations.login_task;


public class login_page extends AppCompatActivity {
    EditText email,password;
    Button btn,sign_up;
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024,1024);
        setContentView(R.layout.login_layout);
        email=findViewById(R.id.email_txt);
        //sign_up=findViewById(R.id.sign_up);
        password=findViewById(R.id.password_txt);
        btn=findViewById(R.id.btn);
        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getString("username","").equals("")&&!prefs.getString("password","").equals("")){
            Intent i=new Intent(login_page.this,MainActivity.class);
            i.putExtra("username",prefs.getString("username",""));
            i.putExtra("password",prefs.getString("password",""));
            startActivity(i);
            finish();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()){
                    email.setError("Username is Required");
                }else if(password.getText().toString().isEmpty()){
                    password.setError("Password is Required");
                }else{
                   new login_task(login_page.this,email.getText().toString(),password.getText().toString()).Call_Api();
                }
            }
        });

        }

}
