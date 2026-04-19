package com.example.petshop.data;

import com.example.petshop.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DuLieuMau {

    private static final List<SanPham> DS_SAN_PHAM = new ArrayList<>();
    private static final List<TaiKhoan> DS_TAI_KHOAN = new ArrayList<>();
    private static final List<GioHangItem> DS_GIO_HANG = new ArrayList<>();
    private static final List<DonHang> DS_DON_HANG = new ArrayList<>();

    static {
        khoiTaoSanPham();
        khoiTaoTaiKhoan();
    }

    private DuLieuMau() {
    }

    private static void khoiTaoSanPham() {
        DS_SAN_PHAM.add(new SanPham("MO231", "Pomeranian Trắng", "Đực", "02 tháng", 6900000, R.drawable.mo231_pomeranian_trang));
        DS_SAN_PHAM.add(new SanPham("MO502", "Poodle Tiny Vàng", "Cái", "02 tháng", 3900000, R.drawable.mo502_poodle_tiny_vang));
        DS_SAN_PHAM.add(new SanPham("MO102", "Poodle Tiny Sepia", "Đực", "02 tháng", 4000000, R.drawable.mo512_poodle_tiny));
        DS_SAN_PHAM.add(new SanPham("MO512", "Alaskan Malamute", "Đực", "03 tháng", 8900000, R.drawable.mo512_alaskan_malamute));
        DS_SAN_PHAM.add(new SanPham("MO777", "Corgi Vàng Trắng", "Cái", "02 tháng", 7200000, R.drawable.mo231_pembroke_corgi));
        DS_SAN_PHAM.add(new SanPham("MO888", "Husky Xám Trắng", "Đực", "03 tháng", 8500000, R.drawable.cholongtrang));
        DS_SAN_PHAM.add(new SanPham("MO901", "French Bulldog Kem", "Đực", "02 tháng", 7600000, R.drawable.cholongnau));
        DS_SAN_PHAM.add(new SanPham("MO902", "Shiba Inu Đỏ", "Cái", "03 tháng", 8300000, R.drawable.cholongnau));
        DS_SAN_PHAM.add(new SanPham("MO903", "Samoyed Trắng", "Đực", "03 tháng", 9100000, R.drawable.mo231_pomeranian_trang));
        DS_SAN_PHAM.add(new SanPham("MO904", "Pug Đen", "Cái", "02 tháng", 5200000, R.drawable.mo502_poodle_tiny_vang));
        DS_SAN_PHAM.add(new SanPham("MO905", "Beagle Ba Màu", "Đực", "02 tháng", 6100000, R.drawable.mo502_pembroke_corgi));
        DS_SAN_PHAM.add(new SanPham("MO906", "Chihuahua Nâu", "Cái", "02 tháng", 4700000, R.drawable.cholongtrang));
        DS_SAN_PHAM.add(new SanPham("MO907", "Golden Retriever", "Đực", "03 tháng", 9400000, R.drawable.mo512_alaskan_malamute));
        DS_SAN_PHAM.add(new SanPham("MO908", "Yorkshire Terrier", "Cái", "02 tháng", 5800000, R.drawable.mo512_poodle_tiny));
        DS_SAN_PHAM.add(new SanPham("MO909", "Boston Terrier", "Đực", "02 tháng", 6900000, R.drawable.mo502_poodle_tiny_vang));
        DS_SAN_PHAM.add(new SanPham("MO910", "Dachshund Nâu", "Cái", "03 tháng", 5600000, R.drawable.mo502_pembroke_corgi));
        DS_SAN_PHAM.add(new SanPham("MO911", "Border Collie", "Đực", "03 tháng", 9700000, R.drawable.mo231_pembroke_corgi));
        DS_SAN_PHAM.add(new SanPham("MO912", "Papillon Mini", "Cái", "02 tháng", 5400000, R.drawable.mo231_pomeranian_trang));
    }

    private static void khoiTaoTaiKhoan() {
        DS_TAI_KHOAN.add(new TaiKhoan(
                "admin",
                "123456",
                "Admin",
                "admin@petshop.local",
                "1 Trần Hưng Đạo, Quận 1, TP HCM"
        ));
        DS_TAI_KHOAN.add(new TaiKhoan(
                "user1",
                "123456",
                "Nguyễn Văn A",
                "user1@petshop.local",
                "9A1, NCT, Phường Bình Hòa, An Giang"
        ));
        DS_TAI_KHOAN.add(new TaiKhoan(
                "user2",
                "123456",
                "Trần Thị B",
                "user2@petshop.local",
                "25 Lê Lợi, Quận Hải Châu, Đà Nẵng"
        ));
    }

    public static List<SanPham> layDanhSachSanPham() {
        return new ArrayList<>(DS_SAN_PHAM);
    }

    public static List<TaiKhoan> layDanhSachTaiKhoan() {
        return new ArrayList<>(DS_TAI_KHOAN);
    }

    public static List<DonHang> layDanhSachDonHang() {
        return new ArrayList<>(DS_DON_HANG);
    }

    public static List<GioHangItem> layDanhSachGioHang() {
        return DS_GIO_HANG;
    }

    public static void themTaiKhoan(TaiKhoan taiKhoan) {
        for (TaiKhoan item : DS_TAI_KHOAN) {
            if (item.getTenDangNhap().equalsIgnoreCase(taiKhoan.getTenDangNhap())) {
                return;
            }
        }
        DS_TAI_KHOAN.add(taiKhoan);
    }

    public static TaiKhoan timTaiKhoanHopLe(String tenDangNhapOrEmail, String matKhau) {
        String key = tenDangNhapOrEmail == null ? "" : tenDangNhapOrEmail.trim().toLowerCase(Locale.ROOT);
        for (TaiKhoan tk : DS_TAI_KHOAN) {
            boolean dungTaiKhoan = tk.getTenDangNhap().equalsIgnoreCase(key)
                    || tk.getEmail().equalsIgnoreCase(key);
            if (dungTaiKhoan && tk.getMatKhau().equals(matKhau)) {
                return tk;
            }
        }
        return null;
    }

    public static TaiKhoan timTaiKhoanTheoTenDangNhap(String tenDangNhap) {
        if (tenDangNhap == null) {
            return null;
        }
        String key = tenDangNhap.trim();
        for (TaiKhoan tk : DS_TAI_KHOAN) {
            if (tk.getTenDangNhap().equalsIgnoreCase(key)) {
                return tk;
            }
        }
        return null;
    }

    public static SanPham timSanPhamTheoMa(String ma) {
        for (SanPham item : DS_SAN_PHAM) {
            if (item.getMa().equalsIgnoreCase(ma)) {
                return item;
            }
        }
        return null;
    }

    public static int timViTriSanPhamTheoMa(String ma) {
        for (int i = 0; i < DS_SAN_PHAM.size(); i++) {
            if (DS_SAN_PHAM.get(i).getMa().equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return 0;
    }

    public static void themVaoGioHang(SanPham sanPham, int soLuongThem) {
        if (sanPham == null) {
            return;
        }
        for (GioHangItem item : DS_GIO_HANG) {
            if (item.getSanPham().getMa().equalsIgnoreCase(sanPham.getMa())) {
                item.setSoLuong(item.getSoLuong() + Math.max(1, soLuongThem));
                return;
            }
        }
        DS_GIO_HANG.add(new GioHangItem(sanPham, Math.max(1, soLuongThem)));
    }

    public static void xoaGioHangDaChon() {
        Iterator<GioHangItem> iterator = DS_GIO_HANG.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().isDaChon()) {
                iterator.remove();
            }
        }
    }

    public static void xoaToanBoGioHang() {
        DS_GIO_HANG.clear();
    }

    public static long tinhTongTienGioHang() {
        long tong = 0L;
        for (GioHangItem item : DS_GIO_HANG) {
            tong += item.tinhThanhTien();
        }
        return tong;
    }

    public static DonHang taoDonHang(String tenDangNhap, long tongTien, String phuongThuc) {
        String maDon = "DH" + String.format(Locale.ROOT, "%03d", DS_DON_HANG.size() + 1);
        DonHang donHang = new DonHang(maDon, tenDangNhap, tongTien, "Chờ xác nhận", phuongThuc);
        DS_DON_HANG.add(0, donHang);
        return donHang;
    }
}
