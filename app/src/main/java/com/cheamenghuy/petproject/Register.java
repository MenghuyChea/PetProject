package com.cheamenghuy.petproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class Register extends AppCompatActivity {
    EditText name_reg,email_reg,pw_reg,phone_reg;
    Button btn_reg_reg;
    ProgressBar loading;
    static String URL_REGIST = "http://192.168.200.87:8000/api/register";
    TextView btn_login_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name_reg = findViewById(R.id.name_reg);
        email_reg = findViewById(R.id.email_reg);
        pw_reg = findViewById(R.id.pw_reg);
        phone_reg = findViewById(R.id.phone_reg);
        btn_reg_reg = findViewById(R.id.btn_reg_reg);
        btn_login_reg = findViewById(R.id.btn_login_reg);

        btn_reg_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        btn_login_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });
    }
    public void insert()
    {
        try {
            String name = name_reg.getText().toString();
            String email = email_reg.getText().toString();
            String pass = pw_reg.getText().toString();
            String phone = phone_reg.getText().toString();
            if(name.equals("") || email.equals("") || pass.equals("") || phone.equals("") )
            {
                Toast.makeText(this,"Please fill all the information",Toast.LENGTH_LONG).show();
            }else {
                SQLiteDatabase db = openOrCreateDatabase("pet", Context.MODE_PRIVATE, null);
                db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR,email VARCHAR,password VARCHAR,phone_number VARCHAR)");
                String sql = "insert into user(username,email,password,phone_number)values(?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                statement.bindString(1, name);
                statement.bindString(2, email);
                statement.bindString(3, pass);
                statement.bindString(4, phone);
                statement.execute();

                Register();

                name_reg.setText("");
                email_reg.setText("");
                pw_reg.setText("");
                phone_reg.setText("");
                name_reg.requestFocus();

                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }

        }catch (Exception ex)
        {
            Toast.makeText(this,"Record Fail",Toast.LENGTH_LONG).show();
        }
    }
    public void Register(){
        final String name = name_reg.getText().toString();
        final String email = email_reg.getText().toString();
        final String pass = pw_reg.getText().toString();
        final String phone = phone_reg.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    Log.e("JSON_DATA",response);
                    if(success.equals("1"))
                    {
                        Intent intent = new Intent(Register.this,Login.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"Please fill all the information",Toast.LENGTH_LONG).show();
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
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",name.trim());
                params.put("email",email.trim());
                params.put("password",pass.trim());
                params.put("phonenumber",phone.trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
