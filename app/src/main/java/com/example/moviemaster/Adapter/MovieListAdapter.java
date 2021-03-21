package com.example.moviemaster.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviemaster.ModelClass.Movie;
import com.example.moviemaster.R;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ItemViewHolder> {
    public interface OnItemCheckListener {
        void onItemCheck(Movie movie);

    }
    public OnItemCheckListener onItemClick;
  List<Movie> movieList;
  Context context;

    public MovieListAdapter(List<Movie> movieList, Context context,OnItemCheckListener onItemCheckListener) {
        this.movieList = movieList;
        this.context = context;
        this.onItemClick = onItemCheckListener;
    }

    @NonNull
    @Override
    public MovieListAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display_movie_list_rv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ItemViewHolder holder, int position) {
            Movie currentItem=movieList.get(position);
            holder.title.setText(movieList.get(position).getTitle());



            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  if(holder.checkBox.isChecked()){
                      //if user checked a item
                      currentItem.setIsfavourit(1);
                      onItemClick.onItemCheck(currentItem);
                  }
                  else {
                      //if user unchecked a item
                      currentItem.setIsfavourit(0);
                      onItemClick.onItemCheck(currentItem);
                  }
                }
            });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox checkBox;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            checkBox=itemView.findViewById(R.id.checkbox);
        }
    }
}
