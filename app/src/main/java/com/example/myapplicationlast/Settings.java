package com.example.myapplicationlast;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import es.dmoral.toasty.Toasty;

public class Settings extends AppCompatActivity {
Button Email_update;
AlertDialog dialog_updet_email;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user1 = auth.getCurrentUser();
  //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


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
                     String up= emailUpdet.getText().toString().trim();
                        if (up.isEmpty()|| Patterns.EMAIL_ADDRESS.matcher(up).matches());
                        {
                            emailUpdet.setError("يجب ان يكون الاميل المدخل اميل حقيقي ");

                        } user1.updateEmail(up).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful())

                                { Toast.makeText(Settings.this, "isSuccessful", Toast.LENGTH_SHORT).show();
                                    check_email_updet();}
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
    }

    public void check_email_updet(){

        user1.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toasty.info(getApplicationContext(), "Go to your Email and enter link", 9000, true).show();


                        }
                    }
                });





    }
}
