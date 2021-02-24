package com.codepath.apps.restclienttemplate;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

import static android.app.PendingIntent.getActivity;

public class composeActivity extends AppCompatActivity {
    public static final int MAX_TWEET_LENGTH = 280;
    public static final String TAG= "composeActivity";
    EditText etCompose;
    Button btnTweet;

    TwitterClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client= TwitterApp.getRestClient(this);

        etCompose= findViewById(R.id.etCompose);
        btnTweet= findViewById(R.id.btnTweet);
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweetContent= etCompose.getText().toString();
               if(tweetContent.isEmpty()){
                   Toast.makeText(composeActivity.this, "Sorry, Your Tweet cannot be empty", Toast.LENGTH_LONG).show();
                   Log.i(TAG, "Published: ");
                   return;
               }
               if (tweetContent.length()> MAX_TWEET_LENGTH){
                   Toast.makeText(composeActivity.this, "Sorry, Your Tweet is Too Long", Toast.LENGTH_LONG).show();
                   return;
               }
              client.publishTweet(tweetContent, new JsonHttpResponseHandler() {
                  @Override
                  public void onSuccess(int statusCode, Headers headers, JSON json) {
                      Log.i(TAG,"onSucess");
                      try {
                          Tweet tweet= Tweet.fromJson(json.jsonObject);
                          Log.i(TAG, "Published: "+tweet.body);

                          Intent i = new Intent();
                          i.putExtra("tweet", Parcels.wrap(tweet));
                          setResult(RESULT_OK, i);

                          finish();

                      } catch (JSONException e) {
                          Log.e(TAG,"fail to publish",e);
                      }
                  }
                  @Override
                  public void onFailure(int statusCode, Headers headers, String response, Throwable throwable){
                      Log.e(TAG,"onFailure to publish twweet",throwable);
                  }
              });
            }
        });

    }


}