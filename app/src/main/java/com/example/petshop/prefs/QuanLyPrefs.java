package com.example.petshop.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public final class QuanLyPrefs {

    private QuanLyPrefs() {
    }

    private static SharedPreferences layPrefs(Context context) {
        return context.getSharedPreferences(KhoaPrefs.TEN_PREFS, Context.MODE_PRIVATE);
    }

    public static void luuPhienDangNhap(Context context, String tenDangNhap, String hoTen) {
        layPrefs(context)
                .edit()
                .putBoolean(KhoaPrefs.DA_DANG_NHAP, true)
                .putString(KhoaPrefs.TEN_DANG_NHAP, tenDangNhap)
                .putString(KhoaPrefs.HO_TEN, hoTen)
                .apply();
    }

    public static boolean daDangNhap(Context context) {
        return layPrefs(context).getBoolean(KhoaPrefs.DA_DANG_NHAP, false);
    }

    public static String layTenDangNhap(Context context) {
        return layPrefs(context).getString(KhoaPrefs.TEN_DANG_NHAP, "");
    }

    public static String layHoTen(Context context) {
        return layPrefs(context).getString(KhoaPrefs.HO_TEN, "");
    }

    public static void dangXuat(Context context) {
        layPrefs(context)
                .edit()
                .putBoolean(KhoaPrefs.DA_DANG_NHAP, false)
                .remove(KhoaPrefs.TEN_DANG_NHAP)
                .remove(KhoaPrefs.HO_TEN)
                .apply();
    }

    public static void luuTaiKhoanDangKy(Context context, String tenDangNhap, String email, String matKhau) {
        layPrefs(context)
                .edit()
                .putString(KhoaPrefs.TK_DA_DANG_KY, tenDangNhap)
                .putString(KhoaPrefs.EMAIL_DA_DANG_KY, email)
                .putString(KhoaPrefs.MK_DA_DANG_KY, matKhau)
                .apply();
    }

    public static boolean coTaiKhoanDangKy(Context context) {
        return !layPrefs(context).getString(KhoaPrefs.TK_DA_DANG_KY, "").isEmpty();
    }

    public static String layTaiKhoanDangKy(Context context) {
        return layPrefs(context).getString(KhoaPrefs.TK_DA_DANG_KY, "");
    }

    public static String layEmailDangKy(Context context) {
        return layPrefs(context).getString(KhoaPrefs.EMAIL_DA_DANG_KY, "");
    }

    public static String layMatKhauDangKy(Context context) {
        return layPrefs(context).getString(KhoaPrefs.MK_DA_DANG_KY, "");
    }
}
