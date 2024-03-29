package com.example.login_navview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://my-json-server.typicode.com/Carlous101/users/data?user=";
    RequestQueue rqueue;
    EditText usr, pass;
    Button login, btn;
    String full_name;
    String img;
    String rol;
    String mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rqueue = Volley.newRequestQueue(this);
        usr = (EditText)findViewById(R.id.txtUser);
        pass = (EditText)findViewById(R.id.txtpass);
        login = (Button)findViewById(R.id.btnLogIn);

        btn=findViewById(R.id.btnRegister);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register= new Intent(MainActivity.this, MainActivitySingUp.class);//Que se valla a la de registrar.
                startActivity(register);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                requestJSON(usr.getText().toString().trim(), pass.getText().toString().trim());
            }
        });
    }

    private void requestJSON(String user, String password)
    {
        Intent in = new Intent(this, NavDrawOptions.class);
       JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
               Request.Method.GET,
               url + user + "&pass=" + password,
               null,
               new Response.Listener<JSONArray>() {
                   @Override
                   public void onResponse(JSONArray response) {
                       if(response.length() <= 0)
                       {
                           Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                       }else
                       {
                           int size = response.length();
                           for (int i = 0; i<size; i++)
                           {
                               try {
                                   JSONObject jsObject = new JSONObject(response.get(i).toString());
                                   full_name = jsObject.getString("full_name");
                                   img = jsObject.getString("img");
                                   rol = jsObject.getString("rol");
                                   mail = jsObject.getString("mail");
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           in.putExtra("full_name", full_name);
                           in.putExtra("img", img);
                           in.putExtra("rol", rol);
                           in.putExtra("mail", mail);

                           startActivity(in);
                       }
                   }
               },
               new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                   }
               }
       );
       rqueue.add(jsonArrayRequest);
    }
}