package com.example.myapplicationlast;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
    Button bold,naher,nobold,plas,mins,line,noline,start,end,center,elgaa,clare;
    String bold1;
    String line1;
    String grvaty=null;
    int siz = 20;
    Spinner spiner;
    Spinner spiner1;
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
    String colortext;
    String colorback;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth  =FirebaseAuth. getInstance();

    DocumentReference rom = db.collection("oll user").document(auth.getCurrentUser().getUid());
    CollectionReference collectionReference = db.collection("post");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        noline=findViewById(R.id.noline);
        center= findViewById(R.id.centertext);
        elgaa= findViewById(R.id.elgaa);
        clare = findViewById(R.id.caler);
        bold = findViewById(R.id.bold);
        nobold = findViewById(R.id.nobold);
        plas = findViewById(R.id.plas);
        mins = findViewById(R.id.mins);
        line = findViewById(R.id.Line);
        start = findViewById(R.id.starttext);
        end = findViewById(R.id.endtext);
        spiner=findViewById(R.id.spiner);
        spiner1=findViewById(R.id.spiner1);
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











        String []date_spinner={"white","red","Aqua","blue","black","green","yellow","azure","silver","orange","purple","pea"};
        String []date_spinner1={"black","red","Aqua","blue","white","green","yellow","azure","silver","orange","purple","pea"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, date_spinner1);
        spiner1.setAdapter(adapter1);
        spiner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colortext = spiner1.getSelectedItem().toString();

                if (position==0){
                    editTextpo.setTextColor(getResources().getColor(R.color.black));


            }else if (position==1){
                editTextpo.setTextColor(getResources().getColor(R.color.red));}
            else if (position==2){
                editTextpo.setTextColor(getResources().getColor(R.color.Aqua));
                   // Toast.makeText(post.this, ""+colortext, Toast.LENGTH_SHORT).show();
            }
            else if (position==3){editTextpo.setTextColor(getResources().getColor(R.color.blue));}
            else if (position==4){editTextpo.setTextColor(getResources().getColor(R.color.white));}
            else if (position==5){editTextpo.setTextColor(getResources().getColor(R.color.green));}
            else if (position==6){editTextpo.setTextColor(getResources().getColor(R.color.yellow));}
            else if (position==7){editTextpo.setTextColor(getResources().getColor(R.color.azure));}
            else if (position==8){editTextpo.setTextColor(getResources().getColor(R.color.silver));}
            else if (position==9){editTextpo.setTextColor(getResources().getColor(R.color.orange));}
            else if (position==10){editTextpo.setTextColor(getResources().getColor(R.color.purple));}
            else if (position==11){editTextpo.setTextColor(getResources().getColor(R.color.pea));}
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, date_spinner);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                colorback=spiner.getSelectedItem().toString();
                if (position==0){
                    editTextpo.setBackgroundResource(R.color.white);


                }else if (position==1){
                    editTextpo.setBackgroundResource(R.color.red);}
                else if (position==2){editTextpo.setBackgroundResource(R.color.Aqua);}
                else if (position==3){editTextpo.setBackgroundResource(R.color.blue);}
                else if (position==4){editTextpo.setBackgroundResource(R.color.black);}
                else if (position==5){editTextpo.setBackgroundResource(R.color.green);}
                else if (position==6){editTextpo.setBackgroundResource(R.color.yellow);}
                else if (position==7){editTextpo.setBackgroundResource(R.color.azure);}
                else if (position==8){editTextpo.setBackgroundResource(R.color.silver);}
                else if (position==9){editTextpo.setBackgroundResource(R.color.orange);}
                else if (position==10){editTextpo.setBackgroundResource(R.color.purple);}
                else if (position==11){editTextpo.setBackgroundResource(R.color.pea);}




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                   /* data123.setSize(String.valueOf(siz));
                    data123.setGravty(grvaty);
                    data123.setBold(bold1);
                    data123.setLine(line1);
                    data123.setColortext(colortext);
                    data123.setColorbackground(colorback);*/


                    collectionReference.add(data123);













                }


            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setGravity(Gravity.CENTER);
                grvaty="center";
            }
        });

        clare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setText("");
                siz=20;
                editTextpo.setGravity(Gravity.CENTER);
                editTextpo.setBackgroundResource(R.color.white);
                editTextpo.setTextColor(getResources().getColor(R.color.black));
                editTextpo.setTypeface(Typeface.DEFAULT);
                editTextpo.setPaintFlags(Paint.LINEAR_TEXT_FLAG);

            }
        });

        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setTypeface(Typeface.DEFAULT_BOLD);
                bold1="bold";
            }
        });
        nobold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setTypeface(Typeface.DEFAULT);
                bold1="nobold";





            }
        });


        plas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siz+=1;
                editTextpo.setTextSize(siz);
            }
        });

        mins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siz-=1;
                editTextpo.setTextSize(siz);
            }
        });


        line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
                line1="line";
            }
        });
        noline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setPaintFlags(Paint.LINEAR_TEXT_FLAG);
                line1="notline";
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setGravity(Gravity.START);
                grvaty="start";


            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextpo.setGravity(Gravity.END);
                grvaty="end";
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
                }


            }
        });

















    }
}
