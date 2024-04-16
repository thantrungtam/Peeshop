package com.example.myapplication.adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.domain.SlideItem;
import com.example.myapplication.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


//Tao view cho tung trang trong viewpaperSlide

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SlideViewHolder>{
    private final Handler handler = new Handler();
    private List<SlideItem> slideItems;
    private ViewPager2 viewPager2;
    private Context context;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
//            slideItems.addAll(slideItems);
//            notifyDataSetChanged();
            if(viewPager2.getCurrentItem() < slideItems.size() -1){
                viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
            }else{
                viewPager2.setCurrentItem(0);
            }
            handler.postDelayed(this, 3000);
        }
    };

    public SlideAdapter(List<SlideItem> slideItem, ViewPager2 viewPager2) { //contructor
        this.slideItems = slideItem;
        this.viewPager2 = viewPager2;
    }


    @NonNull
    @Override
    public SlideAdapter.SlideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        //tao slideholder voi ImageView de hien thi
        return new SlideViewHolder(LayoutInflater.from(context).inflate(R.layout.slide_item_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SlideAdapter.SlideViewHolder holder, int position) {
        //cap nhat SlideViewHolder tai vi tri cu the
        holder.setImageView(slideItems.get(position));
        if(position == slideItems.size() -2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SlideViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SlideViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        handler.postDelayed(runnable, 3000);
    }
    @Override
    public int getItemCount() { //tra ve so luong hinh an trong
        return slideItems.size();
    }

    class SlideViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public SlideViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlide);
        }

        void setImageView(SlideItem slideItems){
            //Neu muon hien thi hinh anh tu internet thi viet code su dung glide hoac piccso
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop());
            Glide.with(context).load(slideItems.getUrl()).apply(requestOptions).into(imageView);
        }
    }
}
