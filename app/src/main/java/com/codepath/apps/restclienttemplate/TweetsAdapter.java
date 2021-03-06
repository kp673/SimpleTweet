package com.codepath.apps.restclienttemplate;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;
import java.util.regex.Pattern;

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

    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // define Viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivProfileImage;
        TextView tvbody;
        TextView tvScreenName;
        TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage= itemView.findViewById(R.id.ivProfileImage);
            tvbody= itemView.findViewById(R.id.tvbody);
            tvScreenName= itemView.findViewById(R.id.tvScreenName);
            tvDate= itemView.findViewById(R.id.tvDate);
        }

        public void bind(Tweet tweet) {
            tvbody.setText(tweet.body);
            new PatternEditableBuilder().
                    addPattern(Pattern.compile("\\@(\\w+)"),Color.parseColor("#ff1da1f2"),
                            new PatternEditableBuilder.SpannableClickedListener() {
                                @Override
                                public void onSpanClicked(String text) {
                                    Toast.makeText(context, "Clicked username: " + text,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }).into(tvbody);
            tvScreenName.setText(tweet.user.screenName);
            GlideApp.with(context).load(tweet.user.profileImageURL).into(ivProfileImage);
            tvDate.setText(tweet.formattedTime);
        }
    }
}
