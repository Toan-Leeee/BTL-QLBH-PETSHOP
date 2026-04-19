package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.SanPham;
import com.example.petshop.util.MenuDieuHuongHelper;
import com.example.petshop.util.TienIch;
import com.example.petshop.util.XacThucHelper;

import java.util.List;

public class ManHinhChinhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_chinh);

        ImageView btnMenu = findViewById(R.id.btnMenu);
        ImageView imgAvatar = findViewById(R.id.imgAvatar);
        Button btnGioiThieu = findViewById(R.id.btnGioiThieu);
        Button btnKhamPha = findViewById(R.id.btnKhamPha);
        View cardPet1 = findViewById(R.id.cardPet1);
        View cardPet2 = findViewById(R.id.cardPet2);
        View cardPet3 = findViewById(R.id.cardPet3);
        View cardPet4 = findViewById(R.id.cardPet4);

        btnMenu.setOnClickListener(v -> MenuDieuHuongHelper.moMenuDieuHuong(this, v, R.id.nav_home));
        btnKhamPha.setOnClickListener(v -> startActivity(new Intent(this, ManHinhSanPhamActivity.class)));
        btnGioiThieu.setOnClickListener(v ->
                Toast.makeText(this, "Monito - cửa hàng thú cưng", Toast.LENGTH_SHORT).show());
        imgAvatar.setOnClickListener(v -> startActivity(new Intent(this, ManHinhCaNhan.class)));

        bindSanPhamNoiBat();
        cardPet1.setOnClickListener(v -> moChiTietSanPham(0));
        cardPet2.setOnClickListener(v -> moChiTietSanPham(1));
        cardPet3.setOnClickListener(v -> moChiTietSanPham(2));
        cardPet4.setOnClickListener(v -> moChiTietSanPham(3));
    }

    private void bindSanPhamNoiBat() {
        List<SanPham> dsSanPham = DuLieuMau.layDanhSachSanPham();
        if (dsSanPham.size() < 4) {
            return;
        }

        bindCard(dsSanPham.get(0), R.id.imgPet1, R.id.txtTenPet1, R.id.txtGiongPet1, R.id.txtTuoiPet1, R.id.txtGiaPet1);
        bindCard(dsSanPham.get(1), R.id.imgPet2, R.id.txtTenPet2, R.id.txtGiongPet2, R.id.txtTuoiPet2, R.id.txtGiaPet2);
        bindCard(dsSanPham.get(2), R.id.imgPet3, R.id.txtTenPet3, R.id.txtGiongPet3, R.id.txtTuoiPet3, R.id.txtGiaPet3);
        bindCard(dsSanPham.get(3), R.id.imgPet4, R.id.txtTenPet4, R.id.txtGiongPet4, R.id.txtTuoiPet4, R.id.txtGiaPet4);
    }

    private void bindCard(SanPham sanPham, int imgId, int tenId, int giongId, int tuoiId, int giaId) {
        ImageView imageView = findViewById(imgId);
        TextView txtTen = findViewById(tenId);
        TextView txtGiong = findViewById(giongId);
        TextView txtTuoi = findViewById(tuoiId);
        TextView txtGia = findViewById(giaId);

        imageView.setImageResource(sanPham.getHinhAnh());
        txtTen.setText(sanPham.getMa() + " - " + sanPham.getTen());
        txtGiong.setText("Giống: " + sanPham.getGioiTinh());
        txtTuoi.setText("Tuổi: " + sanPham.getTuoi());
        txtGia.setText(TienIch.dinhDangTien(sanPham.getGia()));
    }

    private void moChiTietSanPham(int viTri) {
        Intent intent = new Intent(this, ManHinhChiTietActivity.class);
        intent.putExtra("vi_tri_san_pham", viTri);
        startActivity(intent);
    }
}
