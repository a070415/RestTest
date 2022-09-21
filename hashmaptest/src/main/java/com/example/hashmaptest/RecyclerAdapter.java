package com.example.hashmaptest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private Context c;
    private List<Datata> dataList;

    public RecyclerAdapter(Context c, List<Datata> dataList) {
        this.c = c;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(c).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

//        holder.name.setText(dataList.get(position).getName());
//        holder.phone.setText("" + dataList.get(position).getPhone());
//        holder.address.setText("" + dataList.get(position).getAddress());

//        holder.name.setText(dataList.get(position).getId());
        holder.phone.setText("" + dataList.get(position).getTitle());
        holder.address.setText("" + dataList.get(position).getContent());

        holder.itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {

                String title = dataList.get(position).getTitle();
                String content = dataList.get(position).getContent();
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("content", content);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        };

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//        TextView name;
        TextView phone;
        TextView address;

        ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

//            name = (TextView) itemView.findViewById(R.id.dataName);
            phone = (TextView) itemView.findViewById(R.id.dataPhone);
            address = (TextView) itemView.findViewById(R.id.dataAddress);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClickListener(v,getLayoutPosition());
        }
    }

}
