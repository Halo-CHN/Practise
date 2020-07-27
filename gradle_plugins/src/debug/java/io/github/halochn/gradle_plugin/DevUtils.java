package io.github.halochn.gradle_plugin;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

class DevUtils {
    public static void setUI(Activity activity) {
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        View badge = new View(activity);
        badge.setAlpha(0.5F);
        badge.setBackgroundColor(Color.YELLOW);
        decorView.addView(badge, -1, 150);
    }
}