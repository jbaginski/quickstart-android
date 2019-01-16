package com.google.firebase.quickstart.perfmon

import android.app.Application
import android.os.AsyncTask
import android.os.StrictMode
import com.google.firebase.perf.FirebasePerformance

class PerfApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        enableStrictMode()
        enableFirebasePerformance()
    }

    private companion object {
        private fun enableStrictMode() {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyDeath()
                    .penaltyLog()
                    .build()
            )
        }

        private fun enableFirebasePerformance() {
            // enable it on background thread
            AsyncTask.THREAD_POOL_EXECUTOR.execute {
                FirebasePerformance.getInstance()
                    .isPerformanceCollectionEnabled = true
            }
        }
    }
}