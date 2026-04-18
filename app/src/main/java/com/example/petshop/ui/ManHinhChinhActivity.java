package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;
import com.example.petshop.util.MenuDieuHuongHelper;
import com.example.petshop.util.XacThucHelper;

public class ManHinhChinhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_chinh);

        ImageView btnMenu = findViewById(R.id.btnMenu);
        ImageView imgAvatar = findViewById(R.id.imgAvatar);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);
        Button btnKhamPha = findViewById(R.id.btnKhamPha);
        View cardPet1 = findViewById(R.id.cardPet1);
        View cardPet2 = findViewById(R.id.cardPet2);
        View cardPet3 = findViewById(R.id.cardPet3);
        View cardPet4 = findViewById(R.id.cardPet4);

        btnMenu.setOnClickListener(this::moMenuDieuHuong);

        btnKhamPha.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhSanPhamActivity.class)));

        btnGioiThieu.setOnClickListener(v ->
                Toast.makeText(this, "Monito - cua hang thu cung", Toast.LENGTH_SHORT).show());

        imgAvatar.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhCaNhan.class)));

        cardPet1.setOnClickListener(v -> moChiTietSanPham(0));
        cardPet2.setOnClickListener(v -> moChiTietSanPham(1));
        cardPet3.setOnClickListener(v -> moChiTietSanPham(2));
        cardPet4.setOnClickListener(v -> moChiTietSanPham(3));
    }

    private void moChiTietSanPham(int viTri) {
        Intent intent = new Intent(this, ManHinhChiTietActivity.class);
        intent.putExtra("vi_tri_san_pham", viTri);
        startActivity(intent);
    }

    private void moMenuDieuHuong(android.view.View anchor) {
        MenuBuilder menuBuilder = new MenuBuilder(this);
        new PopupMenu(this, anchor).getMenuInflater().inflate(R.menu.menu_drawer, menuBuilder);
        MenuDieuHuongHelper.chuanHoaIconMenu(this, menuBuilder);

        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this, menuBuilder, anchor);
        menuPopupHelper.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, android.view.MenuItem item) {
                xuLyMenuChung(item.getItemId());
                return true;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {
            }
        });
        menuPopupHelper.show();
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
