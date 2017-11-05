/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.anuja.joke.endpoint.backend;

import com.anuja.javalib.JokeProvider;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.endpoint.joke.anuja.com",
                ownerName = "backend.endpoint.joke.anuja.com",
                packagePath = ""
        )
)
public class MyEndpoint {

        //A simple endpoints method that provide a joke
    @ApiMethod(name = "getJoke")
    public MyBean getJoke(){
        JokeProvider obj = new JokeProvider();
        MyBean response = new MyBean();
        response.setData(obj.GetJoke());
        return response;
    }

}
