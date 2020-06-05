package com.example.myapplicationlast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class post extends AppCompatActivity {
    EditText editTextpo;
    Button naher,elgaa,clare;



    String Time_Fragment;
    String ssid;
    String ipAddress;
    String IMEINumber;
    String get_Email;
    String get_image_profile;
    String get_gender;
    String time_open;
    String get_Name;
    String get_Image;
    String onlion;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth  =FirebaseAuth. getInstance();

    DocumentReference rom = db.collection("oll user").document(auth.getCurrentUser().getUid());
    CollectionReference collectionReference = db.collection("post");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        elgaa= findViewById(R.id.elgaa);
        clare = findViewById(R.id.celar);

        editTextpo = findViewById(R.id.posttext);
        naher = findViewById(R.id.naher);
      //  colortext=spiner1.getSelectedItem().toString();
     //   colorback=spiner.getSelectedItem().toString();
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
        Time_Fragment = dateFormat.format(cal1.getTime());

        //get wifi
        WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        ssid = wifiInfo.getSSID();
        ipAddress = Formatter.formatIpAddress(ip);


        int premchion = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE);
        if (premchion== PackageManager.PERMISSION_GRANTED){



            myTelephon();
        }else {
            int PERMISSION_READ_STATTE = 123;
            ActivityCompat.requestPermissions(post.this,
                    new String[]{Manifest.permission.READ_PHONE_STATE}, PERMISSION_READ_STATTE);

        }



        naher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posttext1= editTextpo.getText().toString();
                if (posttext1.isEmpty())
                {editTextpo.setError("is empty");return;}
                else if (posttext1.length()>970)
                {editTextpo.setError("الكلمات حجمها كبير جداا ");return;}
                else {


                    Model_Recycler data123 = new Model_Recycler();
                    data123.setName(get_Name);
                    data123.setWord(posttext1);
                    data123.setEmail(get_Email);
                    data123.setGender(get_gender);
                    data123.setImage_profile(get_image_profile);
                    data123.setTime_open(time_open);
                    data123.setSora(get_Image);
                    data123.setTime(Time_Fragment);
                    data123.setIpAddress(ipAddress);
                    data123.setWifiInfo(ssid);
                    data123.setIMEIphone(IMEINumber);
                    data123.setOnline(onlion);



                    collectionReference.add(data123);
                    Toast.makeText(post.this, "تم نشر المنشور", Toast.LENGTH_SHORT).show();
                    Intent back=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(back);
                    finish();










                }


            }
        });

        elgaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back1=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(back1);
                finish();
            }
        });

        clare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setText("");


            }
        });






































































    }


        @SuppressLint("MissingPermission")
        public void myTelephon(){


            TelephonyManager manager =(TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);



            IMEINumber = manager.getDeviceId();




        }


    @Override
    protected void onStart() {
        super.onStart();






        rom.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                if (e!=null){
                    // Toast.makeText(page4_list.this, "Erorr", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()) {

                    get_Name = documentSnapshot.getString("name");
                    get_Email = documentSnapshot.getString("Email");
                    time_open = documentSnapshot.getString("time_open");
                    get_image_profile = documentSnapshot.getString("image_profile");
                    get_gender = documentSnapshot.getString("gender");
                    get_Image = documentSnapshot.getString("image");
                    onlion = documentSnapshot.getString("online");
                }


            }
        });

















    }
}
