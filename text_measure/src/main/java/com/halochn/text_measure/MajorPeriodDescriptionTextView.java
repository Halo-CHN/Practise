package com.halochn.text_measure;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

public class MajorPeriodDescriptionTextView extends LinearLayout {

    private float contentTextMarginLeft = dpToPixel(3);
    private float beginningTextAreaWidth = dpToPixel(28);//行号背景宽度
    private float beginningTextAreaHeight = dpToPixel(14);//行号背景高度

    //内容文字颜色
    private int textColor = Color.parseColor("#999999");

    private TextView leftDrawable;
    private TextView tvDescription;

    public MajorPeriodDescriptionTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParams.setMargins((int) contentTextMarginLeft, 0, 0, 0);
        tvDescription = new TextView(getContext());
        tvDescription.setTextColor(textColor);
        tvDescription.setLayoutParams(marginLayoutParams);
        MarginLayoutParams layoutParams = new MarginLayoutParams((int) beginningTextAreaWidth, (int) beginningTextAreaHeight);
        layoutParams.setMargins(0, (int) contentTextMarginLeft, 0, 0);
        leftDrawable = new TextView(getContext());
        leftDrawable.setLayoutParams(layoutParams);
        addView(leftDrawable);
        addView(tvDescription);
    }

    /**
     * 设置描述
     *
     * @param type 类型
     * @param text 内容
     */
    public void setDescription(String type, CharSequence text) {
        Object tag = tvDescription.getTag(R.integer.tag_major_period_description);
        boolean same = null != tag && String.valueOf(tag).equals(type);
        if (!same) {//类型不同，重新设置左标签
            leftDrawable.setBackground(promoteDrawable(type));
        }
        tvDescription.setText(text);
    }

    private Drawable promoteDrawable(String type) {
        Drawable drawable;
        switch (type) {
            case "外观":
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.common_period_outward_icon);
                break;
            case "内饰":
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.common_period_interior_icon);
                break;
            case "动力":
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.common_period_power_icon);
                break;
            default:
                drawable = AppCompatResources.getDrawable(getContext(), R.drawable.common_period_others_icon);
                break;
        }
        //drawable.setBounds(0, 0, (int) beginningTextAreaWidth, (int) beginningTextAreaHeight);
        return drawable;
    }

    private float dpToPixel(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
