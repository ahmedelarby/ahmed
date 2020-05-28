package com.example.myapplicationlast;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.WIFI_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment1 extends Fragment {
    AlertDialog dialog ,dilog1;
    String Time_Fragment;
    RecyclerView recyclerView;
    ArrayList<Model_Recycler> idates = new ArrayList<>();
    ShowAdabter adapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("post");
   Button Add_post;
   String get_Name;
    String ssid;
    String ipAddress;
    String IMEINumber;
    String get_Email;
    String get_image_profile;
    String get_gender;
    String time_open;
    StorageReference mStorageRef;
    CollectionReference collectionReference1 = db.collection("oll user_post_pohto");

    String get_Image;
    FirebaseAuth auth  =FirebaseAuth. getInstance();

    DocumentReference rom = db.collection("oll user").document(auth.getCurrentUser().getUid());
    public BlankFragment1() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_blank_fragment1, container, false);

        recyclerView = view.findViewById(R.id.rec3);
        Add_post = view.findViewById(R.id.post);

        adapter = new ShowAdabter(idates,getContext());
        adapter.setOnItemClickListener(new ShowAdabter.OnItemClickListener() {
            @Override
            public void onItemClick() {


            }
        });
        adapter.setOnname(new ShowAdabter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                Toast.makeText(getContext(), "sor", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);




        mStorageRef = FirebaseStorage.getInstance().getReference().child(auth.getCurrentUser().getUid());


//get time

        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
       Time_Fragment = dateFormat.format(cal1.getTime());
//get wifi
        WifiManager wifiManager=(WifiManager)getContext().getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        ssid = wifiInfo.getSSID();
        ipAddress = Formatter.formatIpAddress(ip);






        int premchion = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_PHONE_STATE);
        if (premchion== PackageManager.PERMISSION_GRANTED){



            myTelephon();
        }else {
            int PERMISSION_READ_STATTE=123;
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_PHONE_STATE},PERMISSION_READ_STATTE);
        }
        Add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupMenu popupMenu =new PopupMenu(getContext(),Add_post);
                popupMenu.getMenuInflater().inflate(R.menu.menu_btn_post,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.Addmenupost:
                                Intent p = new Intent(getContext(),post.class);
                                startActivity(p);
                               // post();
                                break;
                            case R.id.Addmenuphoto:
                                Intent open_Stoduo = new Intent(Intent.ACTION_GET_CONTENT);
                                open_Stoduo.setType("image/*");
                                startActivityForResult(Intent.createChooser(open_Stoduo,"اختر مكان الصوره "), 1);

                                break;
                            case R.id.Publishing_policy:
                                Toast.makeText(getContext(), "جاري العمل عليها ", Toast.LENGTH_SHORT).show();
                        }


                        return true;
                    }
                });
                popupMenu.show();




            }
        });












        return view;













    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode== Activity.RESULT_OK&&data!=null&&data.getData()!=null){
            Uri image_post = data.getData();
            StorageReference file = mStorageRef.child("image_Post"+ UUID.randomUUID().toString());
            file.putFile(image_post).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Model_Recycler data1234 = new Model_Recycler();
                            data1234.setName(get_Name);
                            data1234.setSora(get_Image);
                            data1234.setPost_photo(String.valueOf(uri));
                            data1234.setTime(Time_Fragment);
                            data1234.setIpAddress(ipAddress);
                            data1234.setWifiInfo(ssid);
                            data1234.setIMEIphone(IMEINumber);
                            data1234.setEmail(get_Email);
                            data1234.setGender(get_gender);
                            data1234.setImage_profile(get_image_profile);
                            data1234.setTime_open(time_open);


                            collectionReference.add(data1234);




                        }
                    });
                }
            });





        }
    }

    @SuppressLint("MissingPermission")
    public void myTelephon(){


        TelephonyManager manager =(TelephonyManager)getContext().getSystemService(Context.TELEPHONY_SERVICE);



        IMEINumber = manager.getDeviceId();




    }


    public void post(){
        AlertDialog.Builder builder =new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog,null);
        builder.setTitle("add Post").setIcon(R.drawable.c);
        final Button  Cancel = (Button)view.findViewById(R.id.Cancel);
        final EditText ditText111_Dialog =(EditText)view.findViewById(R.id.editText111_Dialog);
        final Button  cereat = (Button)view.findViewById(R.id.cereat);
        cereat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String Post = ditText111_Dialog.getText().toString().trim();
                if (Post.isEmpty()){
                    ditText111_Dialog.setError("isEmpty");
                    return;
                }else {

                    Model_Recycler data123 = new Model_Recycler();
                    data123.setName(get_Name);
                    data123.setWord(Post);
                    data123.setEmail(get_Email);
                    data123.setGender(get_gender);
                    data123.setImage_profile(get_image_profile);
                    data123.setTime_open(time_open);
                    data123.setSora(get_Image);
                    data123.setTime(Time_Fragment);
                    data123.setIpAddress(ipAddress);
                    data123.setWifiInfo(ssid);
                    data123.setIMEIphone(IMEINumber);



                    collectionReference.add(data123);

                    dialog.dismiss();
                }
            }
        });

Cancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        dialog.dismiss();


    }
});








        builder.setView(view);
        dialog = builder.create();
        dialog.show();

    }


    @Override
    public void onStart() {
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







collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

        if (e != null) {
            return;
        }
        idates.clear();
        for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots){
            Model_Recycler dater1 = querySnapshot.toObject(Model_Recycler.class);
            idates.add(dater1);
        }
        adapter.notifyDataSetChanged();










    }
});













    }


}
