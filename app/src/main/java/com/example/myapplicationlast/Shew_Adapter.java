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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Shew_Adapter extends RecyclerView.Adapter<Shew_Adapter.ViewHolder>{
    ArrayList<Model_oll_users> model_data;
    OnItemClickListener onItemClickListener;
    OnItemClickListener onname;
    Context context;

    public void setOnname(OnItemClickListener onname) {
        this.onname = onname;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public Shew_Adapter(ArrayList<Model_oll_users> model_data,Context context) {
        // setHasStableIds(true);
        this.model_data = model_data;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext())).inflate(R.layout.stilt_item_recycelr2,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Model_oll_users item = model_data.get(position);
        holder.name_user.setText(item.getName());
        holder.online.setText(item.getOnline());

        holder.textmenu.setImageResource(item.getMenu());
        Picasso.with(context).load(item.getImage()).fit().centerCrop().into(holder.sora_user);
        if (onItemClickListener != null)
            holder.textmenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Log.e("Position = ",position+"");
                    // onItemClickListener.onItemClick();


                    PopupMenu popupMenu =new PopupMenu(context,holder.textmenu);
                    popupMenu.getMenuInflater().inflate(R.menu.itemrecycr_oll_user,popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int id = item.getItemId();
                            switch (id){

                                case R.id.eblak:
                                    Toast.makeText(context, "تحت الانشاء", Toast.LENGTH_SHORT).show();
                                    break;
                                case R.id.bloke:
                                    Toast.makeText(context, "جاري العمل عليها ", Toast.LENGTH_SHORT).show();
                            }


                            return true;
                        }
                    });
                    popupMenu.show();











                }
            });
        if (onname!=null)
            holder.name_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onname.onItemClick();



                }
            });
    }
    @Override
    public int getItemCount() {
        return model_data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_user;
        TextView online;
        ImageView sora_user;
        ImageView textmenu;
        View perantview;

        ViewHolder(View view){
            super(view);
            perantview = view;
            name_user = view.findViewById(R.id.name_user);
            online = view.findViewById(R.id.online);
            sora_user = view.findViewById(R.id.soraicuant);
           textmenu= view.findViewById(R.id.textmenu);

        }



    }






    interface OnItemClickListener {
        void onItemClick();
    }












}