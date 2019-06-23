package com.example.easytravel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easytravel.R;
import com.example.easytravel.model.Review;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder> {
    private LayoutInflater mInflater;
    private List<Review> reviewsList;

    public ReviewsAdapter(Context context, List<Review> list){
        mInflater= LayoutInflater.from(context);
        reviewsList=list;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView=mInflater.inflate(R.layout.item_review,viewGroup, false);
        return new ReviewViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewHolder, int i) {
        if(reviewsList!=null){
            Review r=reviewsList.get(i);
            reviewHolder.name.setText("Name: "+r.getName());
            reviewHolder.content.setText(r.getContent());
            reviewHolder.time.setText("Time: "+r.getTime());
        }else{
            //Exception:
            //review list isn't initialized
            Log.e("error","review list isn't initialized");
        }
    }

    @Override
    public int getItemCount() {
        if (reviewsList != null){
            return reviewsList.size();
        }else{
            //Exception:
            //review list isn't initialized
            Log.e("error","review list isn't initialized");
            return 0;
        }
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView content;
        private TextView time;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_review_name);
            content=itemView.findViewById(R.id.item_review_content);
            time=itemView.findViewById(R.id.item_review_time);
        }
    }
}
