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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by USER on 07-09-2017.
 */

public class MainFragment extends Fragment {

    Button btnJoke;
    private InterstitialAd mInterstitialAd;
    private String mJoke;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        btnJoke = (Button) root.findViewById(R.id.joke_button);

        btnJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJokeTask();
            }
        });

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                StartActivityJoke(mJoke);
                LoadInterstitialAd();
            }
        });

        LoadInterstitialAd();
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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
                    if(mInterstitialAd.isLoaded())
                    {
                        mInterstitialAd.show();
                    }
                    else
                    {
                        StartActivityJoke(mJoke);
                    }
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

    private void LoadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
