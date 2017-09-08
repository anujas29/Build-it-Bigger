package com.anuja.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * Created by USER on 07-09-2017.
 */


@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTaskTest   {

    CountDownLatch CountdownLatch;
    String result;

    @Test
    public void DoInBackgroundTest() throws Exception{
        try {
            CountdownLatch = new CountDownLatch(1);
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.execute(new JokeReceived() {
                @Override
                public void JokeReceivedListener(String joke) {
                     result = joke;
                    CountdownLatch.countDown();
                }
            });
            CountdownLatch.await(10, TimeUnit.SECONDS);
            assertNotNull(result);
            assertFalse("JOKE IS EMPTY", result.isEmpty());


        } catch (Exception e){
            Log.e("EndpointsAsyncTaskTest","doInBackgroundTest:Timed out"+e);
        }
    }
}
