package com.example.stephane.youviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by stephane on 21/05/2018.
 */

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SECOND ACTIVITY";
    IntentFilter intentFilter ;
    MovieAdapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        intentFilter = new IntentFilter(MainActivity.MOVIE_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new MovieUpdate(), intentFilter);

        Intent intent = new Intent(this, Moviedatabase.class);
        intent.setAction(MainActivity.MOVIE_UPDATE);
        this.startService(intent);

        RecyclerView rv_movie = (RecyclerView)findViewById(R.id.rv_movie);
        rv_movie.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ma = new MovieAdapter(getMOVIESFromFile());
        rv_movie.setAdapter(ma);

    }

    public class MovieUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, intent.getAction());
            Toast.makeText(getApplicationContext(), R.string.dwl, Toast.LENGTH_SHORT).show();
            ma.setNewMovie(getMOVIESFromFile());
        }
    }

    private class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

        private JSONArray movies;

        public MovieAdapter(JSONArray movies) {
            this.movies = movies;
        }

        @Override
        public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            MovieHolder mh = new MovieHolder(inflater.inflate(R.layout.rv_movie_element, parent, false));
            return mh;
        }

        @Override
        public void onBindViewHolder(MovieHolder holder, int position) {
            try {
                holder.name.setText(movies.getJSONObject(position).get("name").toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {

            return movies.length();
        }

        public class MovieHolder extends RecyclerView.ViewHolder {
            TextView name;

            public MovieHolder(View itemView) {
                super(itemView);
                name = (TextView)itemView.findViewById(R.id.rv_movie_element_name);
            }
        }

        public void setNewMovie(JSONArray movies) {
            this.movies = movies;
            notifyDataSetChanged();
        }
    }



    public JSONArray getMOVIESFromFile() {
        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "biers.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        } catch(IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }



    public void clickButton2(View view) {
        // Intent
        Intent j = new Intent(this, MainActivity.class);
        startActivity(j);
    }
}