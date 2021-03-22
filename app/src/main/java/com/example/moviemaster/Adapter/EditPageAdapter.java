package com.example.moviemaster.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviemaster.Database.Constant;
import com.example.moviemaster.Activity.EditMovie;
import com.example.moviemaster.ModelClass.Movie;
import com.example.moviemaster.R;

import java.util.List;

public class EditPageAdapter extends RecyclerView.Adapter<EditPageAdapter.ItemViewHolder> {

  List<Movie> movieList;
  Context context;

    public EditPageAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;

    }

    @NonNull
    @Override
    public EditPageAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //creating a item view for recycler view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display_movie_list_edit_rv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditPageAdapter.ItemViewHolder holder, int position) {
//set data into an item view
            holder.title.setText(movieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //passing movie id into edit activity
                    Intent intent=new Intent(context,EditMovie.class);
                    intent.putExtra(Constant.MOVIEID,movieList.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
