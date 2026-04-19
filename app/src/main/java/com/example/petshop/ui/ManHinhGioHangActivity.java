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
        adapter = new GioHangAdapter(this, dsGioHang, () -> {
            // reserved for future cart total UI
        });
        rvGioHang.setLayoutManager(new LinearLayoutManager(this));
        rvGioHang.setAdapter(adapter);
    }

    private void setupSuKien() {
        btnMenu.setOnClickListener(v -> MenuDieuHuongHelper.moMenuDieuHuong(this, v, R.id.nav_cart));

        btnXoa.setOnClickListener(v -> {
            int soDaXoa = adapter.xoaDaChon();
            if (soDaXoa == 0) {
                Toast.makeText(this, "Bạn chưa chọn sản phẩm để xóa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Đã xóa " + soDaXoa + " sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });

        btnXacNhan.setOnClickListener(v -> {
            if (dsGioHang.isEmpty()) {
                Toast.makeText(this, "Giỏ hàng đang trống", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, ManHinhThanhToanActivity.class));
        });
    }
}
