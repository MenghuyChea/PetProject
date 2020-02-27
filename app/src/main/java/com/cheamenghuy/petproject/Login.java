package com.cheamenghuy.petproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    EditText name_login,pw_login;
    Button login_login;
    TextView reg_login;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name_login = findViewById(R.id.name_login);
        pw_login = findViewById(R.id.pw_login);
        login_login = findViewById(R.id.btn_login_login);
        reg_login = findViewById(R.id.btn_reg_login);

        login_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });
    }

    public void login() {
        String username = name_login.getText().toString();
        String password = pw_login.getText().toString();

        if(username.equals("") || password.equals(""))
        {
            Toast.makeText(this,"Username or Password Blank",Toast.LENGTH_LONG).show();
        }else if(null!=checkUser(username,password))
        {
            String userDb = checkUser(username,password);
            Intent intent = new Intent(Login.this,MainApp.class);
            intent.putExtra("uname",userDb);
            startActivity(intent);
        }
        else
        {
//            Toast.makeText(this,"Username or Password not match",Toast.LENGTH_LONG).show();
//            name_login.setText("");
//            pw_login.setText("");
//            name_login.requestFocus();
            Login1();
        }
    }

    public String checkUser(String username, String password)
    {
        SQLiteDatabase db = openOrCreateDatabase("pet", Context.MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select id,username,password from user where username = ? and password = ? ",new String[]{username,password});
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            String user = cursor.getString(1);
            String pass = cursor.getString(2);
            SharedPreferences.Editor sp = getSharedPreferences("user",MODE_PRIVATE).edit();
            sp.putString("uname",user);
            sp.apply();
            cursor.close();
            return  user;
        }
        return null;
    }
    public void Login1(){
        String url = "http://172.23.0.101/petproject1/login.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1"))
                    {
                        Intent intent = new Intent(Login.this,MainApp.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Your Username or Password is wrong",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error: "+error.toString(),Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("name",name_login.getText().toString().trim());
                params.put("password",pw_login.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
