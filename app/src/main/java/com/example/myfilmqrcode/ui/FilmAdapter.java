package com.example.myfilmqrcode.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfilmqrcode.R;
import com.example.myfilmqrcode.data.Film;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.CinemaHolder> {
    OnclickItem onclickItem;
    public List<Film> films = new ArrayList<>();
    private Context context;

    public FilmAdapter(List<Film> films, Context context) {
        this.films = films;
        this.context = context;
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_vfilm_title, parent, false);
        return new CinemaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaHolder holder, int position) {
        holder.bind(films.get(position));
    }

    public void setOnclickItem(OnclickItem onclickItem) {
        this.onclickItem = onclickItem;
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setElement(Film body) {
        films.add(body);
        notifyDataSetChanged();
    }


    public class CinemaHolder extends RecyclerView.ViewHolder {
        private final TextView tvFilmTitle;

        public CinemaHolder(@NonNull View itemView) {
            super(itemView);
            tvFilmTitle = itemView.findViewById(R.id.tv_film_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onclickItem.onclickFilmTitle(getAdapterPosition());
                }
            });
        }

        public void bind(Film film) {
            tvFilmTitle.setText(film.getTitle());
        }
    }

    public interface OnclickItem {
        void onclickFilmTitle(int position);
    }
}
