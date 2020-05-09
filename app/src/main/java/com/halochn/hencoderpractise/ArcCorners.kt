package com.halochn.hencoderpractise

import android.graphics.*
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.util.Util
import java.nio.ByteBuffer
import java.security.MessageDigest

class ArcCorners(val bottomMargin: Float) : BitmapTransformation() {

    val ID = "io.github.halo-chn.ArcCorners"
    val ID_BYTES = ID.toByteArray(Key.CHARSET)

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return arcCorners(pool, toTransform)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)

        val bottomData =
            ByteBuffer.allocate(4).putFloat(bottomMargin).array()
        messageDigest.update(bottomData)
    }

    override fun equals(other: Any?): Boolean {
        if (other is ArcCorners) {
            return other.bottomMargin == bottomMargin
        }
        return false
    }

    private fun arcCorners(
        pool: BitmapPool, inBitmap: Bitmap
    ): Bitmap { // Alpha is required for this transformation.
        val safeConfig =
            getAlphaSafeConfig(inBitmap)
        val toTransform =
            getAlphaSafeBitmap(pool, inBitmap)
        val result =
            pool[toTransform.width, toTransform.height, safeConfig]
        result.setHasAlpha(true)
        val shader = BitmapShader(
            toTransform,
            Shader.TileMode.CLAMP,
            Shader.TileMode.CLAMP
        )
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = shader

        val path = Path()

        //矩形区域
        val rectF = RectF(
            0F,
            0F,
            toTransform.width.toFloat(),
            toTransform.height.toFloat() - bottomMargin
        )

        //弧形平整度(0.1~1) 值越大 弧度越平整
        val arcSlope = 0.2F

        //弧形区域
        val arcRectF = RectF(
            0F - (bottomMargin * arcSlope),
            rectF.bottom - bottomMargin,
            toTransform.width.toFloat() + (bottomMargin * arcSlope),
            toTransform.height.toFloat()
        )

        path.addArc(
            arcRectF,
            0F,
            180F
        )

        path.addRect(
            rectF,
            Path.Direction.CW
        )

        val canvas = Canvas(result)
        try {
            canvas.drawColor(
                Color.TRANSPARENT,
                PorterDuff.Mode.CLEAR
            )
            canvas.drawPath(path, paint)
        } finally {
            canvas.setBitmap(null)
        }
        if (toTransform != inBitmap) {
            pool.put(toTransform)
        }
        return result
    }

    private fun getAlphaSafeConfig(inBitmap: Bitmap): Bitmap.Config {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Avoid short circuiting the sdk check.
            if (Bitmap.Config.RGBA_F16 == inBitmap.config) { // NOPMD
                return Bitmap.Config.RGBA_F16
            }
        }
        return Bitmap.Config.ARGB_8888
    }

    private fun getAlphaSafeBitmap(
        pool: BitmapPool, maybeAlphaSafe: Bitmap
    ): Bitmap {
        val safeConfig =
            getAlphaSafeConfig(maybeAlphaSafe)
        if (safeConfig == maybeAlphaSafe.config) {
            return maybeAlphaSafe
        }
        val argbBitmap =
            pool[maybeAlphaSafe.width, maybeAlphaSafe.height, safeConfig]
        Canvas(argbBitmap).drawBitmap(maybeAlphaSafe, 0f, 0f, null /*paint*/)
        // We now own this Bitmap. It's our responsibility to replace it in the pool outside this method
// when we're finished with it.
        return argbBitmap
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(), Util.hashCode(bottomMargin))
    }
}