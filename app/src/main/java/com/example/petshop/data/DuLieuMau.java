package com.example.petshop.data;

import com.example.petshop.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class DuLieuMau {

    private static final List<SanPham> DS_SAN_PHAM = new ArrayList<>();
    private static final List<TaiKhoan> DS_TAI_KHOAN = new ArrayList<>();
    private static final List<ThuCung> DS_THU_CUNG = new ArrayList<>();
    private static final List<GioHangItem> DS_GIO_HANG = new ArrayList<>();
    private static final List<DonHang> DS_DON_HANG = new ArrayList<>();

    static {
        khoiTaoSanPham();
        khoiTaoTaiKhoan();
        khoiTaoThuCung();
        khoiTaoDonHang();
    }

    private DuLieuMau() {
    }

    private static void khoiTaoSanPham() {
        DS_SAN_PHAM.add(new SanPham("MO231", "Pomeranian Trang", "Duc", "02 thang", 6900000, R.drawable.cholongtrang));
        DS_SAN_PHAM.add(new SanPham("MO502", "Poodle Tiny Vang", "Cai", "02 thang", 3900000, R.drawable.cholongnau));
        DS_SAN_PHAM.add(new SanPham("MO102", "Poodle Tiny Sepia", "Duc", "02 thang", 4000000, R.drawable.cholongnau));
        DS_SAN_PHAM.add(new SanPham("MO512", "Alaskan Malamute", "Duc", "03 thang", 8900000, R.drawable.cholongtrang));
        DS_SAN_PHAM.add(new SanPham("MO777", "Corgi Vang Trang", "Cai", "02 thang", 7200000, R.drawable.cholongtrang));
        DS_SAN_PHAM.add(new SanPham("MO888", "Husky Xam Trang", "Duc", "03 thang", 8500000, R.drawable.cholongnau));
    }

    private static void khoiTaoTaiKhoan() {
        DS_TAI_KHOAN.add(new TaiKhoan("admin", "123456", "Admin", "admin@petshop.local"));
        DS_TAI_KHOAN.add(new TaiKhoan("user1", "123456", "Nguyen Van A", "user1@petshop.local"));
        DS_TAI_KHOAN.add(new TaiKhoan("user2", "123456", "Tran Thi B", "user2@petshop.local"));
    }

    private static void khoiTaoThuCung() {
        DS_THU_CUNG.add(new ThuCung("TC01", "Cho Pomeranian", "Duc", "02 thang"));
        DS_THU_CUNG.add(new ThuCung("TC02", "Cho Poodle", "Cai", "03 thang"));
        DS_THU_CUNG.add(new ThuCung("TC03", "Cho Husky", "Duc", "04 thang"));
    }

    private static void khoiTaoDonHang() {
        DS_DON_HANG.add(new DonHang("DH01", "user1", 6900000, "Dang xu ly", "COD"));
        DS_DON_HANG.add(new DonHang("DH02", "user2", 8500000, "Hoan thanh", "ZaloPay"));
    }

    public static List<SanPham> layDanhSachSanPham() {
        return new ArrayList<>(DS_SAN_PHAM);
    }

    public static List<TaiKhoan> layDanhSachTaiKhoan() {
        return new ArrayList<>(DS_TAI_KHOAN);
    }

    public static List<ThuCung> layDanhSachThuCung() {
        return new ArrayList<>(DS_THU_CUNG);
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
        DonHang donHang = new DonHang(maDon, tenDangNhap, tongTien, "Cho xac nhan", phuongThuc);
        DS_DON_HANG.add(0, donHang);
        return donHang;
    }
}
