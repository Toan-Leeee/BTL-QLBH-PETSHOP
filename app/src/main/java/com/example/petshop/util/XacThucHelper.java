package com.example.petshop.util;

import android.app.Activity;
import android.content.Intent;

import com.example.petshop.prefs.QuanLyPrefs;
import com.example.petshop.ui.ManHinhDangNhapActivity;

public final class XacThucHelper {

    private XacThucHelper() {
    }

    public static boolean yeuCauDangNhap(Activity activity) {
        if (QuanLyPrefs.daDangNhap(activity)) {
            return false;
        }
        Intent intent = new Intent(activity, ManHinhDangNhapActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
        return true;
    }
}
