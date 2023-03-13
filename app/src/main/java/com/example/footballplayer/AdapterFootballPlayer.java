package com.example.footballplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer>
{
    private Context ctx;
    private ArrayList arrName, arrNumber, arrClub;
    public AdapterFootballPlayer(Context ctx, ArrayList arrName, ArrayList arrNumber, ArrayList arrClub)
    {
        this.ctx = ctx;
        this.arrName = arrName;
        this.arrNumber = arrNumber;
        this.arrClub = arrClub;
    }
    @NonNull
    @Override
    public ViewHolderPlayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item_player, parent, false);
        return new ViewHolderPlayer(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPlayer holder, int position)
    {
        holder.tvName.setText(arrName.get(position).toString());
        holder.tvNumber.setText(arrNumber.get(position).toString());
        holder.tvClub.setText(arrClub.get(position).toString());
    }
    @Override
    public int getItemCount()
    {
        return arrName.size();
    }
    public class ViewHolderPlayer extends RecyclerView.ViewHolder
    {
        private TextView tvName, tvNumber, tvClub;
        public ViewHolderPlayer(@NonNull View itemView)
        {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvClub = itemView.findViewById(R.id.tv_club);
        }
    }
}