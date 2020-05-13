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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import org.angmarch.views.NiceSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Sign_Up extends AppCompatActivity {
    String Name;
    String password;
    String Email;
    ConstraintLayout f;
    AlertDialog dialog;
    MediaPlayer player1, player2;
    EditText email,pass;
    ProgressBar bar;
    FirebaseAuth auth;
    Button register;
    TextView hello;
    EditText name;
    NiceSpinner spinner;
    String gender;
    String time;
    String IMEINumber;
    TextView text_Problem_signup;
    String ssid;
    String ipAddress;
    private static final int PERMISSION_READ_STATTE = 123;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference22 = db.collection("problem_sign_up");



    //DocumentReference rom = db.collection("user1").document(auth.getCurrentUser().getUid());






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);
        f= findViewById(R.id.constraintsign_up);
        auth = FirebaseAuth.getInstance();
        isNetworkConnected();
        text_Problem_signup = (TextView)findViewById(R.id.text_Problem_signup);
        // خاص  بلفاير بيس كلود

      /*  db = FirebaseFirestore.getInstance();






       Map<String, Object> user = new HashMap<>();
        user.put("name", "ahmed");
        user.put("age",age);
        user.put("email", "@gmail");
        user.put("gender", "male");


// Add a new document with a generated ID
        db.collection("users")

                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                       // Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });

*/








        WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        ssid = wifiInfo.getSSID();
        ipAddress = Formatter.formatIpAddress(ip);













      //  hello = (TextView) findViewById(R.id.Text1);
        name = (EditText) findViewById(R.id.editname_signup);

       // f = (ConstraintLayout) findViewById(R.id.context);
       spinner = (NiceSpinner) findViewById(R.id.spinner);



        email = (EditText) findViewById(R.id.edittextemail_Signup);
        pass = (EditText) findViewById(R.id.editpassword_Signup);
        bar = (ProgressBar) findViewById(R.id.progers_Signup);
        register = (Button) findViewById(R.id.btnSign_Up1);

        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
        time = dateFormat.format(cal1.getTime());



//add array
        String[] itemSpiner_gender = {"Choose gender ", "Male", "Female"};


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Email = email.getText().toString().trim();
                password = pass.getText().toString().trim();
                 Name = name.getText().toString().trim();

                if (Email.isEmpty()) {
                    email.setError("Emal is empty");
                    return;
                } else if (password.isEmpty()) {
                    pass.setError("password is empty");
                    return;
                } else if (Name.isEmpty()) {
                    name.setError("name is Empty");
                    return;
                } else if (password.length() < 6) {
                    pass.setError("Password is not less than 6 characters");
                    return;
                }
                else if (gender==null||gender=="Choose gender"){

                    Toast.makeText(Sign_Up.this, "please enter your gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                else


                    bar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(Sign_Up.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {



                                 /*   .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    });*/











                            Toasty.success(getApplicationContext(), " is successful", 3000, true).show();
                            bar.setVisibility(View.GONE);
                            ok();
                            player1 = MediaPlayer.create(Sign_Up.this, R.raw.open);
                            player1.start();


                        } else {
                            Toasty.error(getApplicationContext(), task.getException() + "", 3000, true).show();
                            bar.setVisibility(View.GONE);
                            player2 = MediaPlayer.create(Sign_Up.this, R.raw.error);
                            player2.start();

                            f.setBackgroundResource(R.color.red);
                        }


                    }
                });


            }
        });


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemSpiner_gender);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position==0) {
                    gender = spinner.getSelectedItem().toString();
                    gender = null;

                    // Toasty.error(f.getContext(), " please Choose gender ", 3000, true).show();
                    return;
                }
                else if (position==1) {

                    gender = spinner.getSelectedItem().toString();

                }
                else if (position==2){
                    gender = spinner.getSelectedItem().toString();
                }

                else Toast.makeText(Sign_Up.this, "akter", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });






        int premchion = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (premchion== PackageManager.PERMISSION_GRANTED){
            myTelephon();
        }else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},PERMISSION_READ_STATTE);
        }

        text_Problem_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =new AlertDialog.Builder(Sign_Up.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_signup_problem,null);
                builder.setTitle("Write your problem and incline").setIcon(R.drawable.c);
                Button send=(Button)view.findViewById(R.id.send_Problem_signup);
                final EditText p1e1=(EditText)view.findViewById(R.id.signup_Problememail);
                final EditText p1e2=(EditText)view.findViewById(R.id.signuptext_Problem);
                Button Cancel_problem_signup=(Button)view.findViewById(R.id.Cancel_problem_signup);
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    String  p=   p1e1.getText().toString();
                      String P=  p1e2.getText().toString();
                        if (p.isEmpty()||P.isEmpty()){
                            p1e1.setError("is empty");
                            p1e2.setError("is empty");

                        }else {
                            model_Problem_login z= new model_Problem_login();
                            z.setIMEIphone(IMEINumber);
                            z.setText_probelm(P);
                            z.setEmail(p);
                            z.setIpAddress(ipAddress);
                            z.setWifiInfo(ssid);
                            z.setTime(time);
                            collectionReference22.add(z);
                            Toasty.info(getApplicationContext(), " جاري ارسال مشكلتك ", 3000, true).show();
                            dialog.dismiss();


                        }

                    }
                });

Cancel_problem_signup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        dialog.dismiss();
    }
});






                builder.setView(view);
                dialog = builder.create();
                dialog.show();
            }
        });







    }

    @SuppressLint("MissingPermission")
    public void myTelephon(){

        TelephonyManager manager =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);



        IMEINumber = manager.getDeviceId();





    }




    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            Snackbar snackbar = Snackbar                 // هنا سوف تظهر لغايت ما ادوس علي الزر
                    .make(f, "لايوجد انترنت ", Snackbar.LENGTH_INDEFINITE)
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
                    .make(f, "نعم يوجد انترنت انت جاهز لاستخدام التطبيق ", Snackbar.LENGTH_LONG);

            snackbar.setActionTextColor(Color.WHITE); // هنا لون النص داخل التوست
            snackbar.show();


            return true;}
    }



















    private void ok (){

        FirebaseUser user=  auth.getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(Sign_Up.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    Toasty.info(getApplicationContext(), "Go to your Email and enter link", 3000, true).show();
                    Map<String, Object> user = new HashMap<>();
                    user.put("Email",Email);
                    user.put("password",password);
                    user.put("time",time);
                    user.put("name", Name);
                    user.put("gender",gender);
                    user.put("ssid",ssid);

                    user.put("ipAddress",ipAddress);
                    user.put("IMEI",IMEINumber);

                    // db.collection("user").document(auth.getCurrentUser().getUid()).set(user)
                    db.collection("oll user").document(auth.getCurrentUser().getUid()).set(user);




                    Intent Activity_page2_ = new Intent(Sign_Up.this,login.class);
                    startActivity(Activity_page2_);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

                    finish();

                }else

                    Toasty.error(getApplicationContext(), task.getException()+"", 3000, true).show();

            }
        });






    }


}






