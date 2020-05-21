package com.halochn.text_measure;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import java.util.LinkedHashMap;

public class PeriodDescriptionTextView extends androidx.appcompat.widget.AppCompatTextView {

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

    {
//        LinearGradient linearGradient = new LinearGradient(0, 0, beginningBgRadius * 2, beginningBgRadius * 2, beginningBgStartColor, beginningBgEndColor, Shader.TileMode.CLAMP);
//        beginningBgPaint.setShader(linearGradient);
        beginningBgPaint.setStrokeWidth(beginningBgStrokeWidth);
        beginningBgPaint.setStyle(Paint.Style.STROKE);

        beginningTextPaint.setTextSize(beginningTextSize);
    }

    public PeriodDescriptionTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextColor(textColor);
        setPadding((int) (beginningTextAreaWidth + contentTextMarginLeft), 0, 0, 0);
    }

    private float dpToPixel(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    private int textNum;
    private String type;

    public void setDescription(String type, CharSequence description, int textNum) {
        this.type = type;
        this.textNum = textNum;
        setText(description);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (TextUtils.isEmpty(getText())) {
            return;
        }
        //计算内容文字偏移量
        float contentOffsetY;

        //绘制标题背景位置
        float beginningBgCenterX = beginningTextAreaWidth / 2;

        //绘制标题背景
        beginningBgPaint.setColor(textNum % 2 == 0 ? beginningBgEndColo2Even : beginningBgEndColorOdd);
        beginningBgPaint.setShader(new LinearGradient(0, verticalOffset, beginningBgRadius * 2, verticalOffset + beginningBgRadius * 2, textNum % 2 == 0 ? beginningBgStartColorEven : beginningBgStartColorOdd, textNum % 2 == 0 ? beginningBgEndColo2Even : beginningBgEndColorOdd, Shader.TileMode.CLAMP));
//        canvas.drawCircle(beginningBgCenterX, beginningBgCenterY, beginningBgRadius, beginningBgPaint);
        float bgTop = verticalOffset - (beginningTextAreaHeight / 2);
        int centerY = (int) (getLineHeight() / 2 + beginningBgStrokeWidth * 2);
        int roundRectOffset = (int) (beginningBgStrokeWidth / 2);
        canvas.drawRoundRect(roundRectOffset, centerY - (beginningTextAreaHeight / 2) + beginningBgStrokeWidth, beginningTextAreaWidth - roundRectOffset, centerY + (beginningTextAreaHeight / 2) - beginningBgStrokeWidth, beginningBgRadius, beginningBgRadius, beginningBgPaint);

        beginningTextPaint.getTextBounds(type, 0, type.length(), textBounds);

        //计算标题文字偏移量
        float beginningTextOffsetY = centerY - ((textBounds.top + textBounds.bottom) / 2F);
        float beginningTextOffsetX = beginningBgCenterX - ((textBounds.right + textBounds.left) / 2F);
        //1的时候要做居中处理
        int numOffsetX = (textNum == 1 ? 2 : 0);
        //绘制标题文字
        beginningTextPaint.setColor(textNum % 2 == 0 ? beginningBgEndColo2Even : beginningBgEndColorOdd);
        canvas.drawText(type, beginningTextOffsetX, beginningTextOffsetY, beginningTextPaint);
    }
}
