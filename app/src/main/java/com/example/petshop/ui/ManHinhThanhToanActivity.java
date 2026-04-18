package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.DonHang;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.GioHangItem;
import com.example.petshop.data.TaiKhoan;
import com.example.petshop.prefs.QuanLyPrefs;
import com.example.petshop.util.TienIch;
import com.example.petshop.util.XacThucHelper;
import com.example.petshop.util.adapter.ThanhToanSanPhamAdapter;

import java.util.List;

public class ManHinhThanhToanActivity extends AppCompatActivity {

    private static final long PHI_VAN_CHUYEN = 15000L;

    private ImageView btnBack;
    private TextView txtNguoiNhan;
    private TextView txtDiaChi;
    private RecyclerView rvThanhToanSanPham;
    private TextView txtTongTienHang;
    private TextView txtPhiVanChuyen;
    private TextView txtGiamGia;
    private TextView txtTongThanhToan;
    private TextView txtVoucher;
    private RadioGroup rgPayment;
    private RadioButton rbThanhToanNhanHang;
    private Button btnDatHang;

    private List<GioHangItem> dsGioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (XacThucHelper.yeuCauDangNhap(this)) {
            return;
        }
        setContentView(R.layout.activity_man_hinh_thanh_toan);

        anhXa();
        bindThongTinNguoiNhan();
        setupDanhSachSanPham();
        setupSuKien();
        capNhatTongTien();
    }

    private void anhXa() {
        btnBack = findViewById(R.id.btnBack);
        txtNguoiNhan = findViewById(R.id.txtNguoiNhan);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        rvThanhToanSanPham = findViewById(R.id.rvThanhToanSanPham);
        txtTongTienHang = findViewById(R.id.txtTongTienHang);
        txtPhiVanChuyen = findViewById(R.id.txtPhiVanChuyen);
        txtGiamGia = findViewById(R.id.txtGiamGia);
        txtTongThanhToan = findViewById(R.id.txtTongThanhToan);
        txtVoucher = findViewById(R.id.txtVoucher);
        rgPayment = findViewById(R.id.rgPayment);
        rbThanhToanNhanHang = findViewById(R.id.rbThanhToanNhanHang);
        btnDatHang = findViewById(R.id.btnDatHang);
    }

    private void bindThongTinNguoiNhan() {
        String tenDangNhap = QuanLyPrefs.layTenDangNhap(this);

        TaiKhoan taiKhoan = DuLieuMau.timTaiKhoanTheoTenDangNhap(tenDangNhap);
        if (taiKhoan != null) {
            txtNguoiNhan.setText(taiKhoan.getHoTen());
            txtDiaChi.setText(taiKhoan.getDiaChi());
        } else {
            String hoTenLuu = QuanLyPrefs.layHoTen(this);
            txtNguoiNhan.setText(hoTenLuu.isEmpty() ? "Khach hang" : hoTenLuu);
            txtDiaChi.setText("Chua co dia chi giao hang");
        }

        txtVoucher.setText("Nhap voucher (vi du: GIAM10)");
        rbThanhToanNhanHang.setChecked(true);
    }

    private void setupDanhSachSanPham() {
        dsGioHang = DuLieuMau.layDanhSachGioHang();
        rvThanhToanSanPham.setLayoutManager(new LinearLayoutManager(this));
        rvThanhToanSanPham.setAdapter(new ThanhToanSanPhamAdapter(dsGioHang));
    }

    private void setupSuKien() {
        btnBack.setOnClickListener(v -> finish());

        rgPayment.setOnCheckedChangeListener((group, checkedId) -> capNhatTongTien());

        btnDatHang.setOnClickListener(v -> {
            long tongTienHang = DuLieuMau.tinhTongTienGioHang();
            if (tongTienHang <= 0) {
                Toast.makeText(this, "Khong co san pham de dat", Toast.LENGTH_SHORT).show();
                return;
            }

            long giamGia = tinhGiamGia(tongTienHang);
            long tongThanhToan = tongTienHang + PHI_VAN_CHUYEN - giamGia;
            String phuongThuc = layPhuongThucThanhToan();

            DonHang donHang = DuLieuMau.taoDonHang(
                    QuanLyPrefs.layTenDangNhap(this),
                    tongThanhToan,
                    phuongThuc
            );

            DuLieuMau.xoaToanBoGioHang();
            Toast.makeText(this, "Dat hang thanh cong: " + donHang.getMaDon(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ManHinhDonHangActivity.class);
            intent.putExtra("ma_don", donHang.getMaDon());
            startActivity(intent);
            finish();
        });
    }

    private void capNhatTongTien() {
        long tongTienHang = DuLieuMau.tinhTongTienGioHang();
        long giamGia = tinhGiamGia(tongTienHang);
        long tongThanhToan = tongTienHang + PHI_VAN_CHUYEN - giamGia;

        txtTongTienHang.setText(TienIch.dinhDangTien(tongTienHang));
        txtPhiVanChuyen.setText(TienIch.dinhDangTien(PHI_VAN_CHUYEN));
        txtGiamGia.setText("-" + TienIch.dinhDangTien(giamGia));
        txtTongThanhToan.setText(TienIch.dinhDangTien(tongThanhToan));
    }

    private long tinhGiamGia(long tongTienHang) {
        String voucher = txtVoucher.getText().toString().trim().toUpperCase();
        if (voucher.contains("GIAM10")) {
            return tongTienHang / 10;
        }
        return 0L;
    }

    private String layPhuongThucThanhToan() {
        int checkedId = rgPayment.getCheckedRadioButtonId();
        if (checkedId == R.id.rbThe) {
            return "ZaloPay";
        }
        if (checkedId == R.id.rbMomo) {
            return "Momo";
        }
        if (checkedId == R.id.rbNganHang) {
            return "Vi dien tu";
        }
        return "COD";
    }
}
