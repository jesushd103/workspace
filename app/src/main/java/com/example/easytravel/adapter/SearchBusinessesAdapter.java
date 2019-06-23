package com.example.easytravel.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easytravel.R;
import com.example.easytravel.model.Business;

import java.util.List;

public class SearchBusinessesAdapter extends RecyclerView.Adapter<SearchBusinessesAdapter.BusinessViewHolder> {
    private LayoutInflater mInflater;
    private List<Business> businessesList;
    private OnBusinessClick onBusinessClick;


    public interface OnBusinessClick{
        void onBusinessClick(int pos);
    }

    public class BusinessViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView business_name;

        private BusinessViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            business_name=itemView.findViewById(R.id.item_business_text);
        }

        public void onClick(View v){
            onBusinessClick.onBusinessClick(getAdapterPosition());
        }
    }

    public SearchBusinessesAdapter(Context context, List<Business> list){
        mInflater= LayoutInflater.from(context);
        businessesList=list;
        onBusinessClick=(OnBusinessClick) context;
    }

    public BusinessViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView=mInflater.inflate(R.layout.item_business,parent, false);
        return new BusinessViewHolder(itemView);
    }

    public void onBindViewHolder(BusinessViewHolder holder, int pos){
        if(businessesList!=null){
            Business b=businessesList.get(pos);
            holder.business_name.setText(b.getName());
        }else{
            //Exception:
            //review list isn't initialized
            Log.e("error","review list isn't initialized");
        }
    }

    public int getItemCount() {
        if (businessesList != null){
            return businessesList.size();
        }else{
            //Exception:
            //business list isn't initialized
            Log.e("error","business list isn't initialized");
            return 0;
        }
    }
}
