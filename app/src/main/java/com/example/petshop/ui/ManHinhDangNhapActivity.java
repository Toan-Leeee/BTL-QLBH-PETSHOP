package com.example.petshop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petshop.R;
import com.example.petshop.data.DuLieuMau;
import com.example.petshop.data.TaiKhoan;
import com.example.petshop.prefs.QuanLyPrefs;

public class ManHinhDangNhapActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private TextView btnLogin;
    private TextView btnRegister;
    private TextView txtForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_dang_nhap);

        anhXa();
        setupClick();
    }

    private void anhXa() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        txtForgotPassword = findViewById(R.id.txtForgotPassword);
    }

    private void setupClick() {
        btnLogin.setOnClickListener(v -> xuLyDangNhap());

        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(this, ManHinhDangKyActivity.class)));

        txtForgotPassword.setOnClickListener(v ->
                Toast.makeText(this, "Tai khoan mau: admin / 123456", Toast.LENGTH_SHORT).show());
    }

    private void xuLyDangNhap() {
        String tenDangNhap = edtUsername.getText().toString().trim();
        String matKhau = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(tenDangNhap) || TextUtils.isEmpty(matKhau)) {
            Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
            return;
        }

        TaiKhoan taiKhoanDangKy = timTaiKhoanDangKy(tenDangNhap, matKhau);
        TaiKhoan taiKhoanFixCung = DuLieuMau.timTaiKhoanHopLe(tenDangNhap, matKhau);
        TaiKhoan taiKhoanHopLe = taiKhoanDangKy != null ? taiKhoanDangKy : taiKhoanFixCung;

        if (taiKhoanHopLe == null) {
            Toast.makeText(this, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
            return;
        }

        QuanLyPrefs.luuPhienDangNhap(this, taiKhoanHopLe.getTenDangNhap(), taiKhoanHopLe.getHoTen());

        Intent intent = new Intent(this, ManHinhChinhActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private TaiKhoan timTaiKhoanDangKy(String tenDangNhap, String matKhau) {
        if (!QuanLyPrefs.coTaiKhoanDangKy(this)) {
            return null;
        }

        String tkLuu = QuanLyPrefs.layTaiKhoanDangKy(this);
        String emailLuu = QuanLyPrefs.layEmailDangKy(this);
        String mkLuu = QuanLyPrefs.layMatKhauDangKy(this);

        boolean dungTaiKhoan = tenDangNhap.equalsIgnoreCase(tkLuu)
                || tenDangNhap.equalsIgnoreCase(emailLuu);

        if (dungTaiKhoan && matKhau.equals(mkLuu)) {
            return new TaiKhoan(tkLuu, mkLuu, tkLuu, emailLuu);
        }
        return null;
    }
}
