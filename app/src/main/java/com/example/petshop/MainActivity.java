package com.example.petshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.prefs.QuanLyPrefs;
import com.example.petshop.ui.ManHinhChinhActivity;
import com.example.petshop.ui.ManHinhDangNhapActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent;
        if (QuanLyPrefs.daDangNhap(this)) {
            intent = new Intent(this, ManHinhChinhActivity.class);
        } else {
            intent = new Intent(this, ManHinhDangNhapActivity.class);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
