package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;
import com.example.petshop.data.DonHang;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.prefs.QuanLyPrefs;

import java.util.List;

public class ManHinhCaNhan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_ca_nhan);

        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtOrders = findViewById(R.id.txtOrders);
        Button btnLogout = findViewById(R.id.btnLogout);

        String tenDangNhap = QuanLyPrefs.layTenDangNhap(this);
        if (tenDangNhap.isEmpty()) {
            tenDangNhap = "Khach";
        }
        txtUsername.setText(tenDangNhap);

        List<DonHang> dsDonHang = DuLieuMau.layDanhSachDonHang();
        if (dsDonHang.isEmpty()) {
            txtOrders.setText("Chua co don hang nao");
        } else {
            DonHang donMoi = dsDonHang.get(0);
            txtOrders.setText("Don moi nhat: " + donMoi.getMaDon() + " - " + donMoi.getTrangThai());
        }

        txtOrders.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhDonHangActivity.class)));

        btnLogout.setOnClickListener(v -> {
            QuanLyPrefs.dangXuat(this);
            Intent intent = new Intent(this, ManHinhDangNhapActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
