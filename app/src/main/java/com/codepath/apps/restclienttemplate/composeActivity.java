package com.codepath.apps.restclienttemplate;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class composeActivity extends AppCompatActivity {
    public static final int MAX_TWEET_LENGTH = 280;
    EditText etCompose;
    Button btnTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        etCompose= findViewById(R.id.etCompose);
        btnTweet= findViewById(R.id.btnTweet);
        btnTweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String content= etCompose.getText().toString();
               if(content.isEmpty()){
                   Toast.makeText(composeActivity.this, "Sorry, Your Tweet cannot be empty", Toast.LENGTH_LONG).show();
                   return;
               }
               if (content.length()> MAX_TWEET_LENGTH){
                   Toast.makeText(composeActivity.this, "Sorry, Your Tweet is Too Long", Toast.LENGTH_LONG).show();
                   return;
               }
                Toast.makeText(composeActivity.this, ""+content, Toast.LENGTH_LONG).show();
                return;
            }
        });

    }


}