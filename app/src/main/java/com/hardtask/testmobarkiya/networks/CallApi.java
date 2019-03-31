package com.hardtask.testmobarkiya.networks;

import retrofit.RestAdapter;

/**
 * Created by it_ah on 27/03/2019.
 */

public class CallApi
{
    static   APIInterface apiInterface = null;

    public static synchronized APIInterface getAPI()
    {
        if (apiInterface==null)
        {
            RestAdapter adapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)

                    .setEndpoint(UrlsApi.BASEURL)

                    .build();

            apiInterface = adapter.create(APIInterface.class);
        }

        return apiInterface ;
    }
}
