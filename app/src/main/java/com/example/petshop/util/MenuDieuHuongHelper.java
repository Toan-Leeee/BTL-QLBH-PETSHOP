package com.example.petshop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;

import androidx.annotation.DrawableRes;

import com.example.petshop.R;

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

    private static int layIconRes(int itemId) {
        if (itemId == R.id.nav_home || itemId == R.id.menu_trangchu) {
            return R.drawable.home_icon;
        }
        if (itemId == R.id.nav_pet || itemId == R.id.menu_sanpham) {
            return R.drawable.pet_icon;
        }
        if (itemId == R.id.nav_cart || itemId == R.id.menu_giohang) {
            return R.drawable.cart_icon;
        }
        if (itemId == R.id.nav_user || itemId == R.id.menu_taikhoan) {
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

        canvas.drawBitmap(
                source,
                null,
                new Rect(left, top, left + width, top + height),
                paint
        );

        return new BitmapDrawable(context.getResources(), output);
    }
}
