package com.ukeje.testingapi;

import com.ukeje.testingapi.responses.CountryResponse;
import com.ukeje.testingapi.responses.RetroUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author .: Ukeje Emeka
 * @email ..: ukejee3@gmail.com
 * @created : 5/28/19
 */
public interface TestingApiService {

    @GET("name/{name}")
    Call<List<CountryResponse>> getAllUsers(@Path("name") String countryName);
}
