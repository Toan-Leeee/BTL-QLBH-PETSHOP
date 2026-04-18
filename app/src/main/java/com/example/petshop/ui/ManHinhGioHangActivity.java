package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.GioHangItem;
import com.example.petshop.data.SanPham;
import com.example.petshop.util.MenuDieuHuongHelper;
import com.example.petshop.util.XacThucHelper;
import com.example.petshop.util.adapter.GioHangAdapter;

import java.util.List;

public class ManHinhGioHangActivity extends AppCompatActivity {

    private RecyclerView rvGioHang;
    private Button btnXoa;
    private Button btnXacNhan;
    private ImageView btnMenu;

    private GioHangAdapter adapter;
    private List<GioHangItem> dsGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_gio_hang);

        anhXa();
        setupDanhSach();
        setupSuKien();
    }

    private void anhXa() {
        rvGioHang = findViewById(R.id.rvGioHang);
        btnXoa = findViewById(R.id.btnXoa);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnMenu = findViewById(R.id.btnMenu);
    }

    private void setupDanhSach() {
        dsGioHang = DuLieuMau.layDanhSachGioHang();

        if (dsGioHang.isEmpty()) {
            List<SanPham> dsSanPham = DuLieuMau.layDanhSachSanPham();
            if (!dsSanPham.isEmpty()) {
                DuLieuMau.themVaoGioHang(dsSanPham.get(0), 1);
            }
            if (dsSanPham.size() > 1) {
                DuLieuMau.themVaoGioHang(dsSanPham.get(1), 1);
            }
        }

        adapter = new GioHangAdapter(this, dsGioHang, () -> {
            // reserved for future cart total UI
        });

        rvGioHang.setLayoutManager(new LinearLayoutManager(this));
        rvGioHang.setAdapter(adapter);
    }

    private void setupSuKien() {
        btnMenu.setOnClickListener(this::moMenuDieuHuong);

        btnXoa.setOnClickListener(v -> {
            int soDaXoa = adapter.xoaDaChon();
            if (soDaXoa == 0) {
                Toast.makeText(this, "Ban chua chon san pham de xoa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Da xoa " + soDaXoa + " san pham", Toast.LENGTH_SHORT).show();
            }
        });

        btnXacNhan.setOnClickListener(v -> {
            if (dsGioHang.isEmpty()) {
                Toast.makeText(this, "Gio hang dang trong", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, ManHinhThanhToanActivity.class));
        });
    }

    private void moMenuDieuHuong(android.view.View anchor) {
        MenuBuilder menuBuilder = new MenuBuilder(this);
        new android.widget.PopupMenu(this, anchor).getMenuInflater().inflate(R.menu.menu_drawer, menuBuilder);
        MenuDieuHuongHelper.chuanHoaIconMenu(this, menuBuilder);

        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this, menuBuilder, anchor);
        menuPopupHelper.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, android.view.MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home || id == R.id.menu_trangchu) {
                    startActivity(new Intent(ManHinhGioHangActivity.this, ManHinhChinhActivity.class));
                    return true;
                }
                if (id == R.id.nav_pet || id == R.id.menu_sanpham) {
                    startActivity(new Intent(ManHinhGioHangActivity.this, ManHinhSanPhamActivity.class));
                    return true;
                }
                if (id == R.id.nav_cart || id == R.id.menu_giohang) {
                    return true;
                }
                if (id == R.id.nav_user || id == R.id.menu_taikhoan) {
                    startActivity(new Intent(ManHinhGioHangActivity.this, ManHinhCaNhan.class));
                    return true;
                }
                return false;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {
            }
        });
        menuPopupHelper.show();
    }

}
