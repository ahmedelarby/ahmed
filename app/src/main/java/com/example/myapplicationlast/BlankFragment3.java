package com.example.myapplicationlast;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Model_oll_users> model_oll_users1 = new ArrayList<>();
    Shew_Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    FirebaseAuth auth  =FirebaseAuth. getInstance();
    String get_Name;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference rom = db.collection("oll user").document(auth.getCurrentUser().getUid());

   String online;
    CollectionReference collectionReference = db.collection("user online");
    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       online="on";
       if (online=="on"){
           online="on";
       }else {online="off";}
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_blank_fragment3, container, false);

        recyclerView = view.findViewById(R.id.rec31);
       // model_oll_users1.add(new Model_oll_users("ahmed","نشط الان ",0,0));
        adapter = new Shew_Adapter(model_oll_users1,getContext());
        adapter.setOnItemClickListener(new Shew_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick() {


            }
        });
        adapter.setOnname(new Shew_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick() {
                Toast.makeText(getContext(), "sor", Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);





        return view;




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

                }


            }
        });



        Map<String, Object> useronline = new HashMap<>();


        useronline.put("online",online);
        useronline.put("name",get_Name);

        // db.collection("user").document(auth.getCurrentUser().getUid()).set(user)
        db.collection("user online").document(auth.getCurrentUser().getUid()).set(useronline);




        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }
                model_oll_users1.clear();
                for (QueryDocumentSnapshot querySnapshot : queryDocumentSnapshots){
                    Model_oll_users dater1f3 = querySnapshot.toObject(Model_oll_users.class);
                    // String name= dater1f3.getName();
                    model_oll_users1.add(dater1f3);
                }
                adapter.notifyDataSetChanged();










            }
        });















    }
}
