package com.example.mustafa.playlist_youtube;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mustafa.playlist_youtube.model.Video;
import com.google.android.youtube.player.YouTubeStandalonePlayer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String YOUTUBE_API_KEY ="AIzaSyDYDiQQKgXNKk2A4kU3P-9Pa0dA5GZEfZE";

    public Button mLoadRss;
    public ListView mListViewVideos;
    public Video video;
    public String idVideo;

    private String mContentFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoadRss = findViewById(R.id.button_mostrar_play_list);
        mListViewVideos= findViewById(R.id.listViewPlayList);

        mLoadRss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Parse parse = new Parse(mContentFile);
                parse.process();

                EntAdapter adapterVideo= new EntAdapter(
                        MainActivity.this,
                        parse.getVideos()
                );

                mListViewVideos.setAdapter(adapterVideo);

                mListViewVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        video = parse.getVideo(position);
                        idVideo= video.getId();
                        Intent intent  = YouTubeStandalonePlayer.createVideoIntent(
                                MainActivity.this,
                                YOUTUBE_API_KEY,
                                idVideo
                        );
                        startActivity(intent);
                    }
                });
            }
        });

        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://www.youtube.com/feeds/videos.xml?playlist_id=RDQMj7l7QmN9YTM");

    }

    private class DownloadData extends AsyncTask<String, Void, String>{

        private static final String TAG= "DownloadData";

        @Override
        protected String doInBackground(String... strings) {
            mContentFile = downloadXmlFie(strings[0]);

            if (mContentFile == null){
                Log.d(TAG,"Hemos tenido un problema descargarndo el archivo XML");
            }

            return mContentFile;
        }


        public String downloadXmlFie(String urlPath){

            StringBuilder tempBuffer = new StringBuilder();

            try{
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response= connection.getResponseCode();
                Log.d(TAG, "Response Code: "+response);

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int charsRead;
                char[] inputBuffer = new char[500];

                while (true){
                    charsRead = inputStreamReader.read(inputBuffer);

                    if (charsRead <= 0){
                        break;
                    }

                    tempBuffer.append(String.copyValueOf(inputBuffer,0,charsRead));
                }

                return tempBuffer.toString();
            }catch (IOException e){
                Log.d(TAG,"UPSS.. Hemos tenido un problema al descargar el RSS: "+ e.getMessage());

            }

            return null;
        }


        protected void onPostExecute(String result){
            super.onPostExecute(result);
        }
    }
}
