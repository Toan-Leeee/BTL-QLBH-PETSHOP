package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.SanPham;
import com.example.petshop.util.MenuDieuHuongHelper;
import com.example.petshop.util.XacThucHelper;
import com.example.petshop.util.adapter.SanPhamAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManHinhSanPhamActivity extends AppCompatActivity {
    private static final int SO_SAN_PHAM_MOI_TRANG = 6;

    private final List<SanPham> dsSanPham = new ArrayList<>();
    private RecyclerView rvSanPham;
    private TextView txtTrang1;
    private TextView txtTrang2;
    private TextView txtTrang3;
    private TextView txtTongTrang;
    private TextView txtCham;
    private TextView btnTrangTruoc;
    private TextView btnTrangSau;
    private int trangHienTai = 1;
    private int tongSoTrang = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_san_pham);

        ImageView btnMenu = findViewById(R.id.btnMenu);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);
        Button btnKhamPha = findViewById(R.id.btnKhamPha);
        View btnBoLoc = findViewById(R.id.btnBoLoc);
        rvSanPham = findViewById(R.id.rvSanPham);
        txtTrang1 = findViewById(R.id.txtTrang1);
        txtTrang2 = findViewById(R.id.txtTrang2);
        txtTrang3 = findViewById(R.id.txtTrang3);
        txtTongTrang = findViewById(R.id.txtTongTrang);
        txtCham = findViewById(R.id.txtCham);
        btnTrangTruoc = findViewById(R.id.btnTrangTruoc);
        btnTrangSau = findViewById(R.id.btnTrangSau);

        btnMenu.setOnClickListener(this::moMenuDieuHuong);
        btnGioiThieu.setOnClickListener(v ->
                Toast.makeText(this, "Danh sách sản phẩm thú cưng", Toast.LENGTH_SHORT).show());
        btnKhamPha.setOnClickListener(v -> moSanPhamDauTienTrangHienTai());
        btnBoLoc.setOnClickListener(v ->
                Toast.makeText(this, "Bộ lọc demo với dữ liệu fix cứng", Toast.LENGTH_SHORT).show());

        khoiTaoDanhSachSanPham();
        khoiTaoPhanTrang();
    }

    private void khoiTaoDanhSachSanPham() {
        dsSanPham.clear();
        dsSanPham.addAll(DuLieuMau.layDanhSachSanPham());
        tongSoTrang = Math.max(1, (int) Math.ceil(dsSanPham.size() / (double) SO_SAN_PHAM_MOI_TRANG));

        rvSanPham.setLayoutManager(new GridLayoutManager(this, 2));
        rvSanPham.setHasFixedSize(false);
        capNhatDanhSachTheoTrang();
    }

    private void khoiTaoPhanTrang() {
        txtTrang1.setOnClickListener(v -> chuyenTrang(1));
        txtTrang2.setOnClickListener(v -> chuyenTrang(2));
        txtTrang3.setOnClickListener(v -> chuyenTrang(3));
        btnTrangTruoc.setOnClickListener(v -> chuyenTrang(trangHienTai - 1));
        btnTrangSau.setOnClickListener(v -> chuyenTrang(trangHienTai + 1));
        capNhatDieuKhienPhanTrang();
    }

    private void chuyenTrang(int trangMoi) {
        if (trangMoi < 1 || trangMoi > tongSoTrang || trangMoi == trangHienTai) {
            return;
        }
        trangHienTai = trangMoi;
        capNhatDanhSachTheoTrang();
        capNhatDieuKhienPhanTrang();
    }

    private void capNhatDanhSachTheoTrang() {
        int viTriBatDau = (trangHienTai - 1) * SO_SAN_PHAM_MOI_TRANG;
        int viTriKetThuc = Math.min(viTriBatDau + SO_SAN_PHAM_MOI_TRANG, dsSanPham.size());
        List<SanPham> dsTrang = new ArrayList<>(dsSanPham.subList(viTriBatDau, viTriKetThuc));
        rvSanPham.setAdapter(new SanPhamAdapter(dsTrang,
                item -> moChiTietSanPham(DuLieuMau.timViTriSanPhamTheoMa(item.getMa()))));
    }

    private void capNhatDieuKhienPhanTrang() {
        txtTongTrang.setText(String.valueOf(tongSoTrang));
        capNhatNutTrang(txtTrang1, 1);
        capNhatNutTrang(txtTrang2, 2);
        capNhatNutTrang(txtTrang3, 3);

        txtTrang2.setVisibility(tongSoTrang >= 2 ? View.VISIBLE : View.GONE);
        txtTrang3.setVisibility(tongSoTrang >= 3 ? View.VISIBLE : View.GONE);
        txtCham.setVisibility(tongSoTrang > 4 ? View.VISIBLE : View.GONE);
        txtTongTrang.setVisibility(tongSoTrang > 3 ? View.VISIBLE : View.GONE);
        btnTrangTruoc.setAlpha(trangHienTai > 1 ? 1f : 0.35f);
        btnTrangSau.setAlpha(trangHienTai < tongSoTrang ? 1f : 0.35f);
    }

    private void capNhatNutTrang(TextView textView, int soTrang) {
        if (soTrang > tongSoTrang) {
            textView.setVisibility(View.GONE);
            return;
        }
        textView.setVisibility(View.VISIBLE);
        textView.setText(String.valueOf(soTrang));
        if (soTrang == trangHienTai) {
            textView.setBackgroundResource(R.drawable.bg_page_current);
            textView.setTextColor(0xFFFFFFFF);
        } else {
            textView.setBackground(null);
            textView.setTextColor(0xFF0B3B68);
        }
    }

    private void moSanPhamDauTienTrangHienTai() {
        if (dsSanPham.isEmpty()) {
            return;
        }
        int viTriBatDau = (trangHienTai - 1) * SO_SAN_PHAM_MOI_TRANG;
        if (viTriBatDau >= dsSanPham.size()) {
            viTriBatDau = 0;
        }
        moChiTietSanPham(viTriBatDau);
    }

    private void moChiTietSanPham(int viTri) {
        Intent intent = new Intent(this, ManHinhChiTietActivity.class);
        intent.putExtra("vi_tri_san_pham", viTri);
        startActivity(intent);
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
                    startActivity(new Intent(ManHinhSanPhamActivity.this, ManHinhChinhActivity.class));
                    return true;
                }
                if (id == R.id.nav_pet || id == R.id.menu_sanpham) {
                    return true;
                }
                if (id == R.id.nav_cart || id == R.id.menu_giohang) {
                    startActivity(new Intent(ManHinhSanPhamActivity.this, ManHinhGioHangActivity.class));
                    return true;
                }
                if (id == R.id.nav_user || id == R.id.menu_taikhoan) {
                    startActivity(new Intent(ManHinhSanPhamActivity.this, ManHinhCaNhan.class));
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
