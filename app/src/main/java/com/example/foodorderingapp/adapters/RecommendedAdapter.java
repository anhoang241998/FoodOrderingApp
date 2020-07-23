package com.example.foodorderingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.models.Recommended;
import com.example.foodorderingapp.util.GlideUtil;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> {

    private List<Recommended> mRecommendedList;

    public RecommendedAdapter(List<Recommended> recommendedList) {
        mRecommendedList = recommendedList;
    }

    public void updateRecommendedList(List<Recommended> recommendedList) {
        mRecommendedList.clear();
        mRecommendedList.addAll(recommendedList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommended_recycler, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        holder.recommendedName.setText(mRecommendedList.get(position).getName());
        holder.recommendedRating.setText(mRecommendedList.get(position).getRating());
        holder.recommendedCharges.setText(mRecommendedList.get(position).getDeliveryCharges());
        holder.recommendedDeliveryTime.setText(mRecommendedList.get(position).getDeliveryTime());
        holder.recommendedPrice.setText(mRecommendedList.get(position).getPrice());
        GlideUtil.loadImages(holder.recommendedImage, mRecommendedList.get(position).getImageUrl(), GlideUtil.getCircularProgressDrawable(holder.recommendedImage.getContext()));
    }

    @Override
    public int getItemCount() {
        if (mRecommendedList != null) {
            return mRecommendedList.size();
        }
        return 0;
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

        ImageView recommendedImage;
        TextView recommendedName, recommendedRating, recommendedDeliveryTime, recommendedCharges, recommendedPrice;

        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);

            recommendedImage = itemView.findViewById(R.id.recommended_image);
            recommendedName = itemView.findViewById(R.id.recommended_name);
            recommendedRating = itemView.findViewById(R.id.recommended_rating);
            recommendedDeliveryTime = itemView.findViewById(R.id.recommended_delivery_time);
            recommendedCharges = itemView.findViewById(R.id.delivery_type);
            recommendedPrice = itemView.findViewById(R.id.recommended_price);
        }
    }
}
