package com.halochn.text_measure;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class MajorPeriodDescriptionView extends LinearLayout {

    private float contentTextMargin = dpToPixel(7);//行间距


    private LinkedHashMap<String, String> titleTextMap;

    public MajorPeriodDescriptionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public void setTitleTextMap(LinkedHashMap<String, String> titleTextMap) {
        this.titleTextMap = titleTextMap;
        initDescriptions();
    }

    private List<MajorPeriodDescriptionTextView> descriptionTextViews;

    private void initDescriptions() {
        if (null != titleTextMap && !titleTextMap.isEmpty()) {
            Iterator iterator = titleTextMap.keySet().iterator();
            int index = 0;
            MajorPeriodDescriptionTextView desTextView;
            while (iterator.hasNext()) {
                String type = String.valueOf(iterator.next());
                String description = titleTextMap.get(type);
                if (null != descriptionTextViews) {
                    if (index < descriptionTextViews.size()) {
                        desTextView = descriptionTextViews.get(index);
                    } else {
                        addDesView(desTextView = generateNewDescriptionTextView(type, (int) contentTextMargin));
                    }
                } else {
                    descriptionTextViews = new ArrayList<>();
                    addDesView(desTextView = generateNewDescriptionTextView(type, 0));
                }
                setDescription(desTextView, type, description);
                index++;
            }
            removeOverage();
        } else {
            removeAllViews();
        }
    }

    private void removeOverage() {
        if (this.titleTextMap.size() > descriptionTextViews.size()) {
            removeViews(descriptionTextViews.size() - 1, titleTextMap.size() - 1);
        }
    }

    private void addDesView(MajorPeriodDescriptionTextView textView) {
        descriptionTextViews.add(textView);
        addView(textView);
    }

    /**
     * 根据类型生成TextView类型
     *
     * @param type 类型
     * @return TextView 带有icon
     */
    private MajorPeriodDescriptionTextView generateNewDescriptionTextView(String type, int marginTop) {
        MajorPeriodDescriptionTextView desTextView = new MajorPeriodDescriptionTextView(getContext(), null);
        ViewGroup.MarginLayoutParams layoutParams = new MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, marginTop, 0, 0);
        desTextView.setLayoutParams(layoutParams);
        desTextView.setTag(R.integer.tag_major_period_description, type);
        return desTextView;
    }

    /**
     * 设置描述
     *
     * @param textView
     * @param type     类型
     * @param text     内容
     */
    private void setDescription(MajorPeriodDescriptionTextView textView, String type, CharSequence text) {
        textView.setDescription(type, text);
    }


    private float dpToPixel(int dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }
}
