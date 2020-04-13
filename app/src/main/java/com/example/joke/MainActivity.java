package com.example.joke;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String TAG="JOKE";
    private TextView tvContent;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = findViewById(R.id.tv_content);
        mProgressDialog = new ProgressDialog(this);
        getJoke();
    }
    private void getJoke(){
        mProgressDialog.show();
        JokeService jokeService =  RetrofitUtils.getService();
        Call<Joke> callJoke = jokeService.getJoke("dev");
        callJoke.enqueue(new Callback<Joke>() {
            @Override
            public void onResponse(Call<Joke> call, Response<Joke> response) {
                mProgressDialog.dismiss();
                if (response.isSuccessful()){
                    Joke joke = response.body();
                    Log.d(TAG,"onResponse  Success "+joke.toString());
                    tvContent.setText(joke.getValue());
                }else{
                    Log.d(TAG,"onResponse error "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Joke> call, Throwable t) {
                mProgressDialog.dismiss();
                Log.d(TAG,"onFailure "+t.getLocalizedMessage());
            }
        });
    }

    public void getData(View view) {
        getJoke();
    }
}
