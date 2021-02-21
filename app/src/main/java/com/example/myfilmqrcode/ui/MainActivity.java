package com.example.myfilmqrcode.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myfilmqrcode.R;
import com.example.myfilmqrcode.data.Film;
import com.example.myfilmqrcode.remote.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FilmAdapter adapter;
    private List<Film> films = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        init();
        getFilms();
    }
    private void init() {
        recyclerView = findViewById(R.id.recyclerVFilm);
        films = new ArrayList<>();
        adapter = new FilmAdapter(films, this);
        recyclerView.setAdapter(adapter);
        adapter.setOnclickItem(position -> getFilmId(position));
    }

    private void getFilmId(int position) {
        String id = adapter.films.get(position).getId();
        RetrofitFactory.getInstance().getFilmId(id).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("film_Id", response.body().getId());
                        intent.putExtra("film_title", response.body().getTitle());
                        intent.putExtra("film_des", response.body().getDescription());
                        startActivity(intent);
                        Log.i("f_Id", response.body().getId());
                    }
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {

            }
        });
    }

    private void getFilms() {
        RetrofitFactory.getInstance().getFilm().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Film film : response.body()) {
                        adapter.setElement(film);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {

            }
        });
    }
}