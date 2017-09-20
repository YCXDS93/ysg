package com.hc.ysg.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hc.ysg.R;
import com.hc.ysg.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * RecyclerView
 * Created by YU on 2017/8/25.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Bean.ysgBean>list;

    public RecyclerAdapter(Context context, List<Bean.ysgBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder viewHolder = null;
        view = LayoutInflater.from(context).inflate(R.layout.item, null);
        viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Bean.ysgBean bean=list.get(position);
        myViewHolder viewHolder= (myViewHolder) holder;
        Glide.with(context).load(bean.Moment.pictureList.get(0)).into(viewHolder.imageView);
        Glide.with(context).load(bean.Moment.pictureList.get(0)).into(viewHolder.imag);
        viewHolder.name.setText(bean.Moment.date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static  class myViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView imag,imageView;
        public myViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            imag=(ImageView)itemView.findViewById(R.id.imag);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
    }
}
