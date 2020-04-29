package com.boubalos.knightmoves.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.boubalos.knightmoves.R;
import com.boubalos.knightmoves.models.KnightPath;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {
    private List<KnightPath> pathList =new ArrayList<>();
    private Context context;
    private int selected=-1;
    private RecyclerListInterface listInterface;

    public MyRecyclerAdapter(Context context)
   {
       super();
       this.context=context;
       listInterface =(RecyclerListInterface) context;

   }
    public void setPathList(List<KnightPath> pathList) {
        this.pathList = pathList;
        selected=-1;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.path_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.background.setSelected(position==selected);
        holder.pathText.setText(pathList.get(position).Stringify());
        holder.background.setOnClickListener((v)->{
            selected=position;
            notifyDataSetChanged();
            holder.icon.startAnimation(AnimationUtils.loadAnimation(context, R.anim.pulse));
            listInterface.setActivePath(position);
        });
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pathText;
        ImageView icon;
        LinearLayout background;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            background =itemView.findViewById(R.id.layout);
            icon=itemView.findViewById(R.id.icon);
            pathText=itemView.findViewById(R.id.path_desc);
        }
    }

    public interface RecyclerListInterface{
        void setActivePath(int i);
    }

}
