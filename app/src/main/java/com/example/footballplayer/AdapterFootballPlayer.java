package com.example.footballplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPlayer>
{
    private Context ctx;
    private ArrayList arrID, arrName, arrNumber, arrClub;
    public AdapterFootballPlayer(Context ctx, ArrayList arrID, ArrayList arrName, ArrayList arrNumber, ArrayList arrClub)
    {
        this.ctx = ctx;
        this.arrID = arrID;
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
        holder.tvID.setText(arrID.get(position).toString());
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
        private TextView tvID, tvName, tvNumber, tvClub;
        public ViewHolderPlayer(@NonNull View itemView)
        {
            super(itemView);
            tvID = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
            tvClub = itemView.findViewById(R.id.tv_club);
            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    AlertDialog.Builder message = new AlertDialog.Builder(ctx);
                    message.setTitle("Caution!");
                    message.setMessage("Anda memilih " + tvName.getText().toString() + ". Pilih perintah yang diinginkan");
                    message.setCancelable(true);
                    message.setPositiveButton("Ubah", new DialogInterface.OnClickListener()
                            {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent intent = new Intent(ctx, ChangeActivity.class);
                            intent.putExtra("varID", tvID.getText().toString());
                            intent.putExtra("varName", tvName.getText().toString());
                            intent.putExtra("varNumber", tvNumber.getText().toString());
                            intent.putExtra("varClub", tvClub.getText().toString());
                            ctx.startActivity(intent);
                        }
                    }
                    );
                    message.setNegativeButton("Hapus", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                            long exec = myDB.deletePlayer(tvID.getText().toString());
                            if (exec == -1)
                            {
                                Toast.makeText(ctx, "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ctx, "Sukses menghapus data", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();
                            }
                        }
                    }
                    );
                    message.show();
                    return false;
                }
            }
            );
        }
    }
}