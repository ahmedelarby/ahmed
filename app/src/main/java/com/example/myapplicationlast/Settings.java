package com.example.myapplicationlast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class Settings extends AppCompatActivity {
Button Email_update;
 Button pass_update;
AlertDialog dialog_updet_email;
AlertDialog dialog_updet_password;
 AlertDialog dialog_updet_name;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user1 = auth.getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String up;
    String pas;
    Button chenge_name;
    DocumentReference romupdet = db.collection("oll user").document(auth.getCurrentUser().getUid());
    DocumentReference rom1415 = db.collection("oll user").document(auth.getCurrentUser().getUid());

    //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    AdView adView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    MobileAds.initialize(this,"ca-app-pub-8403267465820162~2686166767");

        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);









        chenge_name =findViewById(R.id.chenge_name);
        pass_update= findViewById(R.id.pass_update);
        Email_update = findViewById(R.id.Email_update);
        Email_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(Settings.this);
                View view = getLayoutInflater().inflate(R.layout.dilog_email_update,null);
                builder.setTitle("Email_update").setIcon(R.drawable.c);
                EditText emailUpdet = (EditText) view.findViewById(R.id.edittextemail_updet);
                Button  up_em =(Button) view.findViewById(R.id.up_em);
                up_em.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      up= emailUpdet.getText().toString().trim();
                        if (up.isEmpty())
                        {
                            emailUpdet.setError("انه فارغ ");
                            return;
                        }
                        else
                        user1.updateEmail(up).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())

                                { Toast.makeText(Settings.this, "isSuccessful", Toast.LENGTH_SHORT).show();
                                    check_email_updet();

                                }
                                else{
                                    Toast.makeText(Settings.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });



                        dialog_updet_email.dismiss();



                    }
                });








                builder.setView(view);
                dialog_updet_email = builder.create();
                dialog_updet_email.show();


            }
        });

        pass_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =new AlertDialog.Builder(Settings.this);
                View view = getLayoutInflater().inflate(R.layout.dilog_password_update_on_email,null);
                builder.setTitle("Email_update").setIcon(R.drawable.c);
                EditText passUpdet = (EditText) view.findViewById(R.id.edittextpassword_updet1);
                Button  up_pass =(Button) view.findViewById(R.id.up_pass);
                up_pass.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    pas= passUpdet.getText().toString();
                        if (pas.isEmpty()||pas.length()<6){
                            passUpdet.setError("Password is not less than 6 characters");
                            return;

                        }else
                        user1.updatePassword(pas)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {

                                            HashMap<String,String> map = new HashMap<>();

                                            map.put("password",pas);



                                            romupdet.set(map, SetOptions.merge());


                                            FirebaseAuth.getInstance().signOut();
                                            Intent out = new Intent(Settings.this,login.class);
                                            startActivity(out);
                                            finish();
                                        }
                                    }
                                });















                        dialog_updet_password.dismiss();


                    }
                });


                builder.setView(view);
                dialog_updet_password = builder.create();
                dialog_updet_password.show();

            }
        });




        chenge_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =new AlertDialog.Builder(Settings.this);
                View view = getLayoutInflater().inflate(R.layout.dilog_name_update,null);
                builder.setTitle("name_update").setIcon(R.drawable.c);
                Button name =view.findViewById(R.id.up_name);
                EditText edittextname_updet1 = view.findViewById(R.id.updet_name1);
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    String n111= edittextname_updet1.getText().toString().trim();
                        if (n111.isEmpty()){
                            edittextname_updet1.setError("is Empty");
                            return;
                        }else {

                            HashMap<String,String> name111 = new HashMap<>();
                            name111.put("name",n111);

                            romupdet.set(name111,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(Settings.this, "isSuccessful updet name", Toast.LENGTH_SHORT).show();


                                    }else {
                                        Toast.makeText(Settings.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });



                        }
                        dialog_updet_name.dismiss();
                    }
                });



                builder.setView(view);
                dialog_updet_name = builder.create();
                dialog_updet_name.show();




            }
        });























    }

    public void check_email_updet(){

        user1.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {







                            Toasty.info(getApplicationContext(), "Go to your Email and enter link", 9000, true).show();
                            HashMap<String,String> map = new HashMap<>();

                            map.put("Email",up);



                            romupdet.set(map, SetOptions.merge());



                            FirebaseAuth.getInstance().signOut();

                            Intent su = new Intent(Settings.this,login.class);
                            startActivity(su);
                            finish();
                        }
                    }
                });





    }


    @Override
    protected void onStart() {
        super.onStart();

        HashMap<String,String> map1415 = new HashMap<>();
        map1415.put("online","on");
        rom1415.set(map1415, SetOptions.merge());



    }
}
