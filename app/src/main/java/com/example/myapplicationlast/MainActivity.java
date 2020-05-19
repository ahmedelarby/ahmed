package com.example.myapplicationlast;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager pager ;
    TabLayout layout;
    TabItem first ,scand,thred;
    pageAdapter adapter;
    TextView name;
    ImageView imageView;
    FirebaseAuth auth  =FirebaseAuth. getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Uri uri;
   Button chenge_back_grund;
   ImageView back_gruond;
    StorageReference mStorageRef;
     StorageReference mStorageRef2;
    DocumentReference rom = db.collection("oll user").document(auth.getCurrentUser().getUid());
    DocumentReference rom1 = db.collection("oll user_Profil").document(auth.getCurrentUser().getUid());
    DocumentReference rom2 = db.collection("oll user_Profil back_ground").document(auth.getCurrentUser().getUid());
    String  get_Name;
    String Time_Fragment1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(" ");
        pager = findViewById(R.id.viewpager1);
        layout = findViewById(R.id.tablayout);
        first = findViewById(R.id.firstitem);
        scand= findViewById(R.id.secnditem);
        thred= findViewById(R.id.thireditem);


        drawerLayout = findViewById(R.id.drawo);
        navigationView = findViewById(R.id.navigationv123);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
      //add textview heder
         name=headerLayout.findViewById(R.id.textViewname);
         imageView= headerLayout.findViewById(R.id.post);
        chenge_back_grund = headerLayout.findViewById(R.id.chenge_back_gruand);
        back_gruond= headerLayout.findViewById(R.id.back_grund);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.off);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        adapter = new pageAdapter(getSupportFragmentManager(), FragmentPagerAdapter
                .BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,layout.getTabCount());
        pager.setAdapter(adapter);


        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));


imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent image_porfile = new Intent(Intent.ACTION_GET_CONTENT);
        image_porfile.setType("image/*");

        startActivityForResult(Intent.createChooser(image_porfile,"اختر مكان الصوره "), 123);





    }
});

chenge_back_grund.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent image_porfile = new Intent(Intent.ACTION_GET_CONTENT);
        image_porfile.setType("image/*");

        startActivityForResult(Intent.createChooser(image_porfile,"اختر مكان الصوره "), 1234);

    }
});
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd hh:mm a", Locale.ENGLISH);
        Time_Fragment1 = dateFormat.format(cal1.getTime());



        mStorageRef = FirebaseStorage.getInstance().getReference().child(auth.getCurrentUser().getUid());
        mStorageRef2 = FirebaseStorage.getInstance().getReference().child(auth.getCurrentUser().getUid());



    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==123 && resultCode == Activity.RESULT_OK && data!=null && data.getData()!=null){

           // imageView.setImageURI(data.getData());
            Uri image = data.getData();
           // imageView.setImageURI(data.getData());

            if (image!=null){

        StorageReference file = mStorageRef.child("image_profile"+ UUID.randomUUID().toString());
            file.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap<String,String> map = new HashMap<>();

                            map.put("image", String.valueOf(uri));
                            map.put("name",get_Name);
                            map.put("time",Time_Fragment1);

                            rom1.set(map);


                        }
                    });
                }
            });















            }





    }else if (requestCode==1234&&resultCode==Activity.RESULT_OK&&data!=null&&data.getData()!=null){

            Uri image1 = data.getData();


            StorageReference file1 = mStorageRef.child("image_profile backgruond"+ UUID.randomUUID().toString());
            file1.putFile(image1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    file1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            HashMap<String,String> map = new HashMap<>();

                            map.put("image", String.valueOf(uri));
                            rom2.set(map);


                        }
                    });
                }
            });















        }
















        }










    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if (menuItem.getItemId()== R.id.page_main) {
            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }else if (menuItem.getItemId()==R.id.sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent out = new Intent(MainActivity.this, login.class);

            startActivity(out);
            finish();
        }else if (menuItem.getItemId()==R.id.settings){

        Intent settings = new Intent(getApplicationContext(),Settings.class);
        startActivity(settings);


        }





        return false;
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

                      name.setText(get_Name);

                }



            }
        });


        rom1.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                if (e!=null){
                    // Toast.makeText(page4_list.this, "Erorr", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()) {

                    String  getphoto = documentSnapshot.getString("image");

                    Picasso.with(getApplicationContext()).load(getphoto).into(imageView);

                }









            }
        });





        rom2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {


                if (e!=null){
                    // Toast.makeText(page4_list.this, "Erorr", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (documentSnapshot.exists()) {

                    String  getphoto1 = documentSnapshot.getString("image");

                    Picasso.with(getApplicationContext()).load(getphoto1).into(back_gruond);

                }









            }
        });












    }
}
