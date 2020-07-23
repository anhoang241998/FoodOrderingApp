package com.example.foodorderingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.util.GlideUtil;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuAdapterViewHolder> {

    private List<Allmenu> mAllMenu;

    public AllMenuAdapter(List<Allmenu> allMenu) {
        mAllMenu = allMenu;
    }

    public void updateMenu(List<Allmenu> allMenu) {
        mAllMenu.clear();
        mAllMenu.addAll(allMenu);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AllMenuAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_menu_recycler, parent, false);
        return new AllMenuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuAdapterViewHolder holder, int position) {
        holder.allMenuName.setText(mAllMenu.get(position).getName());
        holder.allMenuPrice.setText(mAllMenu.get(position).getPrice());
        holder.allMenuTime.setText(mAllMenu.get(position).getDeliveryTime());
        holder.allMenuRating.setText(mAllMenu.get(position).getRating());
        holder.allMenuCharges.setText(mAllMenu.get(position).getDeliveryCharges());
        holder.allMenuNote.setText(mAllMenu.get(position).getNote());
        GlideUtil.loadImages(holder.allMenuImage, mAllMenu.get(position).getImageUrl(), GlideUtil.getCircularProgressDrawable(holder.allMenuImage.getContext()));

    }

    @Override
    public int getItemCount() {
        if (mAllMenu != null) {
            return mAllMenu.size();
        }
        return 0;
    }

    public class AllMenuAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView allMenuImage;
        TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;

        public AllMenuAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuNote = itemView.findViewById(R.id.all_menu_note);
            allMenuCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            allMenuTime = itemView.findViewById(R.id.all_menu_delivery_time);
            allMenuRating = itemView.findViewById(R.id.all_menu_rating);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);
        }
    }
}
