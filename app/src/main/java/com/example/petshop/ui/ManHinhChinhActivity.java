package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;

public class ManHinhChinhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);

        ImageView btnMenu = findViewById(R.id.btnMenu);
        ImageView imgAvatar = findViewById(R.id.imgAvatar);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);
        Button btnKhamPha = findViewById(R.id.btnKhamPha);

        btnMenu.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhSanPhamActivity.class)));

        btnKhamPha.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhSanPhamActivity.class)));

        btnGioiThieu.setOnClickListener(v ->
                Toast.makeText(this, "Monito - cua hang thu cung", Toast.LENGTH_SHORT).show());

        imgAvatar.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhCaNhan.class)));
    }
}
