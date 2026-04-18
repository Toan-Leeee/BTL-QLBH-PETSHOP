package com.example.petshop.data;

public class TaiKhoan {
    private final String tenDangNhap;
    private final String matKhau;
    private final String hoTen;
    private final String email;

    public TaiKhoan(String tenDangNhap, String matKhau, String hoTen) {
        this(tenDangNhap, matKhau, hoTen, "");
    }

    public TaiKhoan(String tenDangNhap, String matKhau, String hoTen, String email) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.email = email;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getEmail() {
        return email;
    }
}
