package com.example.foodorderingapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ItemPopularRecyclerBinding;
import com.example.foodorderingapp.models.Popular;
import com.example.foodorderingapp.util.GlideUtil;
import com.example.foodorderingapp.view.ListFragmentDirections;
import com.example.foodorderingapp.view.recyclerinterface.PopularClickListener;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> implements PopularClickListener {

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPopularRecyclerBinding view = DataBindingUtil.inflate(inflater, R.layout.item_popular_recycler, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.itemView.setPopular(mPopularList.get(position));
        holder.itemView.setListener(this);
//        GlideUtil.loadImages(holder.popularImage, mPopularList.get(position).getImageUrl(), GlideUtil.getCircularProgressDrawable(holder.popularImage.getContext()));

    }

    @Override
    public int getItemCount() {
        if (mPopularList != null) {
            return mPopularList.size();
        }
        return 0;
    }

    @Override
    public void onPopularClicked(View v) {
        String uuidString = ((TextView)v.findViewById(R.id.popularID)).getText().toString();
        int uuid = Integer.valueOf(uuidString);
        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
        action.setPopularUuid(uuid);
        Navigation.findNavController(v).navigate(action);
    }

    public static class PopularViewHolder extends RecyclerView.ViewHolder {
        ItemPopularRecyclerBinding itemView;

        public PopularViewHolder(@NonNull ItemPopularRecyclerBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
