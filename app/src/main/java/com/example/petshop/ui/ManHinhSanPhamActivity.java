package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.petshop.R;
import com.google.android.material.navigation.NavigationView;

public class ManHinhSanPhamActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_san_pham);

        drawerLayout = findViewById(R.id.drawerLayout);
        ImageView btnMenu = findViewById(R.id.btnMenu);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);
        Button btnKhamPha = findViewById(R.id.btnKhamPha);
        View btnBoLoc = findViewById(R.id.btnBoLoc);
        NavigationView navigationView = findViewById(R.id.navigationView);

        btnMenu.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));
        btnGioiThieu.setOnClickListener(v ->
                Toast.makeText(this, "Danh sach san pham thu cung", Toast.LENGTH_SHORT).show());
        btnKhamPha.setOnClickListener(v -> moChiTietSanPham(0));
        btnBoLoc.setOnClickListener(v ->
                Toast.makeText(this, "Bo loc demo voi du lieu fix cung", Toast.LENGTH_SHORT).show());

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                startActivity(new Intent(this, ManHinhChinhActivity.class));
            } else if (id == R.id.nav_pet) {
                // Dang o man hinh san pham.
            } else if (id == R.id.nav_cart) {
                startActivity(new Intent(this, ManHinhGioHangActivity.class));
            } else if (id == R.id.nav_user) {
                startActivity(new Intent(this, ManHinhCaNhan.class));
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void moChiTietSanPham(int viTri) {
        Intent intent = new Intent(this, ManHinhChiTietActivity.class);
        intent.putExtra("vi_tri_san_pham", viTri);
        startActivity(intent);
    }
}
