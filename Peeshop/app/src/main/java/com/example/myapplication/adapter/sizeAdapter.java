package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ViewholderCategoryBinding;
import com.example.myapplication.databinding.ViewholderSizeBinding;

import java.util.ArrayList;

public class sizeAdapter extends RecyclerView.Adapter<sizeAdapter.Viewholder> {
    ArrayList<String> items;
    Context context;
    int selectedPosition = -1;
    int lastSelectedPosition = -1;

    public sizeAdapter(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public sizeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderSizeBinding binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull sizeAdapter.Viewholder holder, int position) {
        holder.binding.sizeTxt.setText(items.get(position));

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(lastSelectedPosition);
                notifyItemChanged(selectedPosition);
            }
        });

        if(selectedPosition == holder.getAdapterPosition()){
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.size_selected);
            holder.binding.sizeTxt.setTextColor(context.getResources().getColor(R.color.blue));
        }else{
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.size_unselected);
            holder.binding.sizeTxt.setTextColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return items.size(); //tra ve so luong item trong danh sach items
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderSizeBinding binding;
        public Viewholder(ViewholderSizeBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
