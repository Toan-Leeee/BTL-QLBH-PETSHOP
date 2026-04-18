package com.example.petshop.data;

public class DonHang {
    private final String maDon;
    private final String tenDangNhap;
    private final long tongTien;
    private final String trangThai;
    private final String phuongThucThanhToan;

    public DonHang(String maDon, String tenDangNhap, long tongTien, String trangThai) {
        this(maDon, tenDangNhap, tongTien, trangThai, "COD");
    }

    public DonHang(String maDon, String tenDangNhap, long tongTien, String trangThai, String phuongThucThanhToan) {
        this.maDon = maDon;
        this.tenDangNhap = tenDangNhap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getMaDon() {
        return maDon;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public long getTongTien() {
        return tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }
}
