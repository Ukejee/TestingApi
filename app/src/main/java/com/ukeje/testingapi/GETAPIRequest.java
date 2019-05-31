package com.ukeje.testingapi;

import android.content.Context;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author .: Ukeje Emeka
 * @email ..: ukejee3@gmail.com
 * @created : 5/28/19
 */
public class GETAPIRequest {

    public void request(final Context context, final FetchDataListener listener, final String ApiUrl)
       throws JSONException {

        if(listener != null){
            //call onFetchComplete of the listener
            //to show progress dialog
            //-indicates calling started
            listener.onFetchStart();
        }

        //base server url
        String baseUrl = "https://restcountries.eu/rest/";
        //add extension api url received from caller
        //and make full api
        String url = baseUrl + ApiUrl;

        JsonArrayRequest postRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response){

                try{
                    if(listener != null){

                        if(response != null){
                            //received response
                            //call onFetchComplete of the listener
                            listener.onFetchComplete(response);
                        }
                        else if(response == null){
                            //has error in response
                            //call onFetchFailure of the listener
                            listener.onFetchFailure("error");
                        }
                        else{
                            listener.onFetchComplete(null);
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NoConnectionError) {
                    listener.onFetchFailure("Network Connectivity Problem");
                } else if (error.networkResponse != null && error.networkResponse.data != null) {
                    VolleyError volley_error = new VolleyError(new String(error.networkResponse.data));
                    String errorMessage = "";
                    try {
                        JSONArray errorJson = new JSONArray(volley_error.getMessage().toString());
                        if (errorJson != null) errorMessage = errorJson.toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (errorMessage.isEmpty()) {
                        errorMessage = volley_error.toString();
                    }

                    if (listener != null) listener.onFetchFailure(errorMessage);
                } else {
                    listener.onFetchFailure("Something went wrong. Please try again later");
                }
            }
            });

        RequestQueueService.getInstance(context).addToRequestQueue(postRequest.setShouldCache(false));
    }
}
