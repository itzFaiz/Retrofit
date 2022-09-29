package com.mindorks.framework.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindorks.framework.retrofit.adapter.RecyclerAdapter;
import com.mindorks.framework.retrofit.data.ApiClient;
import com.mindorks.framework.retrofit.data.ApiInterface;
import com.mindorks.framework.retrofit.databinding.ActivityMainBinding;
import com.mindorks.framework.retrofit.model.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<Results> resultsList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        recyclerView = binding.rvResult;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        resultsList = new ArrayList<>();
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),resultsList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Results>> call = apiInterface.getMovies();

        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {
                resultsList = response.body();
                Log.d("TAG", "onResponse: " +resultsList);
                recyclerAdapter.setMovieList(resultsList);
            }

            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.toString());
            }
        });
    }
}
