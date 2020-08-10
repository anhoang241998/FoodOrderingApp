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
import com.example.foodorderingapp.databinding.ItemAllMenuRecyclerBinding;
import com.example.foodorderingapp.models.Allmenu;
import com.example.foodorderingapp.util.GlideUtil;
import com.example.foodorderingapp.view.ListFragmentDirections;
import com.example.foodorderingapp.view.recyclerinterface.AllMenuClickListener;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuAdapterViewHolder> implements AllMenuClickListener {

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
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemAllMenuRecyclerBinding view = DataBindingUtil.inflate(inflater, R.layout.item_all_menu_recycler, parent, false);
        return new AllMenuAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuAdapterViewHolder holder, int position) {
        holder.itemView.setAllmenu(mAllMenu.get(position));
        holder.itemView.setListener(this);
//        GlideUtil.loadImages(holder.allMenuImage, mAllMenu.get(position).getImageUrl(), GlideUtil.getCircularProgressDrawable(holder.allMenuImage.getContext()));

    }

    @Override
    public int getItemCount() {
        if (mAllMenu != null) {
            return mAllMenu.size();
        }
        return 0;
    }

    @Override
    public void onMenuClicked(View v) {
        String uuidString = ((TextView)v.findViewById(R.id.allmenuID)).getText().toString();
        int uuid = Integer.valueOf(uuidString);
        ListFragmentDirections.ActionDetail action = ListFragmentDirections.actionDetail();
        action.setAllMenuUuid(uuid);
        Navigation.findNavController(v).navigate(action);
    }

    public class AllMenuAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemAllMenuRecyclerBinding itemView;

        public AllMenuAdapterViewHolder(@NonNull ItemAllMenuRecyclerBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }
    }
}
