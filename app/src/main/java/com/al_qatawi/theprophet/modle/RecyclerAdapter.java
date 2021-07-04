package com.al_qatawi.theprophet.modle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.al_qatawi.theprophet.R;
import com.al_qatawi.theprophet.interfase.OnClickLasenarRecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";
    ArrayList<Content>list;
    OnClickLasenarRecyclerView onClickLasenarRecyclerView;

    public RecyclerAdapter(ArrayList<Content> list , OnClickLasenarRecyclerView onClickLasenarRecyclerView) {
        this.list = list;
        this.onClickLasenarRecyclerView = onClickLasenarRecyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(list.get(position).getTEXT());


        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String text = String.valueOf(holder.textView.getText());
                Log.d(TAG, "hhh onClick: "+text);
                onClickLasenarRecyclerView.onClick(text);

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_item);

        }
    }

}
