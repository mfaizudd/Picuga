package com.example.bandeng.picuga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Bandeng on 7/21/2018.
 */

public class LevelRecyclerViewAdapter extends RecyclerView.Adapter<LevelRecyclerViewAdapter.ViewHolder> {
    List<LevelModel> items;
    Context context;
    GlobalFunction gf;

    public LevelRecyclerViewAdapter(List<LevelModel> items, Context context) {
        this.items = items;
        this.context = context;
        gf = new GlobalFunction(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.levels_button_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        LevelModel item = items.get(position);
        if(item.getStatus().equals("playable")) {
            holder.button.setText(String.format(Locale.getDefault(), "%d", position + 1));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gf.playBubble();
                    Intent intent = new Intent(context, Game.class);
                    intent.putExtra("level", Integer.parseInt(holder.button.getText().toString()) - 1);
                    context.startActivity(intent);
                    ((Activity)context).finish();
                }
            });
        } else if(item.getStatus().equals("locked")) {
            holder.button.setBackground(context.getResources().getDrawable(R.drawable.level_button_default));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Play more to unlock this level", Toast.LENGTH_SHORT).show();
                }
            });
        } else if(item.getStatus().equals("build")) {
            holder.button.setBackground(context.getResources().getDrawable(R.drawable.level_button_default));
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Level is not ready to be played yet (still in development)", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;

        public ViewHolder(View view) {
            super(view);
            button = view.findViewById(R.id.button_level);
        }
    }
}
