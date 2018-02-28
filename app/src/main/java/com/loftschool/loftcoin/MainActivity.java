package com.loftschool.loftcoin;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycler;

    private CoinsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CoinsAdapter();

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        loadData();
    }

    private void loadData() {

        Application application = getApplication();
        App app = (App) application;
        Api api = app.getApi();

        api.ticker().enqueue(new Callback<List<Coin>>() {
            @Override
            public void onResponse(Call<List<Coin>> call, Response<List<Coin>> response) {
                Log.d("MainActivity", "onResponse");

                List<Coin> coins = response.body();

                Collections.sort(coins, new Comparator<Coin>() {
                    @Override
                    public int compare(Coin o1, Coin o2) {
                        return Double.compare(o2.percentChange, o1.percentChange);
                    }
                });


                adapter.setCoins(coins);
            }

            @Override
            public void onFailure(Call<List<Coin>> call, Throwable t) {
                Log.e("MainActivity", "onFailure", t);
            }
        });
    }
}
