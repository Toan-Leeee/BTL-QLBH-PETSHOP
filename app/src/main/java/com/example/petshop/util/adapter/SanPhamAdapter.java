package com.example.petshop.util.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.SanPham;
import com.example.petshop.util.TienIch;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.VH> {

    public interface OnItemClick {
        void onClick(SanPham item);
    }

    private final List<SanPham> ds;
    private final OnItemClick listener;

    public SanPhamAdapter(List<SanPham> ds, OnItemClick listener) {
        this.ds = ds;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_san_pham, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int position) {
        SanPham sp = ds.get(position);

        h.txtTenSanPham.setText(sp.getMa() + " - " + sp.getTen());
        h.txtGiong.setText("Giong: " + sp.getGioiTinh());
        h.txtTuoi.setText("Tuoi: " + sp.getTuoi());
        h.txtGia.setText(TienIch.dinhDangTien(sp.getGia()));
        h.imgSanPham.setImageResource(sp.getHinhAnh());

        h.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(sp);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds == null ? 0 : ds.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        ImageView imgSanPham;
        TextView txtTenSanPham;
        TextView txtGiong;
        TextView txtTuoi;
        TextView txtGia;

        public VH(@NonNull View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
            txtTenSanPham = itemView.findViewById(R.id.txtTenSanPham);
            txtGiong = itemView.findViewById(R.id.txtGiong);
            txtTuoi = itemView.findViewById(R.id.txtTuoi);
            txtGia = itemView.findViewById(R.id.txtGia);
        }
    }
}
