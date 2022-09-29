package com.mindorks.framework.retrofit.data;

import com.mindorks.framework.retrofit.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("volley_array.json")
    Call<List<Results>> getMovies();
}
