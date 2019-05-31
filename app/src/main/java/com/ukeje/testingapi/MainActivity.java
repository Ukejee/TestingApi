package com.ukeje.testingapi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ukeje.testingapi.responses.CountryResponse;
import com.ukeje.testingapi.responses.RetroUsers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private Button getApiBtn,postApiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView  = (TextView)findViewById(R.id.resultTextView);
        getApiBtn       = (Button)findViewById(R.id.getApiBtn);
        postApiBtn      = (Button)findViewById(R.id.postApiBtn);

        TestingApiService service = RetrofitClient.getRetrofitInstance().create(TestingApiService.class);
        Call<List<CountryResponse>> call = service.getAllUsers("ghana");
        call.enqueue(new Callback<List<CountryResponse>>() {
            @Override
            public void onResponse(Call<List<CountryResponse>> call, Response<List<CountryResponse>> response) {

                if(response != null){
                    Toast.makeText(getApplicationContext(),response.body().get(0).getName(),Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Response is null",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<CountryResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Network Failure", Toast.LENGTH_LONG).show();

            }
        });

        //Attaching OnClickListener with Buttons
       // getApiBtn.setOnClickListener(getApiListener);
        //postApiBtn.setOnClickListener(postApiListener);
    }

//    View.OnClickListener getApiListener=new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            //Call getApiCall() method
//            getApiCall();
//        }
//    };

//    View.OnClickListener postApiListener=new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            //Call postApiCall() method
//            postApiCall();
//        }
//    };

//    private void getApiCall(){
//        try{
//            //Create Instance of GETAPIRequest and call it's
//            //request() method
//            GETAPIRequest getapiRequest=new GETAPIRequest();
//            //Attaching only part of URL as base URL is given
//            //in our GETAPIRequest(of course that need to be same for all case)
//            String url="/usa";
//            getapiRequest.request(MainActivity.this,fetchGetResultListener,url);
//            Toast.makeText(MainActivity.this,"GET API called",Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    //Implementing interfaces of FetchDataListener for GET api request
//    FetchDataListener fetchGetResultListener=new FetchDataListener() {
//        @Override
//        public void onFetchComplete(JSONArray data) {
//            //Fetch Complete. Now stop progress bar  or loader
//            //you started in onFetchStart
//            RequestQueueService.cancelProgressDialog();
//            try {
//                //Now check result sent by our GETAPIRequest class
//                if (data != null) {
//                  String response = data.toString();
//                  resultTextView.setText(response);
//                } else {
//                    RequestQueueService.showAlert("Error! No data fetched", MainActivity.this);
//                }
//            }catch (Exception e){
//                RequestQueueService.showAlert("Something went wrong", MainActivity.this);
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onFetchFailure(String msg) {
//            RequestQueueService.cancelProgressDialog();
//            //Show if any error message is there called from GETAPIRequest class
//            RequestQueueService.showAlert(msg,MainActivity.this);
//        }
//
//        @Override
//        public void onFetchStart() {
//            //Start showing progressbar or any loader you have
//            RequestQueueService.showProgressDialog(MainActivity.this);
//        }
//    };

//    private void postApiCall(){
//        try{
//            //Create Instance of POSTAPIRequest and call it's
//            //request() method
//            POSTAPIRequest postapiRequest=new POSTAPIRequest();
//            //Attaching only part of URL as base URL is given
//            //in our POSTAPIRequest(of course that need to be same for all case)
//            String url="webapi.php";
//            JSONObject params=new JSONObject();
//            try {
//                //Creating POST body in JSON format
//                //to send in POST request
//                params.put("userId",2);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            postapiRequest.request(MainActivity.this,fetchPostResultListener,params,url);
//            Toast.makeText(MainActivity.this,"POST API called",Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    //Implementing interfaces of FetchDataListener for POST api request
//    FetchDataListener fetchPostResultListener=new FetchDataListener() {
//        @Override
//        public void onFetchComplete(JSONObject data) {
//            //Fetch Complete. Now stop progress bar  or loader
//            //you started in onFetchStart
//            RequestQueueService.cancelProgressDialog();
//            try {
//                //Now check result sent by our POSTAPIRequest class
//                if (data != null) {
//                    if (data.has("success")) {
//                        int success = data.getInt("success");
//                        if(success==1){
//                            JSONObject response=data.getJSONObject("response");
//                            if(response!=null) {
//                                //Display the result
//                                //Or, You can do whatever you need to
//                                //do with the JSONObject
//                                resultTextView.setText(response.toString(4));
//                            }
//                        }else{
//                            RequestQueueService.showAlert("Error! No data fetched", MainActivity.this);
//                        }
//                    }
//                } else {
//                    RequestQueueService.showAlert("Error! No data fetched", MainActivity.this);
//                }
//            }catch (Exception e){
//                RequestQueueService.showAlert("Something went wrong", MainActivity.this);
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onFetchFailure(String msg) {
//            RequestQueueService.cancelProgressDialog();
//            //Show if any error message is there called from POSTAPIRequest class
//            RequestQueueService.showAlert(msg,MainActivity.this);
//        }
//
//        @Override
//        public void onFetchStart() {
//            //Start showing progressbar or any loader you have
//            RequestQueueService.showProgressDialog(MainActivity.this);
//        }
//    };

}
