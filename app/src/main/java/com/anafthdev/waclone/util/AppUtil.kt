package com.anafthdev.waclone.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast

fun Any?.toast(context: Context, length: Int = Toast.LENGTH_SHORT) {
	Handler(Looper.getMainLooper()).post {
		Toast.makeText(context, toString(), length).show()
	}
}
