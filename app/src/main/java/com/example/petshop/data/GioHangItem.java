package com.example.petshop.data;

public class GioHangItem {

    private final SanPham sanPham;
    private int soLuong;
    private boolean daChon;
    private String phanLoai;

    public GioHangItem(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = Math.max(1, soLuong);
        this.phanLoai = "Mac dinh";
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = Math.max(1, soLuong);
    }

    public boolean isDaChon() {
        return daChon;
    }

    public void setDaChon(boolean daChon) {
        this.daChon = daChon;
    }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public long tinhThanhTien() {
        return sanPham.getGia() * soLuong;
    }
}
