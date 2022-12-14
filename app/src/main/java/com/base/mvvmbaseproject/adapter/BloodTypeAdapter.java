package com.base.mvvmbaseproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.OnChooseRecyclerView;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.entity.BloodType;

import java.util.ArrayList;

public class BloodTypeAdapter extends RecyclerView.Adapter<BloodTypeAdapter.ViewHolder> {
    private final Context mContext;
    private final ArrayList<BloodType> mBlood;
    private int row_index = -1;
    OnChooseRecyclerView onChooseBloodType;

    public BloodTypeAdapter(Context mContext, ArrayList<BloodType> mBlood, OnChooseRecyclerView onChooseBloodType) {
        this.mContext = mContext;
        this.mBlood = mBlood;
        this.onChooseBloodType = onChooseBloodType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mBloodName;
        LinearLayout ln_layout_blood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBloodName = (itemView).findViewById(R.id.btn_nhommauA);
            ln_layout_blood = itemView.findViewById(R.id.ln_nhommauA);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View heroView = inflater.inflate(R.layout.rcv_blood_type, parent, false);
        return new ViewHolder(heroView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull BloodTypeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BloodType bloodType = mBlood.get(position);
        holder.mBloodName.setText(bloodType.getBloodtype());
        holder.ln_layout_blood.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();
            mBlood.get(position).setChecked(true);
            onChooseBloodType.onChoose(mBlood.get(position));
        });
        if (row_index == position) {
            holder.ln_layout_blood.setBackgroundResource(R.drawable.linear_nhommau);
        } else {
            holder.ln_layout_blood.setBackgroundResource(R.drawable.ln_nhommau_white);
        }
    }

    @Override
    public int getItemCount() {
        return mBlood.size();
    }

}

