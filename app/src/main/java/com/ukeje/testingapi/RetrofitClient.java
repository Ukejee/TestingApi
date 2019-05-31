package com.ukeje.testingapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author .: Ukeje Emeka
 * @email ..: ukejee3@gmail.com
 * @created : 5/28/19
 */
public class RetrofitClient {

    private static Retrofit retrofit;

    //define the base url
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/";

    //create the retrofit instance

    public static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())    //add the converter
                    .build();                                          //build the retrofit instance
        }
        return  retrofit;

    }
}
