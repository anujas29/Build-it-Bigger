package com.anuja.androidlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by USER on 29-08-2017.
 */

public class ActivityJoke extends AppCompatActivity {


    private String INTENTJOKE = "JOKE";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        getSupportActionBar().setTitle("JOKE");
        TextView mTextView = (TextView)findViewById(R.id.txtJoke);
        String mJoke = getIntent().getStringExtra(INTENTJOKE);

        if(mJoke!= null)
        {
            mTextView.setText(mJoke);
        }




    }
}
