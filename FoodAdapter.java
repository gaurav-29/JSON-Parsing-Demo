package com.example.jsonparsingdemo;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter {

    private ArrayList<Food> TitleFoodList;
    private Context ctx;

    private static int currentPosition = 0; // due to static, when we close the app with some selected expanded position and again open app, the same (last selected) expanded position will open.

    public FoodAdapter(ArrayList<Food> titleFoodList, Context ctx) {
        TitleFoodList = titleFoodList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View FoodRow = LayoutInflater.from(ctx).inflate(R.layout.food_row,null);
        MyWidgetContainer container = new MyWidgetContainer(FoodRow);
        return container;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        final MyWidgetContainer container = (MyWidgetContainer) holder;
        Food currenttitlefood = TitleFoodList.get(position);

        String titleimageURL = currenttitlefood.getTitleimage();
        String titleName = currenttitlefood.getTitlename();
        //String subtitleimageURL = currenttitlefood.getSubtitleimage();
       // String subtitleName = currenttitlefood.getSubtitlename();

        container.titletxtfoodname.setText(titleName);
        Picasso.get().load(titleimageURL).fit().centerInside().into(container.titleimgfood);
       // container.subtitletxtfoodname.setText(subtitleName);
       // Picasso.get().load(subtitleimageURL).fit().centerInside().into(container.subtitleimgfood);

        container.linsublist.setVisibility(View.GONE);

        container.imgadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //container.imgadd.setVisibility(View.GONE);
               // container.imgminus.setVisibility(View.VISIBLE);
                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });

        //if the position is equals to the item position which is to be expanded.
        if (currentPosition == position) {
            //creating an animation
            Animation animlist = AnimationUtils.loadAnimation(ctx, R.anim.animlist);

            //toggling visibility
            container.linsublist.setVisibility(View.VISIBLE);

            //adding sliding effect
            container.linsublist.startAnimation(animlist);
        }
    }

    @Override
    public int getItemCount() {
        return TitleFoodList.size();
    }

    class MyWidgetContainer extends RecyclerView.ViewHolder
    {
        ImageView titleimgfood, imgadd, subtitleimgfood, imgminus;
        TextView titletxtfoodname, subtitletxtfoodname;
        LinearLayout linsublist;

        public MyWidgetContainer(@NonNull View itemView) {
            super(itemView);

            titleimgfood = itemView.findViewById(R.id.titleimgfood);
            titletxtfoodname = itemView.findViewById(R.id.titletxtfoodname);
            //subtitleimgfood = itemView.findViewById(R.id.subtitleimgfood);
            //subtitletxtfoodname = itemView.findViewById(R.id.subtitletxtfoodname);
            imgadd = itemView.findViewById(R.id.imgadd);
            //imgminus = itemView.findViewById(R.id.imgminus);
            linsublist = itemView.findViewById(R.id.linsublist);
        }
    }
}
