package com.example.login_navview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivitySingUp extends AppCompatActivity {

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sing_up);

        btnLogin=findViewById(R.id.btnSesion);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register= new Intent(MainActivitySingUp.this, MainActivity.class);//Que se valla a la de registrar.
                startActivity(register);
            }
        });
        //Guardar nuevo Usuario
        AlertDialog.Builder sms=new AlertDialog.Builder(this);
        sms.setMessage("Usuario registrado correctamente...!");



    }
}