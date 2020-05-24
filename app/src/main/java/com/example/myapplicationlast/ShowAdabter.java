package com.example.myapplicationlast;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ShowAdabter extends RecyclerView.Adapter<ShowAdabter.ViewHolder>{
    ArrayList<Model_Recycler>dates;
    OnItemClickListener onItemClickListener;
   OnItemClickListener onname;
    Context context;

    public void setOnname(OnItemClickListener onname) {
        this.onname = onname;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public ShowAdabter(ArrayList<Model_Recycler> dates,Context context) {
       // setHasStableIds(true);
        this.dates = dates;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.stily_item,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Model_Recycler item = dates.get(position);
        holder.name.setText(item.getName());
        holder.time.setText(item.getTime());
        holder.words.setText(item.getWord());
       holder.btn_menu.setImageResource(item.getSora1());
        Picasso.with(context).load(item.getPost_photo()).fit().centerCrop().into(holder.post_photo);
        Picasso.with(context).load(item.getSora()).fit().centerCrop().into(holder.sora);
      if (onItemClickListener != null)
       holder.btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Log.e("Position = ",position+"");
               // onItemClickListener.onItemClick();


                PopupMenu popupMenu =new PopupMenu(context,holder.btn_menu);
                popupMenu.getMenuInflater().inflate(R.menu.itemrecycr,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item1) {
                        int id = item1.getItemId();
                        switch (id){
                            case R.id.Shew_Profile:
                                String name=  item.getName();
                                String gender=  item.getGender();
                                String email=  item.getEmail();
                                String sora=  item.getSora();
                                String background=  item.getImage_profile();
                                String timeopen= item.getTime_open();
                                Intent profil = new Intent(context,Show_Profile.class);
                                profil.putExtra("name",name);
                                profil.putExtra("image",sora);
                                profil.putExtra("gender",gender);
                                profil.putExtra("email",email);
                                profil.putExtra("background",background);
                                profil.putExtra("timeopen",timeopen);

                                context.startActivity(profil);

                                break;
                            case R.id.ablage:
                                Toast.makeText(context, "جاري ارسال الابلاغ الي الاداره ", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.send_masege_f1:
                                Toast.makeText(context, "جاري العمل عليها ", Toast.LENGTH_SHORT).show();
                        }


                        return true;
                    }
                });
                popupMenu.show();











            }
        });
       if (onname!=null)
       holder.name.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onname.onItemClick();
             String name=  item.getName();
               String gender=  item.getGender();
               String email=  item.getEmail();
               String sora=  item.getSora();
               String background=  item.getImage_profile();
               String timeopen= item.getTime_open();
               Intent profil = new Intent(context,Show_Profile.class);
               profil.putExtra("name",name);
               profil.putExtra("image",sora);
               profil.putExtra("gender",gender);
               profil.putExtra("email",email);
               profil.putExtra("background",background);
               profil.putExtra("timeopen",timeopen);

               context.startActivity(profil);
           }
       });
    }
    @Override
    public int getItemCount() {
        return dates.size();
    }
     public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView time;
         ImageView sora;
         TextView words;
        View perantview;
        ImageView btn_menu;
        ImageView post_photo;
        ViewHolder(View view){
           super(view);
           perantview = view;
           name = view.findViewById(R.id.name);
           time = view.findViewById(R.id.time);
           sora = view.findViewById(R.id.sora);
           words= view.findViewById(R.id.word);
           post_photo= view.findViewById(R.id.post_photo);
           btn_menu=view.findViewById(R.id.btn_menu);
        }



    }






    interface OnItemClickListener {
        void onItemClick();
    }












}
