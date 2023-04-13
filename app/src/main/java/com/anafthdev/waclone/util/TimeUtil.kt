package com.anafthdev.waclone.util

fun hourMinuteFormatter(hourMinute: Int): String {
	return if (hourMinute < 10) "0$hourMinute" else "$hourMinute"
}
