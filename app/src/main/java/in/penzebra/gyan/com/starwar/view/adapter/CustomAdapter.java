package in.penzebra.gyan.com.starwar.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.penzebra.gyan.com.starwar.R;
import in.penzebra.gyan.com.starwar.model.ResponseResult;
import in.penzebra.gyan.com.starwar.view.MainActivity;
import in.penzebra.gyan.com.starwar.view.UserDetailActivity;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList<ResponseResult.Result> dataSet;

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);

            textViewName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ctx, UserDetailActivity.class);
                    intent.putExtra("name", dataSet.get(getAdapterPosition()).getName());
                    intent.putExtra("height", dataSet.get(getAdapterPosition()).getHeight());
                    intent.putExtra("mass", dataSet.get(getAdapterPosition()).getMass());
                    intent.putExtra("dateNtime", dataSet.get(getAdapterPosition()).getCreated());
                    ctx.startActivity(intent);

                }
            });

        }
    }


    public CustomAdapter(Context ctx, ArrayList<ResponseResult.Result> data) {
        this.ctx = ctx;
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.user_list_adapter, parent, false);



        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        textViewName.setText(dataSet.get(listPosition).getName());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
