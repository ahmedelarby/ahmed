package com.example.myapplicationlast;

import android.content.Context;
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
        holder.sora.setImageResource(item.getSora());
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
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.remove:
                                Toast.makeText(context, "جاري الازاله ", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.eblak:
                                Toast.makeText(context, "تحت الانشاء", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.send_masege:
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
        ViewHolder(View view){
           super(view);
           perantview = view;
           name = view.findViewById(R.id.name);
           time = view.findViewById(R.id.time);
           sora = view.findViewById(R.id.sora);
           words= view.findViewById(R.id.word);
           btn_menu=view.findViewById(R.id.btn_menu);
        }



    }






    interface OnItemClickListener {
        void onItemClick();
    }












}
