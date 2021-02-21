package com.example.myfilmqrcode.remote;

import com.example.myfilmqrcode.data.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {
    @GET(EndPoints.GET_FILMS)
    Call<List<Film>> getFilm();

    @GET(EndPoints.GET_FILM_ID)
    Call<Film> getFilmId(
            @Path("id") String id
    );
}
