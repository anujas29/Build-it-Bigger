package com.anuja.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.anuja.androidlib.ActivityJoke;

/**
 * Created by USER on 07-09-2017.
 */

public class MainFragment extends Fragment {

    Button ButtonJoke;
    private String mJoke;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButtonJoke = (Button) root.findViewById(R.id.joke_button);

        ButtonJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJokeTask();
            }
        });

        return root;

    }

    private void fetchJokeTask(){

        new EndpointsAsyncTask().execute(new JokeReceived() {
            @Override
            public void JokeReceivedListener(String joke) {
                mJoke = joke;
                if(joke!=null)
                {
                    Log.d("Inside fetchJokeTask",joke);
                    System.out.println("----------------- inside  fetchJokeTask---------------------");

                        StartActivityJoke(mJoke);

                }
                else {
                    System.out.println("----------------- inside  fetchJokeTask null---------------------"+mJoke);
                }
            }
        });


    }

    private void StartActivityJoke(String joke){
        Intent mIntent = new Intent(getActivity(),ActivityJoke.class);

        System.out.println("----------------- inside  StartActivityJoke---------------------"+joke);
        mIntent.putExtra("JOKE",joke);
        startActivity(mIntent);
    }
}