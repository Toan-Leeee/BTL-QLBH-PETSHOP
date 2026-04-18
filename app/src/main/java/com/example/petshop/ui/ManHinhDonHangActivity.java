package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.petshop.R;
import com.example.petshop.data.DonHang;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.util.MenuDieuHuongHelper;
import com.example.petshop.util.TienIch;
import com.example.petshop.util.XacThucHelper;

import java.util.List;

public class ManHinhDonHangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_don_hang);

        ConstraintLayout root = findViewById(R.id.main);
        ImageView btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(this::moMenuDieuHuong);
        taoNoiDung(root);
    }

    private void taoNoiDung(ConstraintLayout root) {
        List<DonHang> dsDonHang = DuLieuMau.layDanhSachDonHang();
        String maDonMoi = getIntent().getStringExtra("ma_don");

        LinearLayout box = new LinearLayout(this);
        box.setId(View.generateViewId());
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(48, 48, 48, 48);
        box.setBackgroundColor(0xFFF5EEDC);

        ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(32, 120, 32, 32);
        lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID;
        lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        box.setLayoutParams(lp);

        TextView tvTitle = new TextView(this);
        tvTitle.setText("Don hang");
        tvTitle.setTextSize(24f);
        tvTitle.setTextColor(0xFF0B3B68);

        TextView tvCount = new TextView(this);
        tvCount.setText("Tong so don: " + dsDonHang.size());
        tvCount.setTextSize(16f);

        box.addView(tvTitle);
        box.addView(tvCount);

        if (maDonMoi != null && !maDonMoi.isEmpty()) {
            DonHang donMoi = dsDonHang.get(0);

            TextView tvNew = new TextView(this);
            tvNew.setText("Don moi: " + maDonMoi);
            tvNew.setTextSize(16f);

            TextView tvTien = new TextView(this);
            tvTien.setText("Tong tien: " + TienIch.dinhDangTien(donMoi.getTongTien()));
            tvTien.setTextSize(16f);

            TextView tvTrangThai = new TextView(this);
            tvTrangThai.setText("Trang thai: " + donMoi.getTrangThai());
            tvTrangThai.setTextSize(16f);

            box.addView(tvNew);
            box.addView(tvTien);
            box.addView(tvTrangThai);
        }

        Button btnTiepTucMuaSam = new Button(this);
        btnTiepTucMuaSam.setText("Tiep tuc mua sam");
        btnTiepTucMuaSam.setAllCaps(false);
        btnTiepTucMuaSam.setTextSize(16f);
        btnTiepTucMuaSam.setTextColor(0xFFFFFFFF);
        btnTiepTucMuaSam.setBackgroundResource(R.drawable.bg_register_button);

        LinearLayout.LayoutParams lpBtn = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        lpBtn.topMargin = 32;
        btnTiepTucMuaSam.setLayoutParams(lpBtn);
        btnTiepTucMuaSam.setOnClickListener(v ->
                startActivity(new Intent(ManHinhDonHangActivity.this, ManHinhSanPhamActivity.class)));

        box.addView(btnTiepTucMuaSam);

        root.addView(box);
    }

    private void moMenuDieuHuong(View anchor) {
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
                    startActivity(new Intent(ManHinhDonHangActivity.this, ManHinhChinhActivity.class));
                    return true;
                }
                if (id == R.id.nav_pet || id == R.id.menu_sanpham) {
                    startActivity(new Intent(ManHinhDonHangActivity.this, ManHinhSanPhamActivity.class));
                    return true;
                }
                if (id == R.id.nav_cart || id == R.id.menu_giohang) {
                    startActivity(new Intent(ManHinhDonHangActivity.this, ManHinhGioHangActivity.class));
                    return true;
                }
                if (id == R.id.nav_user || id == R.id.menu_taikhoan) {
                    startActivity(new Intent(ManHinhDonHangActivity.this, ManHinhCaNhan.class));
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
