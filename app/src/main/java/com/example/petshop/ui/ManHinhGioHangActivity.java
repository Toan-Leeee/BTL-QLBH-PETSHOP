package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.GioHangItem;
import com.example.petshop.data.SanPham;
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
        btnMenu.setOnClickListener(v -> finish());

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
}
