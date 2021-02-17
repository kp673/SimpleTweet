package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;
    //pass in context and list tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    //For each row inflate layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.item_tweet,parent,false);
        return new ViewHolder(view);
    }
    // Bind values based on position of element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get data
        Tweet tweet = tweets.get(position);
        //bind data
        holder.bind(tweet);
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }







    // define Viewholder

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvbody;
        TextView tvScreenName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage= itemView.findViewById(R.id.ivProfileImage);
            tvbody= itemView.findViewById(R.id.tvbody);
            tvScreenName= itemView.findViewById(R.id.tvScreenName);
        }

        public void bind(Tweet tweet) {
            tvbody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            GlideApp.with(context).load(tweet.user.profileImageURL).into(ivProfileImage);
        }
    }
}
