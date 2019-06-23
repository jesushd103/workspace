package com.example.easytravel.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.example.easytravel.R;
import com.example.easytravel.activity.CategoryDetailsActivity;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.CategoryViewHolder> {

    private JsonArray mData;

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.getRootView();
        }

        public void bind(JsonObject category) {
            mTextView.setText(category.get("title").getAsString());
            mTextView.setOnClickListener(it -> {
                Intent intent = new Intent(mTextView.getContext(), CategoryDetailsActivity.class);
                intent.putExtra("alias", category.get("alias").getAsString());
                mTextView.getContext().startActivity(intent);
            });
        }

    }

    public AllCategoriesAdapter(JsonArray data) {
        mData = data;
    }

    public void setData(JsonArray data) {
        mData = data;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CategoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.bind(mData.get(i).getAsJsonObject());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
