package com.example.mobile_candidate;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class LoginActivity extends AppCompatActivity {

    private EditText  passEdt;
    private Button loginBtn;
    private TextView userEdt; //passEdt;
    private ImageView imgvPersona;
    boolean passwordVisible;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        initView();
        setVariable();
    }

    private void setVariable(){

        Intent intent = getIntent();
        String password = intent.getExtras().getString("password");

        passEdt.setText(password);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userEdt.getText().toString().equals(userEdt.getText().toString()) && passEdt.getText().toString().equals(password)){

                    Intent intent = getIntent();
                    String name = intent.getExtras().getString("name");
                    String phone = intent.getExtras().getString("phone");
                    String cell = intent.getExtras().getString("cell");
                    String location = intent.getExtras().getString("location");
                    String picture = intent.getExtras().getString("picture");
                    String latitud = intent.getExtras().getString("latitud");
                    String longitud = intent.getExtras().getString("longitud");

                    Intent intent1 = new Intent(getApplicationContext(), InfoCompleta.class);
                    DatosObj datosObj = new DatosObj(name , phone, cell, location, picture, latitud, longitud);
                    intent1.putExtra("datosObj", datosObj);
                    startActivity(intent1);
                }else {
                    Toast.makeText(LoginActivity.this, "La contraseña no es correcta", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(){
        userEdt = findViewById(R.id.edtUser);
        passEdt = findViewById(R.id.edtPassword);
        loginBtn = findViewById(R.id.btnLogin);

        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
        String password = intent.getExtras().getString("password");

        userEdt.setText(username);
        passEdt.setText(password);

        passEdt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX()>= passEdt.getRight()-passEdt.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = passEdt.getSelectionEnd();
                        if(passwordVisible){
                            // set drawable image here
                            passEdt.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24, 0);
                            // for hide password
                            passEdt.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            // set drawable image here
                            passEdt.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24, 0);
                            // for show password
                            passEdt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        passEdt.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });

    }


}
