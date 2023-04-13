package com.anafthdev.waclone.runtime

import android.app.Application
import com.anafthdev.waclone.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WACApplication: Application() {
	
	override fun onCreate() {
		super.onCreate()
		
		if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
	}
}