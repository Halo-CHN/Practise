package com.hehebaba.practise.architecture.hilt

import android.content.Context
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class AnalyticsAdapter @Inject constructor(
    @ActivityContext private var context: Context,
    private var service: AnalyticsService
) {
}