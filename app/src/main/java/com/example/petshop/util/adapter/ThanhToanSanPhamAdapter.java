package com.example.petshop.util.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petshop.R;
import com.example.petshop.data.GioHangItem;
import com.example.petshop.util.TienIch;

import java.util.List;

public class ThanhToanSanPhamAdapter extends RecyclerView.Adapter<ThanhToanSanPhamAdapter.VH> {

    private final List<GioHangItem> ds;

    public ThanhToanSanPhamAdapter(List<GioHangItem> ds) {
        this.ds = ds;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thanh_toan_san_pham, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        GioHangItem item = ds.get(position);
        holder.imgSanPham.setImageResource(item.getSanPham().getHinhAnh());
        holder.txtTen.setText(item.getSanPham().getMa() + " - " + item.getSanPham().getTen());
        holder.txtGia.setText(TienIch.dinhDangTien(item.getSanPham().getGia()));
        holder.txtSoLuong.setText("x" + item.getSoLuong());
    }

    @Override
    public int getItemCount() {
        return ds == null ? 0 : ds.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        ImageView imgSanPham;
        TextView txtTen;
        TextView txtGia;
        TextView txtSoLuong;

        VH(@NonNull View itemView) {
            super(itemView);
            imgSanPham = itemView.findViewById(R.id.imgSanPham);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtGia = itemView.findViewById(R.id.txtGia);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
        }
    }
}
