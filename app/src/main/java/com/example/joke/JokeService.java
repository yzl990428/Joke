package com.example.joke;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JokeService {
    @GET("jokes/random")
    Call<Joke> getJoke(@Query("category") String category);
}
