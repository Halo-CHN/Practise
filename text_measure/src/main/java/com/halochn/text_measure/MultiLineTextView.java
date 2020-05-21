package com.halochn.text_measure;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class MultiLineTextView extends View {

    private String TAG = MultiLineTextView.class.getSimpleName();

    private float textSize = dpToPixel(13);
    private float contentTextMarginLeft = dpToPixel(3);
    private float contentTextSpacing = dpToPixel(18);//行间距
    private float contentTextMargin = dpToPixel(7);//行间距

    private float beginningTextSize = dpToPixel(9);
    private float beginningTextAreaWidth = dpToPixel(28);//行号背景宽度
    private float beginningTextAreaHeight = dpToPixel(14);//行号背景高度
    private float beginningBgRadius = dpToPixel(7);//行号背景半径
    private float beginningBgStrokeWidth = dpToPixel(1);//行号背景粗细

    //内容文字颜色
    private int textColor = Color.parseColor("#999999");
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float contentOffsetX = beginningTextAreaWidth + contentTextMarginLeft;
    private float[] measureWidth = new float[1];

    //行号文字背景渐变色
    private int beginningBgStartColorOdd = Color.parseColor("#FCD586");
    private int beginningBgEndColorOdd = Color.parseColor("#FF8C00");
    private int beginningBgStartColorEven = Color.parseColor("#AFCEF9");
    private int beginningBgEndColo2Even = Color.parseColor("#599FFD");
    private Paint beginningBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int beginningTextColorOdd = Color.parseColor("#FF8C00");
    private int beginningTextColorEven = Color.parseColor("#599FFD");

    //行号文字颜色
    private Paint beginningTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private LinkedHashMap<String, String> titleTextMap;
    private String[] multiText;//文字

    private float verticalOffset = 0;//纵向偏移量，用于计算多行文字位置

    private Rect textBounds = new Rect();

    private boolean useFontMetrics = false;
    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    public MultiLineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    {
        Log.d(TAG, " textSize: " + textSize);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

//        LinearGradient linearGradient = new LinearGradient(0, 0, beginningBgRadius * 2, beginningBgRadius * 2, beginningBgStartColor, beginningBgEndColor, Shader.TileMode.CLAMP);
//        beginningBgPaint.setShader(linearGradient);
        beginningBgPaint.setStrokeWidth(beginningBgStrokeWidth);
        beginningBgPaint.setStyle(Paint.Style.STROKE);

        beginningTextPaint.setTextSize(beginningTextSize);
    }

    public void setTitleTextMap(LinkedHashMap<String, String> titleTextMap) {
        this.titleTextMap = titleTextMap;
        invalidate();
    }

    public void setLinesText(String... multiText) {
        this.multiText = multiText;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getMeasuredHeight();
        Log.d(TAG, "onMeasure: widthMeasureSpec: " + MeasureSpec.toString(widthMeasureSpec) + " heightMeasureSpec: " + MeasureSpec.toString(heightMeasureSpec));
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
        Log.d(TAG, "layout: " + " " + l + " " + t + " " + r + " " + b);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: " + changed + " " + left + " " + top + " " + right + " " + bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //每次绘制重置纵向偏移量
        verticalOffset = 0;
        if (null != titleTextMap && !titleTextMap.isEmpty()) {
            Iterator iterator = titleTextMap.keySet().iterator();
            int index = 1;
            while (iterator.hasNext()) {
                String title = String.valueOf(iterator.next());
                drawOneText(index++, canvas, title, titleTextMap.get(title));
            }
        } else {
            drawOneText(0, canvas, "", "");
        }
    }

    private void drawOneText(int textNum, Canvas canvas, String title, String text) {
        //计算内容文字偏移量
        float contentOffsetY;

        //绘制标题背景位置
        float beginningBgCenterX = beginningTextAreaWidth / 2;
        float beginningBgOffsetY;

        if (useFontMetrics) {
            textPaint.getFontMetrics(fontMetrics);
            if (verticalOffset == 0) {
                //计算文字初始位置偏移
                verticalOffset -= fontMetrics.ascent;
            } else {
                verticalOffset += contentTextMargin;
            }
            contentOffsetY = (fontMetrics.ascent + fontMetrics.descent) / 2F;
            beginningBgOffsetY = verticalOffset;
        } else {
            textPaint.getTextBounds(text, 0, text.length(), textBounds);
            if (verticalOffset == 0) {
                //计算文字初始位置偏移
                verticalOffset -= textBounds.top;
            } else {
                verticalOffset += contentTextMargin;
            }
            contentOffsetY = (textBounds.top + textBounds.bottom) / 2F;
            beginningBgOffsetY = verticalOffset;
        }


        //绘制标题背景
        beginningBgPaint.setColor(textNum % 2 == 0 ? beginningBgEndColo2Even : beginningBgEndColorOdd);
        beginningBgPaint.setShader(new LinearGradient(0, verticalOffset, beginningBgRadius * 2, verticalOffset + beginningBgRadius * 2, textNum % 2 == 0 ? beginningBgStartColorEven : beginningBgStartColorOdd, textNum % 2 == 0 ? beginningBgEndColo2Even : beginningBgEndColorOdd, Shader.TileMode.CLAMP));
//        canvas.drawCircle(beginningBgCenterX, beginningBgCenterY, beginningBgRadius, beginningBgPaint);
        float bgTop = verticalOffset - (beginningTextAreaHeight / 2);
        canvas.drawRoundRect(0, bgTop, beginningTextAreaWidth, bgTop + beginningTextAreaHeight, beginningBgRadius, beginningBgRadius, beginningBgPaint);

        beginningTextPaint.getTextBounds(title, 0, title.length(), textBounds);

        //计算标题文字偏移量
        float beginningTextOffsetY = beginningBgOffsetY - ((textBounds.top + textBounds.bottom) / 2F);
        float beginningTextOffsetX = beginningBgCenterX - ((textBounds.right + textBounds.left) / 2F);
        //1的时候要做居中处理
        int numOffsetX = (textNum == 1 ? 2 : 0);
        //绘制标题文字
        beginningTextPaint.setColor(textNum % 2 == 0 ? beginningBgEndColo2Even : beginningBgEndColorOdd);
        canvas.drawText(title, beginningTextOffsetX, beginningTextOffsetY, beginningTextPaint);

        //绘制内容
        textPaint.setTextSize(textSize);
        int textLength = text.length();
        for (int start = 0, count; start < textLength; start += count, verticalOffset += contentTextSpacing) {
            count = textPaint.breakText(text, start, textLength, true, getWidth() - contentOffsetX, measureWidth);
            canvas.drawText(text, start, start + count, contentOffsetX, verticalOffset - contentOffsetY, textPaint);
        }
    }

    private float dpToPixel(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
