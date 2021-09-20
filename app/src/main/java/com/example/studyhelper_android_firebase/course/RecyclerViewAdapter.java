package com.example.studyhelper_android_firebase.course;

import com.example.studyhelper_android_firebase.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> data, Context mContext) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //creating the view holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //loading data
        String animal = mData.get(position);
        holder.myTextView.setText(animal);

        holder.parentLayout.setOnClickListener(view -> Toast.makeText(mContext, "Clicked on " + mData.get(position), Toast.LENGTH_SHORT).show());
        //holder.button.setonclick listner
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentLayout;
        TextView myTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myTextView = itemView.findViewById(R.id.tvAnimalName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}

//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.studyhelper_android_firebase.R;
//
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
//    private List<String> mData;
//    private LayoutInflater mInflater;
//    private ItemClickListener mClickListener;
//
//    // data is passed into the constructor
//    Adapter(Context context, List<String> data) {
//        this.mInflater = LayoutInflater.from(context);
//        this.mData = data;
//    }
//
//    // inflates the row layout from xml when needed
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.row_layout, parent, false);
//        return new ViewHolder(view);
//    }
//
//    // binds the data to the TextView in each row
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = mData.get(position);
//        holder.myTextView.setText(animal);
//    }
//
//    // total number of rows
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//
//    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView myTextView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            myTextView = itemView.findViewById(R.id.tvAnimalName);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
//
//}
