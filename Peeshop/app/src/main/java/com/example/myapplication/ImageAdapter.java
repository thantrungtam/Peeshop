package com.example.myapplication;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


@NonNull
@Override
public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
    View view
        }

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    Context context;
    ArrayList<String> arrayList;
    public ImageAdapter(Context context,ArrayList<String> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public ViewHolder(@NonNull View itemView){
                super(itemView);
                imageView = itemView.findViewById(R.id.list_item_image);

        }
    }
}
