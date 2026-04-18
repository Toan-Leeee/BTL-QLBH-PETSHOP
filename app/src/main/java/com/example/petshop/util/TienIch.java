package com.example.petshop.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class TienIch {

    private TienIch() {
    }

    public static String dinhDangTien(long soTien) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("vi", "VN"));
        return numberFormat.format(soTien) + " VND";
    }

    public static String tienToString(long soTien) {
        return String.valueOf(soTien);
    }

    public static long stringToTien(String giaTri) {
        try {
            return Long.parseLong(giaTri);
        } catch (Exception e) {
            return 0L;
        }
    }
}
