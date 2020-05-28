package com.example.myapplicationlast;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Show_Profile extends AppCompatActivity {
    TextView name_profil;
    ImageView photo_profile;
    ImageView Image_Profil_back;
    TextView Email_profil;
    TextView gender_profil;
    TextView namber_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);

        name_profil= findViewById(R.id.name_profil);
        photo_profile= findViewById(R.id.photo_profile);
        Image_Profil_back= findViewById(R.id.Image_Profil_back);
        Email_profil =findViewById(R.id.Email_profil);
        gender_profil=findViewById(R.id.gender_profil);
        namber_phone =findViewById(R.id.namber_phone);
        Intent intent =getIntent();
        String name =intent.getStringExtra("name");
        String sora =intent.getStringExtra("image");
        String gender1=intent.getStringExtra("gender");
        String time_open=intent.getStringExtra("timeopen");
        String Email=intent.getStringExtra("email");
        String background=intent.getStringExtra("background");
        Email_profil.setText(Email  );
        gender_profil.setText(gender1);
        namber_phone.setText(time_open);
        name_profil.setText(name);
        if (sora!=null){
        Picasso.with(getApplicationContext())
                .load(sora).fit()
                .centerCrop()
                .into(photo_profile);}
        else {photo_profile.setImageResource(R.drawable.user);}
            if (background!=null){
        Picasso.with(getApplicationContext())
                .load(background).fit().centerCrop()
                .into(Image_Profil_back);}
            else {Image_Profil_back.setImageResource(R.drawable.b);}

















    }




























}
