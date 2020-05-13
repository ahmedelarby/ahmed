package com.example.myapplicationlast;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ViewPager pager ;
    TabLayout layout;
    TabItem first ,scand,thred;
    pageAdapter adapter;
    TextView name;
    TextView Email;
    FirebaseAuth auth  =FirebaseAuth. getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    DocumentReference rom = db.collection("oll user").document(auth.getCurrentUser().getUid());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        Email=headerLayout.findViewById(R.id.textviewEmail);
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
        }else if (menuItem.getItemId()==R.id.Night_mode){

            drawerLayout.setBackgroundResource(R.drawable.back_nith);


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

                    String  get_Name = documentSnapshot.getString("name");

                      name.setText(get_Name);
                    String  get_Email = documentSnapshot.getString("Email");
                            Email.setText(get_Email);
                }



            }
        });







    }
}
