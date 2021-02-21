package com.example.myfilmqrcode.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfilmqrcode.databinding.ActivityDetailsBinding;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding ui;
    private String filmId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Intent intent = getIntent();
        if (intent != null) {
            filmId = intent.getStringExtra("film_Id");
            ui.filmId.setText(filmId);
        }
        ShowQRCode();
    }

    private void ShowQRCode() {
        BarcodeEncoder encoder = new BarcodeEncoder();
        try {
            Bitmap qrCode = encoder.encodeBitmap(filmId, BarcodeFormat.QR_CODE,250,250);
            ui.ivQr.setImageBitmap(qrCode);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

}
