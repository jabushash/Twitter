package com.jamille.android.twitter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button4);
        final TwitterTask task = new TwitterTask();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.execute(editText.getText().toString());
            }
        });

    }

    private class TwitterTask extends AsyncTask<String, Void, Boolean> {

        Twitter tweet;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            String apiKeyConsumer = "aJNWywzLgOl4NVLoMvDYRdicn";
            String apiKeyConsumerSecret = "szNYY9daxmWtkiqMYkJ2XH8mGswUP03RUgHfqN3rnv5p28RSmK";
            String accessToken = "1197560578602098689-Tj09r55zKRjcr3Ds8fWpWr11pQUb43";
            String accessTokenSecret = "9ytre6S213HKqv84z8kKQkDLrdDjhLA8Z0r59jHP98jLc";

            tweet = new TwitterFactory().getInstance();

            tweet.setOAuthConsumer(apiKeyConsumer, apiKeyConsumerSecret);
            AccessToken token = new AccessToken(accessToken,
                    accessTokenSecret);

            tweet.setOAuthAccessToken(token);
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                tweet.updateStatus(strings[0]);
                return true;
            } catch (TwitterException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success)
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }
}
