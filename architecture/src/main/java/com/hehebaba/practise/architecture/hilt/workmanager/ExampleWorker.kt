package com.hehebaba.practise.architecture.hilt.workmanager

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.Worker
import androidx.work.WorkerParameters

class ExampleWorker @WorkerInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParameters: WorkerParameters
) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }
}