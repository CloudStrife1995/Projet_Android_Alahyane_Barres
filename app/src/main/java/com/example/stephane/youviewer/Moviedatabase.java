package com.example.stephane.youviewer;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by stephane on 29/05/2018.
 */

public class Moviedatabase extends IntentService {
    //TODO:
    private static final String ACTION_FOO = "com.example.stephane.youviewer.action.FOO";
    private static final String TAG = "Quel bon film";

    public Moviedatabase() {

        super("Moviedatabase");
    }

    public static void startActionFoo(Context context) {
        Intent intent = new Intent(context, Moviedatabase.class);
        intent.setAction(ACTION_FOO);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (MainActivity.MOVIE_UPDATE.equals(action)) {
                handleActionMovie();
            }
        }
    }


    public void handleActionMovie() {
        Log.d(TAG, "Thread service name: " + Thread.currentThread().getName());
        URL url = null;

        try {
            url = new URL("http://www.omdbapi.com/?i=tt3896198&apikey=be8f850a");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if(HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                copyInputStreamToFile(conn.getInputStream(), new File(getCacheDir(), "movie.json"));
                Log.d(TAG, "Film downloaded");
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(MainActivity.MOVIE_UPDATE));
            } else {
                Log.e(TAG, "CONNECTION ERROR" + conn.getResponseCode());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0) {
                out.write(buf,0,len);
            }
            out.close();
            in.close();
            Log.d(TAG, "Finished loading this file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleActionFoo() {
        Log.d(TAG, "handled foo");
    }




}
