package com.codepath.Blanjina.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.Blanjina.flixster.R;
import com.codepath.Blanjina.flixster.models.Movie;

import java.util.List;




public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Movie> movies;
    public static int populaire= 0;
    public static int m_populaire = 1;


    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies=movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Log.d("MovieAdapter","onCreateViewHolder");

        if (viewType==0){
            View movieView= LayoutInflater.from(context).inflate(R.layout.popular_movies,parent,false);
            viewHolder=new  ViewHolder1(movieView);
        }
        else{
            View movieView= LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);

            viewHolder= new ViewHolder(movieView);
        }



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("MovieAdapter","onBindViewHolder"+ position);
        Movie movie= movies.get(position);

        if (holder.getItemViewType()==0){
            ViewHolder1 v = (ViewHolder1) holder;
            v.bind(movie);

        }
        else{
            ViewHolder l = (ViewHolder) holder;
            l.bind(movie);
        }

    
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getRating() >=5) {
            return populaire;
        }
        else  {
            return m_populaire;
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvOverview=itemView.findViewById(R.id.tvOverview);
            ivPoster= itemView.findViewById(R.id.ivPoster);


        }

        public void bind(Movie movie) {
            String imageURL;
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            if(context.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                imageURL=movie.getBackdropPath();
            }
            else{
                imageURL=movie.getPosterPath();
            }

           Glide.with(context).load(imageURL)
                   .placeholder(R.drawable.image4)
                   .into(ivPoster);

        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder1(View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.tVImage);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }

        public void bind(Movie movie) {
            Glide.with(context).load(movie.getBackdropPath())
                    .placeholder(R.drawable.image4)
                    .into(imageView);

        }


    }



}
