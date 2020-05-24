package com.example.myapplicationlast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class login extends AppCompatActivity {
    private static final int PERMISSION_READ_STATTE = 123;
    EditText  Email;
EditText password;
Button  login;
Button  Regster;
 AlertDialog dialog ,dialog1,dialog_updet_email;
 TextView problem;
TextView I_forgot_the_password;
TextView Use_policy_and_privacy;
ProgressBar progressBar;
 FirebaseAuth mAuth;
    String email;
    String pass;
    String ssid;
    String ipAddress;
    String time;
    String IMEINumber;
    Animation animFadeIn;
    ConstraintLayout consterntlogin;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    CollectionReference collectionReference = db.collection("Login");
    CollectionReference collectionReference_p = db.collection("Login_Problem");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        consterntlogin= findViewById(R.id.constraintsign_up);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        isNetworkConnected();
        problem =(TextView) findViewById(R.id.t_pProblem);
        Email = findViewById(R.id.edittextemaillogin);
        password = findViewById(R.id.editpasswordlogin);
        login = findViewById(R.id.btnlogin);
        Regster = findViewById(R.id.btnSign_Up);
        I_forgot_the_password = findViewById(R.id.I_forgot_the_password);
        Use_policy_and_privacy = findViewById(R.id.Use_policy_and_privacy);
        progressBar = findViewById(R.id.progressBarlogin);
        mAuth = FirebaseAuth.getInstance();
       // get wifi_Info && and get_ipAdderss
        WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
         ssid = wifiInfo.getSSID();
         ipAddress = Formatter.formatIpAddress(ip);
    //get Time
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
        time = dateFormat.format(cal1.getTime());
        problem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 =new AlertDialog.Builder(login.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_login_problem,null);
                builder1.setTitle("Write your problem and your Email").setIcon(R.drawable.c);
                Button send = (Button) view.findViewById(R.id.send_Problem);
                final EditText email_Problem=(EditText) view.findViewById(R.id.editTextdiloge_Problememail);
                final EditText text_Problem=(EditText) view.findViewById(R.id.editTextdiloge_Problem);
                Button cancel= (Button) view.findViewById(R.id.Cancel_problem);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String p1=   email_Problem.getText().toString();
                        String p2=  text_Problem.getText().toString();
                        if (p1.isEmpty()||p2.isEmpty()){
                            email_Problem.setError("is empty");
                            text_Problem.setError("is empty");

                        }else {
                            model_Problem_login problemLogin = new model_Problem_login();
                            problemLogin.setEmail(p1);
                            problemLogin.setText_probelm(p2);
                    problemLogin.setIMEIphone(IMEINumber);
                    problemLogin.setIpAddress(ipAddress);
                    problemLogin.setWifiInfo(ssid);
                    problemLogin.setTime(time);
                            collectionReference_p.add(problemLogin);

                            Toasty.info(getApplicationContext(), " جاري ارسال مشكلتك", 3000, true).show();
                            dialog1.dismiss();
                        }




                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog1.dismiss();
                    }
                });







                builder1.setView(view);
                dialog1 = builder1.create();
                dialog1.show();










            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }
        });

        Regster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regster.startAnimation(animFadeIn);
            Intent Reg = new Intent(login.this,Sign_Up.class);
            startActivity(Reg);
            finish();

         overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });


        I_forgot_the_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(login.this);
                View view = getLayoutInflater().inflate(R.layout.dilog_password_update,null);
                builder.setTitle("update_password").setIcon(R.drawable.c);

                EditText emailUpdet = (EditText) view.findViewById(R.id.edittextemail_updet1);
                Button  up_em1 =(Button) view.findViewById(R.id.up_em1);
                up_em1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String up1= emailUpdet.getText().toString().trim();
                        if (up1.isEmpty()){

                            emailUpdet.setError("انه فارغ ");
                            return;
                        } else
                            auth.sendPasswordResetEmail(up1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful())
                                        Toasty.info(getApplicationContext(), "Go to your Email and enter link", 9000, true).show();



                                    else {

                                        Toasty.error(getApplicationContext(), ""+task.getException().getMessage(), 9000, true).show();
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





        Use_policy_and_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =new AlertDialog.Builder(login.this);
                View view = getLayoutInflater().inflate(R.layout.dialig_informtion,null);
                builder.setTitle("Program use policy").setIcon(R.drawable.c);









                builder.setView(view);
                dialog = builder.create();
                dialog.show();





            }
        });















int premchion = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
if (premchion== PackageManager.PERMISSION_GRANTED){
    myTelephon();
}else {
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.READ_PHONE_STATE},PERMISSION_READ_STATTE);
}














    }

    @SuppressLint("MissingPermission")
    public void myTelephon(){

        TelephonyManager manager =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);



        IMEINumber = manager.getDeviceId();





    }



















    public void login(){
       email= Email.getText().toString().trim();
        pass= password.getText().toString().trim();
        if (email.isEmpty()){ Email.setError("Email is Empty");;return;}
        else if (pass.isEmpty()){password.setError("password is Empty");return;}
        else if (pass.length()<6){password.setError("Password is not less than 6 characters ");return;}
        else
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){


                        cheak();
                        progressBar.setVisibility(View.GONE);
                    }else {
                        progressBar.setVisibility(View.GONE);
                        MediaPlayer w = MediaPlayer.create(login.this,R.raw.error);
                        w.start();
                        Toasty.error(getApplicationContext(), task.getException()+"", 9000, true).show();

                    }
                }
            });





    }
    private void cheak(){
        FirebaseUser used= mAuth .getCurrentUser();
        if (used.isEmailVerified()){

            MediaPlayer t = MediaPlayer.create(login.this,R.raw.open);
            t.start();
            Toasty.success(getApplicationContext(), " Sucsseful", 3000, true).show();
            Toasty.success(getApplicationContext(), "Verified", 3000, true).show();
            Data_login model=new Data_login();
            model.setEmail(email);
            model.setPassword(pass);
            model.setIpAddress(ipAddress);
            model.setWifiInfo(ssid);
            model.setTime(time);
            model.setIMEIphone(IMEINumber);

            collectionReference.add(model);


            Intent Activity_page4 = new Intent(login.this,MainActivity.class);
            startActivity(Activity_page4);
            login.startAnimation(animFadeIn);
            //add Animation
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            finish();
        }else {
            Toasty.info(getApplicationContext(), "Go to your Email and enter link", 9000, true).show();
            MediaPlayer s = MediaPlayer.create(login.this,R.raw.error);
            s.start();

            progressBar.setVisibility(View.GONE);
        }
    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            Snackbar snackbar = Snackbar                 // هنا سوف تظهر لغايت ما ادوس علي الزر
                    .make(consterntlogin, "لايوجد انترنت ", Snackbar.LENGTH_INDEFINITE)
                    .setAction("اعادت المحاوله  ", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            isNetworkConnected();
                            isNetworkConnected();
// هنا تكتب الامر بعد الضغط على الزر دااخل التوست
                        }
                    });
            snackbar.setActionTextColor(Color.WHITE); // هنا لون النص داخل التوست
            snackbar.show();

            return false;
        }else {

            Snackbar snackbar = Snackbar                 // هنا سوف تظهر لغايت ما ادوس علي الزر
                    .make(consterntlogin, "نعم يوجد انترنت انت جاهز لاستخدام التطبيق ", Snackbar.LENGTH_LONG);

            snackbar.setActionTextColor(Color.WHITE); // هنا لون النص داخل التوست
            snackbar.show();


            return true;}
    }









    @Override
    protected void onStart() {
        super.onStart();

       if (user!=null){
           if (user.isEmailVerified()){
               Intent login= new Intent(this,MainActivity.class);
               startActivity(login);
                finish();

           }else {
              // Toast.makeText(this, "غير مسموح لك بلدخول حتي تفعل الاميل", Toast.LENGTH_SHORT).show();
           }





       }






















    }
}
