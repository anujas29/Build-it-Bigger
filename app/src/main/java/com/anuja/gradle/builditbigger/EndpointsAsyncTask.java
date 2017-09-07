package com.anuja.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.anuja.joke.endpoint.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by USER on 31-08-2017.
 */

public class EndpointsAsyncTask extends AsyncTask<JokeReceived, Void, String> {

    private static MyApi myApiService = null;
    private String URL = "http://192.168.1.17:8080/_ah/api/";
    private JokeReceived mlistener;


    @Override
    protected String doInBackground(JokeReceived... params) {

        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(URL)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        mlistener = params[0];

        try {
            String JokeStr = myApiService.getJoke().execute().getData();
            Log.d("Inside EndPointAsyncTas",JokeStr);
            return JokeStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(String result) {
        mlistener.JokeReceivedListener(result);
    }
}
