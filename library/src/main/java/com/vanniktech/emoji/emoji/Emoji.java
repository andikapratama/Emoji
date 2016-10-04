package com.vanniktech.emoji.emoji;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class Emoji implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Paint paint = new Paint();
    private static byte[] missingCharacterByte = getPixels(drawBitmap("\u2936"));
    @NonNull
    private final String emoji;

    public Emoji(@NonNull final String emoji) {
        this.emoji = emoji;
    }

    public static Emoji fromCodePoint(final int codePoint) {
        return new Emoji(new String(Character.toChars(codePoint)));
    }

    public static Emoji fromChar(final char ch) {
        return new Emoji(Character.toString(ch));
    }

    public static Bitmap drawBitmap(String text) {
        Bitmap b = Bitmap.createBitmap(50, 50, Bitmap.Config.ALPHA_8);
        Canvas c = new Canvas(b);
        c.drawText(text, 0, 50 / 2, paint);
        return b;
    }

    static byte[] getPixels(Bitmap b) {
        ByteBuffer buffer = ByteBuffer.allocate(b.getByteCount());
        b.copyPixelsToBuffer(buffer);
        return buffer.array();
    }

    static boolean isCharacterMissingInFont(String ch) {
        byte[] b1 = getPixels(drawBitmap(ch));
        return Arrays.equals(b1, missingCharacterByte);
    }

    @NonNull
    public String getEmoji() {
        return emoji;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Emoji e = (Emoji) o;
        return emoji.equals(e.emoji);
    }

    @Override
    public int hashCode() {
        return emoji.hashCode();
    }

    public boolean isSupported() {
        //in marshmallow, this is the correct way to know whether an emoji is supported or not
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return paint.hasGlyph(getEmoji());
        }
        return !isCharacterMissingInFont(getEmoji());
    }
}
