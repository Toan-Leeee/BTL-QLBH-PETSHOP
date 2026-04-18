package com.example.petshop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.petshop.R;
import com.example.petshop.data.DonHang;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.util.TienIch;

import java.util.List;

public class ManHinhDonHangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_don_hang);

        ConstraintLayout root = findViewById(R.id.main);
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

        root.addView(box);
    }
}
