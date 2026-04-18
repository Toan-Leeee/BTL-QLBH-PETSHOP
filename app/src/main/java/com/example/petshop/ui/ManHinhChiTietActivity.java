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
import com.example.petshop.util.TienIch;
import com.example.petshop.util.XacThucHelper;
import com.example.petshop.util.adapter.SanPhamAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManHinhChiTietActivity extends AppCompatActivity {

    private ImageView btnMenu;
    private ImageView imgMain;
    private ImageView imgBannerSecondary;
    private ImageView btnPrev;
    private ImageView btnNext;
    private ImageView thumb1;
    private ImageView thumb2;
    private ImageView thumb3;
    private ImageView thumb4;
    private ImageView thumb5;

    private TextView txtTen;
    private TextView txtGia;
    private TextView txtSku;
    private TextView txtGiong;
    private TextView txtTuoi;
    private TextView txtKichThuoc;
    private TextView txtMauSac;
    private TextView txtMoTa;

    private RecyclerView rvLienQuan;
    private Button btnMuaNgay;
    private Button btnThemVaoGio;

    private List<SanPham> dsSanPham;
    private int viTriHienTai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_chi_tiet);

        dsSanPham = DuLieuMau.layDanhSachSanPham();
        viTriHienTai = getIntent().getIntExtra("vi_tri_san_pham", 0);
        if (viTriHienTai < 0 || viTriHienTai >= dsSanPham.size()) {
            viTriHienTai = 0;
        }

        anhXaView();
        bindSanPhamTheoViTri(viTriHienTai);
        setupSuKien();
    }

    private void anhXaView() {
        btnMenu = findViewById(R.id.btnMenu);
        imgMain = findViewById(R.id.imgMain);
        imgBannerSecondary = findViewById(R.id.imgBannerSecondary);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        thumb1 = findViewById(R.id.thumb1);
        thumb2 = findViewById(R.id.thumb2);
        thumb3 = findViewById(R.id.thumb3);
        thumb4 = findViewById(R.id.thumb4);
        thumb5 = findViewById(R.id.thumb5);

        txtTen = findViewById(R.id.txtTen);
        txtGia = findViewById(R.id.txtGia);
        txtSku = findViewById(R.id.txtSku);
        txtGiong = findViewById(R.id.txtGiong);
        txtTuoi = findViewById(R.id.txtTuoi);
        txtKichThuoc = findViewById(R.id.txtKichThuoc);
        txtMauSac = findViewById(R.id.txtMauSac);
        txtMoTa = findViewById(R.id.txtMoTa);

        rvLienQuan = findViewById(R.id.rvLienQuan);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        btnThemVaoGio = findViewById(R.id.btnThemVaoGio);
    }

    private void setupSuKien() {
        btnMenu.setOnClickListener(this::moMenuDieuHuong);

        btnPrev.setOnClickListener(v -> {
            if (dsSanPham.isEmpty()) {
                return;
            }
            viTriHienTai = (viTriHienTai - 1 + dsSanPham.size()) % dsSanPham.size();
            bindSanPhamTheoViTri(viTriHienTai);
        });

        btnNext.setOnClickListener(v -> {
            if (dsSanPham.isEmpty()) {
                return;
            }
            viTriHienTai = (viTriHienTai + 1) % dsSanPham.size();
            bindSanPhamTheoViTri(viTriHienTai);
        });

        thumb1.setOnClickListener(v -> datAnhChinh(thumb1));
        thumb2.setOnClickListener(v -> datAnhChinh(thumb2));
        thumb3.setOnClickListener(v -> datAnhChinh(thumb3));
        thumb4.setOnClickListener(v -> datAnhChinh(thumb4));
        thumb5.setOnClickListener(v -> datAnhChinh(thumb5));

        btnMuaNgay.setOnClickListener(v -> {
            if (dsSanPham.isEmpty()) {
                return;
            }
            DuLieuMau.themVaoGioHang(dsSanPham.get(viTriHienTai), 1);
            startActivity(new Intent(this, ManHinhGioHangActivity.class));
        });

        btnThemVaoGio.setOnClickListener(v -> {
            if (dsSanPham.isEmpty()) {
                return;
            }
            SanPham sanPham = dsSanPham.get(viTriHienTai);
            DuLieuMau.themVaoGioHang(sanPham, 1);
            Toast.makeText(this, "Da them vao gio hang", Toast.LENGTH_SHORT).show();
        });
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
                    startActivity(new Intent(ManHinhChiTietActivity.this, ManHinhChinhActivity.class));
                    return true;
                }
                if (id == R.id.nav_pet || id == R.id.menu_sanpham) {
                    startActivity(new Intent(ManHinhChiTietActivity.this, ManHinhSanPhamActivity.class));
                    return true;
                }
                if (id == R.id.nav_cart || id == R.id.menu_giohang) {
                    startActivity(new Intent(ManHinhChiTietActivity.this, ManHinhGioHangActivity.class));
                    return true;
                }
                if (id == R.id.nav_user || id == R.id.menu_taikhoan) {
                    startActivity(new Intent(ManHinhChiTietActivity.this, ManHinhCaNhan.class));
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

    private void bindSanPhamTheoViTri(int viTri) {
        if (dsSanPham.isEmpty()) {
            return;
        }

        SanPham sp = dsSanPham.get(viTri);
        imgMain.setImageResource(sp.getHinhAnh());
        imgBannerSecondary.setImageResource(sp.getHinhAnh());

        thumb1.setImageResource(sp.getHinhAnh());
        thumb2.setImageResource(R.drawable.cholongnau);
        thumb3.setImageResource(R.drawable.cholongtrang);
        thumb4.setImageResource(R.drawable.cholongnau);
        thumb5.setImageResource(R.drawable.cholongtrang);

        txtTen.setText(sp.getMa() + " - " + sp.getTen());
        txtGia.setText(TienIch.dinhDangTien(sp.getGia()));
        txtSku.setText("#" + sp.getMa());
        txtGiong.setText(sp.getGioiTinh());
        txtTuoi.setText(sp.getTuoi());
        txtKichThuoc.setText(layKichThuoc(sp.getTen()));
        txtMauSac.setText(layMauSac(sp.getTen()));
        txtMoTa.setText("Thu cung giong " + sp.getTen() + ", tinh cach than thien, phu hop nuoi trong gia dinh.");

        setupSanPhamLienQuan(dsSanPham, viTri);
    }

    private void datAnhChinh(ImageView thumb) {
        imgMain.setImageDrawable(thumb.getDrawable());
        imgBannerSecondary.setImageDrawable(thumb.getDrawable());
    }

    private void setupSanPhamLienQuan(List<SanPham> ds, int viTriDangXem) {
        List<SanPham> dsLienQuan = new ArrayList<>();

        for (int i = 0; i < ds.size(); i++) {
            if (i != viTriDangXem && dsLienQuan.size() < 6) {
                dsLienQuan.add(ds.get(i));
            }
        }

        rvLienQuan.setLayoutManager(new GridLayoutManager(this, 2));
        rvLienQuan.setNestedScrollingEnabled(false);

        SanPhamAdapter adapter = new SanPhamAdapter(dsLienQuan, item -> {
            int viTriMoi = DuLieuMau.timViTriSanPhamTheoMa(item.getMa());
            viTriHienTai = viTriMoi;
            bindSanPhamTheoViTri(viTriHienTai);
        });
        rvLienQuan.setAdapter(adapter);
    }

    private String layKichThuoc(String ten) {
        String tenThuong = ten.toLowerCase();
        if (tenThuong.contains("pomeranian") || tenThuong.contains("poodle")) {
            return "Nho";
        }
        if (tenThuong.contains("corgi")) {
            return "Trung binh";
        }
        return "Lon";
    }

    private String layMauSac(String ten) {
        String tenThuong = ten.toLowerCase();
        if (tenThuong.contains("trang")) {
            return "Trang";
        }
        if (tenThuong.contains("vang")) {
            return "Vang";
        }
        if (tenThuong.contains("sepia")) {
            return "Nau";
        }
        if (tenThuong.contains("xam")) {
            return "Xam trang";
        }
        return "Nau do";
    }
}
