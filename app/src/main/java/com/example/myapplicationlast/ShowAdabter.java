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
    /*int siz ;
    String gravty;
    String colotext;
    String bold;
    String line;
    String colorback;*/
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
        /*colotext=item.getColortext();
        colorback=item.getColorbackground();
         siz = Integer.parseInt(item.getSize());
         gravty = item.getGravty();
         line=item.getLine();
         bold = item.getBold();*/





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
          /* words.setTextSize(siz);
           if (gravty=="center"){words.setGravity(Gravity.CENTER);return;}
           if (gravty=="start"){words.setGravity(Gravity.START);return;}
           if (gravty=="end"){words.setGravity(Gravity.END);return;}
           if (colotext=="black"){words.setTextColor(context.getResources().getColor(R.color.black));}
           if (colotext=="red"){words.setTextColor(context.getResources().getColor(R.color.red));}
           if (colotext=="Aqua"){words.setTextColor(context.getResources().getColor(R.color.Aqua));}
           if (colotext=="blue"){words.setTextColor(context.getResources().getColor(R.color.blue));}
           if (colotext=="white"){words.setTextColor(context.getResources().getColor(R.color.white));}
           if (colotext=="green"){words.setTextColor(context.getResources().getColor(R.color.green));}
           if (colotext=="yellow"){words.setTextColor(context.getResources().getColor(R.color.yellow));}
           if (colotext=="azure"){words.setTextColor(context.getResources().getColor(R.color.azure));}
           if (colotext=="silver"){words.setTextColor(context.getResources().getColor(R.color.silver));}
           if (colotext=="orange"){words.setTextColor(context.getResources().getColor(R.color.orange));}
           if (colotext=="purple"){words.setTextColor(context.getResources().getColor(R.color.purple));}
           if (colotext=="pea"){words.setTextColor(context.getResources().getColor(R.color.pea));}
           if (bold=="bold"){words.setTypeface(Typeface.DEFAULT_BOLD);}
           else {words.setTypeface(Typeface.DEFAULT);}
           if (line=="line"){
               words.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);}
            else {words.setPaintFlags(Paint.LINEAR_TEXT_FLAG);}
           if (colorback=="white") {

               words.setBackgroundResource(R.color.white);
           }
               else if (colorback=="red"){
                   words.setBackgroundResource(R.color.red);}

               else if (colorback=="Aqua"){
                   words.setBackgroundResource(R.color.Aqua);}

               else if (colorback=="blue"){
                   words.setBackgroundResource(R.color.blue);}

               else if (colorback=="black"){
                   words.setBackgroundResource(R.color.black);}

               else if (colorback=="green"){
                   words.setBackgroundResource(R.color.green);}

               else if (colorback=="yellow"){
                   words.setBackgroundResource(R.color.yellow);}

               else if (colorback=="azure"){
                   words.setBackgroundResource(R.color.azure);}

               else if (colorback=="silver"){
                   words.setBackgroundResource(R.color.silver);}

               else if (colorback=="orange"){
                   words.setBackgroundResource(R.color.orange);}

               else if (colorback=="purple"){
                   words.setBackgroundResource(R.color.purple);}

               else if (colorback=="pea"){
                   words.setBackgroundResource(R.color.pea);}*/





           post_photo= view.findViewById(R.id.post_photo);
           btn_menu=view.findViewById(R.id.btn_menu);
        }



    }






    interface OnItemClickListener {
        void onItemClick();
    }












}
