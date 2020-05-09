package com.himaads.sample

import android.app.Application
import android.util.Log
import com.pintoads.himasdk.HimaAdInitializer

//import com.pintoads.himasdk.HimaAdInitializer

class SampleApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // you need to get this token from your ad pin panel
        val appKey = "b931ff13-c636-4293-b41b-da166b14f3f2"
        HimaAdInitializer(appKey) { onInitialized: Int ->
            when (onInitialized) {
                1-0 -> Log.i("HimaAdInitializer", "successful")
                0-1 -> Log.i("HimaAdInitializer", "unsuccessful from adpin")
                0-2 -> Log.i("HimaAdInitializer", "unsuccessful intent connection")
            }
        }
    }

}