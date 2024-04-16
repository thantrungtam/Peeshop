package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ViewholderCategoryBinding;
import com.example.myapplication.domain.CategoryDomain;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Viewholder> {

    private ArrayList<CategoryDomain> items;
    private Context context;

    public CategoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderCategoryBinding biding =  ViewholderCategoryBinding.inflate(LayoutInflater.from(context),
                parent, false);
        return new Viewholder(biding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Viewholder holder, int position) {
        holder.binding.title.setText(items.get(position).getTitle());

        Glide.with(context)
                .load(items.get(position).getPicUrl()).into(holder.binding.pic);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ViewholderCategoryBinding binding;
            public Viewholder(ViewholderCategoryBinding binding){
                super(binding.getRoot());
                this.binding = binding;
            }
    }
}
