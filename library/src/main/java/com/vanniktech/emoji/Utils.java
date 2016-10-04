package com.vanniktech.emoji;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;

import java.nio.ByteBuffer;
import java.util.Arrays;

final class Utils {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    static void removeOnGlobalLayoutListener(final View v, final ViewTreeObserver.OnGlobalLayoutListener listener){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        } else {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        }
    }

    @NonNull
    static <T> T checkNotNull(@Nullable final T reference, final String message) {
        if (reference == null) {
            throw new IllegalArgumentException(message);
        }

        return reference;
    }

    public static Bitmap drawBitmap(String text){
        Bitmap b = Bitmap.createBitmap(50, 50, Bitmap.Config.ALPHA_8);
        Canvas c = new Canvas(b);
        c.drawText(text, 0, 50 / 2, paint);
        return b;
    }

    public static Paint paint = new Paint();

    private static byte[] missingCharacterByte = getPixels(drawBitmap("\u2936"));

    public static byte[] getPixels(Bitmap b) {
        ByteBuffer buffer = ByteBuffer.allocate(b.getByteCount());
        b.copyPixelsToBuffer(buffer);
        return buffer.array();
    }
    public static boolean isCharacterMissingInFont(String ch) {
        byte[] b1 = getPixels(drawBitmap(ch));
        return Arrays.equals(b1, missingCharacterByte);
    }

    private Utils() {
        throw new AssertionError("No instances.");
    }
}
