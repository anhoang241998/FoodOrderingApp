package com.example.foodorderingapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.R;
import com.example.foodorderingapp.databinding.ItemRecommendedRecyclerBinding;
import com.example.foodorderingapp.models.Recommended;
import com.example.foodorderingapp.util.GlideUtil;
import com.example.foodorderingapp.view.ListFragmentDirections;
import com.example.foodorderingapp.view.recyclerinterface.RecommendClickListener;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.RecommendedViewHolder> implements RecommendClickListener {

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemRecommendedRecyclerBinding view = DataBindingUtil.inflate(inflater, R.layout.item_recommended_recycler, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        holder.itemView.setRecommend(mRecommendedList.get(position));
        holder.itemView.setListener(this);
//        GlideUtil.loadImages(holder.recommendedImage, mRecommendedList.get(position).getImageUrl(), GlideUtil.getCircularProgressDrawable(holder.recommendedImage.getContext()));
    }

    @Override
    public int getItemCount() {
        if (mRecommendedList != null) {
            return mRecommendedList.size();
        }
        return 0;
    }

    @Override
    public void onRecommendClicked(View v) {
        String uuidString = ((TextView)v.findViewById(R.id.recommendID)).getText().toString();
        int uuid = Integer.valueOf(uuidString);
        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
        action.setRecommendUuid(uuid);
        Navigation.findNavController(v).navigate(action);
    }

    public class RecommendedViewHolder extends RecyclerView.ViewHolder {

        ItemRecommendedRecyclerBinding itemView;

        public RecommendedViewHolder(@NonNull ItemRecommendedRecyclerBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
