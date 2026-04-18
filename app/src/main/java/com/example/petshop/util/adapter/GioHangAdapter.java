package com.example.petshop.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.GioHangItem;
import com.example.petshop.util.TienIch;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.VH> {

    public interface OnCartChangeListener {
        void onChanged();
    }

    private final Context context;
    private final List<GioHangItem> ds;
    private final OnCartChangeListener onCartChangeListener;

    public GioHangAdapter(Context context, List<GioHangItem> ds, OnCartChangeListener onCartChangeListener) {
        this.context = context;
        this.ds = ds;
        this.onCartChangeListener = onCartChangeListener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gio_hang, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        GioHangItem item = ds.get(position);

        holder.imgSanPham.setImageResource(item.getSanPham().getHinhAnh());
        holder.txtTen.setText(item.getSanPham().getMa() + " - " + item.getSanPham().getTen());
        holder.txtGia.setText(TienIch.dinhDangTien(item.getSanPham().getGia()));
        holder.txtSoLuong.setText(String.valueOf(item.getSoLuong()));

        String[] phanLoai = new String[]{"Mac dinh", "Vip", "Premium"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                phanLoai
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spPhanLoai.setAdapter(spinnerAdapter);

        int viTri = 0;
        for (int i = 0; i < phanLoai.length; i++) {
            if (phanLoai[i].equalsIgnoreCase(item.getPhanLoai())) {
                viTri = i;
                break;
            }
        }
        holder.spPhanLoai.setSelection(viTri, false);

        holder.cbChon.setOnCheckedChangeListener(null);
        holder.cbChon.setChecked(item.isDaChon());
        holder.cbChon.setOnCheckedChangeListener((buttonView, isChecked) -> item.setDaChon(isChecked));

        holder.btnTang.setOnClickListener(v -> {
            item.setSoLuong(item.getSoLuong() + 1);
            notifyItemChanged(holder.getBindingAdapterPosition());
            goiOnChanged();
        });

        holder.btnGiam.setOnClickListener(v -> {
            if (item.getSoLuong() > 1) {
                item.setSoLuong(item.getSoLuong() - 1);
                notifyItemChanged(holder.getBindingAdapterPosition());
                goiOnChanged();
            }
        });
    }

    public int xoaDaChon() {
        int soLuongXoa = 0;
        for (int i = ds.size() - 1; i >= 0; i--) {
            if (ds.get(i).isDaChon()) {
                ds.remove(i);
                soLuongXoa++;
            }
        }
        if (soLuongXoa > 0) {
            notifyDataSetChanged();
            goiOnChanged();
        }
        return soLuongXoa;
    }

    private void goiOnChanged() {
        if (onCartChangeListener != null) {
            onCartChangeListener.onChanged();
        }
    }

    @Override
    public int getItemCount() {
        return ds == null ? 0 : ds.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        CheckBox cbChon;
        ImageView imgSanPham;
        TextView txtTen;
        Spinner spPhanLoai;
        TextView txtGia;
        TextView btnGiam;
        TextView txtSoLuong;
        TextView btnTang;

        VH(@NonNull View itemView) {
            super(itemView);
            cbChon = itemView.findViewById(R.id.cbChon);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
            txtTen = itemView.findViewById(R.id.txtTen);
            spPhanLoai = itemView.findViewById(R.id.spPhanLoai);
            txtGia = itemView.findViewById(R.id.txtGia);
            btnGiam = itemView.findViewById(R.id.btnGiam);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            btnTang = itemView.findViewById(R.id.btnTang);
        }
    }
}
