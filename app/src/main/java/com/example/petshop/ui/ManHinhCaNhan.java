package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import com.example.petshop.R;
import com.example.petshop.data.DonHang;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.TaiKhoan;
import com.example.petshop.prefs.QuanLyPrefs;
import com.example.petshop.util.MenuDieuHuongHelper;
import com.example.petshop.util.TienIch;
import com.example.petshop.util.XacThucHelper;

import java.util.ArrayList;
import java.util.List;

public class ManHinhCaNhan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_ca_nhan);

        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtPhone = findViewById(R.id.txtPhone);
        TextView txtOrders = findViewById(R.id.txtOrders);
        LinearLayout layoutRecentOrders = findViewById(R.id.layoutRecentOrders);
        Button btnLogout = findViewById(R.id.btnLogout);
        ImageView btnMenu = findViewById(R.id.btnMenu);

        String tenDangNhap = QuanLyPrefs.layTenDangNhap(this);
        String hoTen = QuanLyPrefs.layHoTen(this);
        if (tenDangNhap.isEmpty()) {
            tenDangNhap = "Khach";
        }
        TaiKhoan taiKhoan = DuLieuMau.timTaiKhoanTheoTenDangNhap(tenDangNhap);
        if (hoTen.isEmpty() && taiKhoan != null) {
            hoTen = taiKhoan.getHoTen();
        }
        if (hoTen.isEmpty()) {
            hoTen = tenDangNhap;
        }

        txtUsername.setText(hoTen);
        txtPhone.setText(taoThongTinPhu(taiKhoan, tenDangNhap));

        List<DonHang> dsDonHangTheoTaiKhoan = locDonHangTheoTaiKhoan(tenDangNhap);
        if (dsDonHangTheoTaiKhoan.isEmpty()) {
            txtOrders.setVisibility(View.VISIBLE);
            txtOrders.setText("Chua co don hang nao gan day");
        } else {
            txtOrders.setVisibility(View.GONE);
            hienThiDonHangGanDay(layoutRecentOrders, dsDonHangTheoTaiKhoan);
        }

        layoutRecentOrders.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhDonHangActivity.class)));
        txtOrders.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhDonHangActivity.class)));
        btnMenu.setOnClickListener(this::moMenuDieuHuong);

        btnLogout.setOnClickListener(v -> {
            QuanLyPrefs.dangXuat(this);
            Intent intent = new Intent(this, ManHinhDangNhapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private List<DonHang> locDonHangTheoTaiKhoan(String tenDangNhap) {
        List<DonHang> ketQua = new ArrayList<>();
        for (DonHang donHang : DuLieuMau.layDanhSachDonHang()) {
            if (donHang.getTenDangNhap().equalsIgnoreCase(tenDangNhap)) {
                ketQua.add(donHang);
            }
        }
        return ketQua;
    }

    private String taoThongTinPhu(TaiKhoan taiKhoan, String tenDangNhap) {
        if (taiKhoan != null && !taiKhoan.getEmail().isEmpty()) {
            return taiKhoan.getEmail();
        }
        return tenDangNhap + "@petshop.local";
    }

    private void hienThiDonHangGanDay(LinearLayout container, List<DonHang> dsDonHang) {
        container.removeAllViews();
        int soLuongHienThi = Math.min(3, dsDonHang.size());
        for (int i = 0; i < soLuongHienThi; i++) {
            DonHang donHang = dsDonHang.get(i);

            LinearLayout item = new LinearLayout(this);
            item.setOrientation(LinearLayout.VERTICAL);
            item.setPadding(0, 0, 0, 18);
            item.setClickable(true);
            item.setOnClickListener(v ->
                    startActivity(new Intent(this, ManHinhDonHangActivity.class)));

            TextView tvMa = new TextView(this);
            tvMa.setText(donHang.getMaDon() + " - " + donHang.getTrangThai());
            tvMa.setTextSize(15f);
            tvMa.setTextColor(0xFF1B1B1B);
            tvMa.setTypeface(tvMa.getTypeface(), android.graphics.Typeface.BOLD);

            TextView tvTien = new TextView(this);
            tvTien.setText(TienIch.dinhDangTien(donHang.getTongTien()));
            tvTien.setTextSize(16f);
            tvTien.setTextColor(0xFF0B3B68);
            tvTien.setPadding(0, 8, 0, 0);
            tvTien.setTypeface(tvTien.getTypeface(), android.graphics.Typeface.BOLD);

            TextView tvPhuongThuc = new TextView(this);
            tvPhuongThuc.setText("Thanh toan: " + donHang.getPhuongThucThanhToan());
            tvPhuongThuc.setTextSize(13f);
            tvPhuongThuc.setTextColor(0xFF7A7A7A);
            tvPhuongThuc.setPadding(0, 8, 0, 0);

            View divider = new View(this);
            LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 1);
            dividerParams.topMargin = 16;
            divider.setLayoutParams(dividerParams);
            divider.setBackgroundColor(0xFFE9DEC8);

            item.addView(tvMa);
            item.addView(tvTien);
            item.addView(tvPhuongThuc);
            if (i < soLuongHienThi - 1) {
                item.addView(divider);
            }

            container.addView(item);
        }
    }

    private void moMenuDieuHuong(View anchor) {
        MenuBuilder menuBuilder = new MenuBuilder(this);
        new PopupMenu(this, anchor).getMenuInflater().inflate(R.menu.menu_drawer, menuBuilder);
        MenuDieuHuongHelper.chuanHoaIconMenu(this, menuBuilder);

        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this, menuBuilder, anchor);
        menuPopupHelper.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, android.view.MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home || id == R.id.menu_trangchu) {
                    startActivity(new Intent(ManHinhCaNhan.this, ManHinhChinhActivity.class));
                    return true;
                }
                if (id == R.id.nav_pet || id == R.id.menu_sanpham) {
                    startActivity(new Intent(ManHinhCaNhan.this, ManHinhSanPhamActivity.class));
                    return true;
                }
                if (id == R.id.nav_cart || id == R.id.menu_giohang) {
                    startActivity(new Intent(ManHinhCaNhan.this, ManHinhGioHangActivity.class));
                    return true;
                }
                if (id == R.id.nav_user || id == R.id.menu_taikhoan) {
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
