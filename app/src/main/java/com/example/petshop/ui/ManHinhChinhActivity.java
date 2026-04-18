package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.PopupMenu;
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

        btnMenu.setOnClickListener(this::moMenuDieuHuong);

        btnKhamPha.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhSanPhamActivity.class)));

        btnGioiThieu.setOnClickListener(v ->
                Toast.makeText(this, "Monito - cua hang thu cung", Toast.LENGTH_SHORT).show());

        imgAvatar.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhCaNhan.class)));
    }

    private void moMenuDieuHuong(android.view.View anchor) {
        PopupMenu popupMenu = new PopupMenu(this, anchor);
        popupMenu.getMenuInflater().inflate(R.menu.menu_drawer, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            xuLyMenuChung(item.getItemId());
            return true;
        });
        popupMenu.show();
    }

    private void xuLyMenuChung(int itemId) {
        if (itemId == R.id.nav_home || itemId == R.id.menu_trangchu) {
            return;
        }
        if (itemId == R.id.nav_pet || itemId == R.id.menu_sanpham) {
            startActivity(new Intent(this, ManHinhSanPhamActivity.class));
            return;
        }
        if (itemId == R.id.nav_cart || itemId == R.id.menu_giohang) {
            startActivity(new Intent(this, ManHinhGioHangActivity.class));
            return;
        }
        if (itemId == R.id.nav_user || itemId == R.id.menu_taikhoan) {
            startActivity(new Intent(this, ManHinhCaNhan.class));
        }
    }
}
