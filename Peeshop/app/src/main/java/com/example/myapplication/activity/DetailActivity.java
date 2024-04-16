    package com.example.myapplication.activity;

    import android.os.Bundle;
    import android.os.Handler;
    import android.view.View;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentPagerAdapter;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.myapplication.adapter.SlideAdapter;
    import com.example.myapplication.adapter.sizeAdapter;
    import com.example.myapplication.databinding.ActivityDetailBinding;
    import com.example.myapplication.domain.ItemsDomain;
    import com.example.myapplication.domain.SlideItem;
    import com.example.myapplication.fragment.DescriptionFragment;
    import com.example.myapplication.fragment.ReviewFragment;
    import com.example.myapplication.fragment.SoldFragment;
    import com.example.myapplication.helper.ManagmentCart;

    import java.util.ArrayList;
    import java.util.List;

    public class DetailActivity extends MainActivity {
        ActivityDetailBinding binding;
        private ItemsDomain object;
        private int numberOrder = 1;
        private ManagmentCart managmentCart;
        private Handler handler = new Handler();


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
           binding = ActivityDetailBinding.inflate(getLayoutInflater());
           setContentView(binding.getRoot());

           managmentCart = new ManagmentCart(this);

            //Xu li su kien button co id addToCartBtn
            binding.addToCartBtn.setOnClickListener(v -> {
                object.setNumberInCart(numberOrder);
                managmentCart.insertFood(object);
            });

            //xu li su kien nut back
            binding.backBtn.setOnClickListener(v -> finish());

           getBundles();
           initBanners();
           initSize();
           setupViewPaper();
        }

        private void initSize() {
            ArrayList<String> list = new ArrayList<>();
            list.add("S");
            list.add("M");
            list.add("L");
            list.add("XL");
            list.add("XXL");

            binding.recyclerSize.setAdapter(new sizeAdapter(list));
            binding.recyclerSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        }

        private void initBanners() {
            if(object!=null&&object.getPicUrl()!=null) {
                ArrayList<SlideItem> slideItems = new ArrayList<>();
                for (int i = 0; i < object.getPicUrl().size(); i++) {
                    slideItems.add(new SlideItem(object.getPicUrl().get(i)));
                }
                binding.viewpageSlider.setAdapter(new SlideAdapter(slideItems, binding.viewpageSlider));
                binding.viewpageSlider.setClipToPadding(false);
                binding.viewpageSlider.setClipChildren(false);
                binding.viewpageSlider.setOffscreenPageLimit(3);
                binding.viewpageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
            }
        }

        private void getBundles() {
            object = (ItemsDomain) getIntent().getSerializableExtra("object");
            if(object != null) {
                binding.titleTxt.setText(object.getTitle());
                binding.priceTxt.setText("$" + object.getPrice());
                binding.ratingBar.setRating((float) object.getRating());
                binding.ratingTxt.setText(object.getRating() + "Rating");

                binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        object.setNumberInCart(numberOrder);
                        managmentCart.insertFood(object);
                    }
                });
                binding.backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }

       private void setupViewPaper(){
           ViewPaperAdapter adapter = new ViewPaperAdapter(getSupportFragmentManager());

           DescriptionFragment tab1 = new DescriptionFragment();
           ReviewFragment tab2 = new ReviewFragment();
           SoldFragment tab3 = new SoldFragment();

           Bundle bundle1 = new Bundle();
           Bundle bundle2 = new Bundle();
           Bundle bundle3 = new Bundle();

//           bundle1.putString("description", object.getDescription());

           tab1.setArguments(bundle1);
           tab2.setArguments(bundle2);
           tab3.setArguments(bundle3);

           adapter.addFrag(tab1, "Descriptions");
           adapter.addFrag(tab2, "Reviews");
           adapter.addFrag(tab3, "Sold");

           binding.viewpaper.setAdapter(adapter);
//           binding.tabLayout.setupWithViewPager(binding.viewpaper);

       }
        private class ViewPaperAdapter extends FragmentPagerAdapter{
            private final List<Fragment> mFragmentList = new ArrayList<>();
            private  final List<String> mFragmentTitleList = new ArrayList<>();

            public ViewPaperAdapter(@NonNull FragmentManager fm){
                super(fm);
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }


            @Override
            public int getCount() {
                return mFragmentList.size();
            }


            private void addFrag(Fragment fragment, String  title){
                mFragmentList.add(fragment);
                mFragmentTitleList.add(title);
            }
            @Override
            public CharSequence getPageTitle(int position){
                return mFragmentTitleList.get(position);
            }
        }
    }

