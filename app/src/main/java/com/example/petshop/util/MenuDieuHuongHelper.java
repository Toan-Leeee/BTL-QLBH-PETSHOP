package com.example.petshop.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import com.example.petshop.R;
import com.example.petshop.ui.ManHinhCaNhan;
import com.example.petshop.ui.ManHinhChinhActivity;
import com.example.petshop.ui.ManHinhGioHangActivity;
import com.example.petshop.ui.ManHinhSanPhamActivity;

public final class MenuDieuHuongHelper {

    private MenuDieuHuongHelper() {
    }

    public static void chuanHoaIconMenu(Context context, Menu menu) {
        int sizePx = (int) (20 * context.getResources().getDisplayMetrics().density);
        for (int i = 0; i < menu.size(); i++) {
            android.view.MenuItem item = menu.getItem(i);
            @DrawableRes int iconRes = layIconRes(item.getItemId());
            if (iconRes == 0) {
                continue;
            }
            item.setIcon(taoIconVuong(context, iconRes, sizePx));
        }
    }

    public static void moMenuDieuHuong(AppCompatActivity activity, View anchor, int currentItemId) {
        MenuBuilder menuBuilder = new MenuBuilder(activity);
        new PopupMenu(activity, anchor).getMenuInflater().inflate(R.menu.menu_drawer, menuBuilder);
        chuanHoaIconMenu(activity, menuBuilder);

        MenuPopupHelper menuPopupHelper = new MenuPopupHelper(activity, menuBuilder, anchor);
        menuPopupHelper.setForceShowIcon(true);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, android.view.MenuItem item) {
                return dieuHuong(activity, item.getItemId(), currentItemId);
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {
            }
        });
        menuPopupHelper.show();
    }

    private static boolean dieuHuong(AppCompatActivity activity, int itemId, int currentItemId) {
        if (itemId == currentItemId) {
            return true;
        }

        Class<?> target = null;
        if (itemId == R.id.nav_home) {
            target = ManHinhChinhActivity.class;
        } else if (itemId == R.id.nav_pet) {
            target = ManHinhSanPhamActivity.class;
        } else if (itemId == R.id.nav_cart) {
            target = ManHinhGioHangActivity.class;
        } else if (itemId == R.id.nav_user) {
            target = ManHinhCaNhan.class;
        }

        if (target == null || activity.getClass().equals(target)) {
            return false;
        }

        activity.startActivity(new Intent(activity, target));
        return true;
    }

    private static int layIconRes(int itemId) {
        if (itemId == R.id.nav_home) {
            return R.drawable.home_icon;
        }
        if (itemId == R.id.nav_pet) {
            return R.drawable.pet_icon;
        }
        if (itemId == R.id.nav_cart) {
            return R.drawable.cart_icon;
        }
        if (itemId == R.id.nav_user) {
            return R.drawable.user_icon;
        }
        return 0;
    }

    private static Drawable taoIconVuong(Context context, @DrawableRes int iconRes, int sizePx) {
        Bitmap source = BitmapFactory.decodeResource(context.getResources(), iconRes);
        Bitmap output = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        float scale = Math.min((float) sizePx / source.getWidth(), (float) sizePx / source.getHeight());
        int width = Math.max(1, Math.round(source.getWidth() * scale));
        int height = Math.max(1, Math.round(source.getHeight() * scale));
        int left = (sizePx - width) / 2;
        int top = (sizePx - height) / 2;

        canvas.drawBitmap(source, null, new Rect(left, top, left + width, top + height), paint);
        return new BitmapDrawable(context.getResources(), output);
    }
}
