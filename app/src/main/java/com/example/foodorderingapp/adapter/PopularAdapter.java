package com.example.foodorderingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.util.GlideUtil;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private List<Popular> mPopularList;

    public PopularAdapter(List<Popular> popularList) {
        this. mPopularList = popularList;
    }

    public void updatePopularList(List<Popular> newList){
        mPopularList.clear();
        mPopularList.addAll(newList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular_recycler, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.popularName.setText(mPopularList.get(position).getName());
        GlideUtil.loadImages(holder.popularImage, mPopularList.get(position).getImageUrl(), GlideUtil.getCircularProgressDrawable(holder.popularImage.getContext()));

    }

    @Override
    public int getItemCount() {
        if (mPopularList != null) {
            return mPopularList.size();
        }
        return 0;
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {

        ImageView popularImage;
        TextView popularName;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);

            popularImage = itemView.findViewById(R.id.all_menu_image);
            popularName = itemView.findViewById(R.id.all_menu_name);
        }
    }
}
