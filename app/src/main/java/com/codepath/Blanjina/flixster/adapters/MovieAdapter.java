package com.codepath.Blanjina.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.Blanjina.flixster.DetailActivity;
import com.codepath.Blanjina.flixster.R;
import com.codepath.Blanjina.flixster.models.Movie;

import org.parceler.Parcels;

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
        Log.d("MovieAdapter", "onCreateViewHolder");
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 0){
            View morePop = inflater.inflate(R.layout.popular_movies, parent, false);
            viewHolder = new ViewHolder1(morePop);
        }else{
            View lessPop = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(lessPop);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder" + position);
        Movie movie = movies.get(position);
        if(viewHolder.getItemViewType()==0){
            ViewHolder1 viewPop=(ViewHolder1) viewHolder;
            viewPop.bind(movie);

        }
        else{
            ViewHolder viewMPop=(ViewHolder) viewHolder;
            viewMPop.bind(movie);
        }


    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getRate() >=5) {
            return populaire;
        }
        else  {
            return m_populaire;
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout container;
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
            Glide.with(context)
                    .load(imageURL)
                    .transform(new RoundedCorners(50))
                    .placeholder(R.drawable.image4)
                    .into(ivPoster);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
            }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        RelativeLayout container2;
        ImageView imageView;

        public ViewHolder1(@NonNull View v) {
            super(v);
            imageView = (ImageView) v.findViewById(R.id.tVImage);
            container2=v.findViewById(R.id.container2);


        }



        public void bind(Movie movie) {




            Glide.with(context)
                    .load(movie.getBackdropPath())
                    .transform(new RoundedCorners(50))
                    .placeholder(R.drawable.image4)
                    .into(imageView);

            container2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent k = new Intent(context, DetailActivity.class);
                    k.putExtra("movie", Parcels.wrap(movie));
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)context,imageView,"transitionMovie" );
                    context.startActivity(k,options.toBundle());
                }
            });

        }


    }



}
