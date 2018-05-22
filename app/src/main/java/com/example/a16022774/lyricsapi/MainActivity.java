package com.example.a16022774.lyricsapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText etArtist, etSong;
    Button btnSearch;
    TextView tvLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etArtist = (EditText) findViewById(R.id.etArtist);
        etSong = (EditText) findViewById(R.id.etSong);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        tvLyrics = (TextView) findViewById(R.id.tvLyrics);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for step 1 start
                HttpRequest request = new HttpRequest
                        ("https://orion.apiseeds.com/api/music/lyric/" + etArtist.getText().toString() + "/" + etSong.getText().toString() + "?apikey=DUAz45kb9QxAUTB0qDl2TZYWIWghJbvyHt6G8MHEtjYqvi66ZzzYlZZRSvVplc3I");
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                // Code for step 1 end
            }
        });

    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject result = jsonObject.getJSONObject("result");
                        JSONObject artist = result.getJSONObject("artist");
                        String name = artist.getString("name");
                        JSONObject track = result.getJSONObject("track");
                        String lyrics = track.getString("text");

                        tvLyrics.setText("Artist: " + name + "\n\n" + "Lyrics:\n" + lyrics);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            };
    // Code for step 2 end
}
