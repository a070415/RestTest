package com.example.recyclertest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context c;
    private List<Data> dataList;

    public RecyclerAdapter(Context c, List<Data> dataList) {
        this.c = c;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(c).inflate(R.layout.row_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        holder.name.setText(dataList.get(position).getName());
        holder.numOfStar.setText("" + dataList.get(position).getPhone());
        holder.numOfLike.setText("" + dataList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView numOfStar;
        TextView numOfLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.dataName);
            numOfStar = (TextView) itemView.findViewById(R.id.dataPhone);
            numOfLike = (TextView) itemView.findViewById(R.id.dataAdress);

        }
    }

}
