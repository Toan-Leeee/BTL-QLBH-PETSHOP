package com.example.petshop.data;

public class SanPham {
    private final String ma;
    private final String ten;
    private final String gioiTinh;
    private final String tuoi;
    private final long gia;
    private final int hinhAnh;

    public SanPham(String ma, String ten, String gioiTinh, String tuoi, long gia, int hinhAnh) {
        this.ma = ma;
        this.ten = ten;
        this.gioiTinh = gioiTinh;
        this.tuoi = tuoi;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getTuoi() {
        return tuoi;
    }

    public long getGia() {
        return gia;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }
}
