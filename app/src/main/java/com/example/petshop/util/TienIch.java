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
}
