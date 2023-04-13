package com.anafthdev.waclone.data

import com.anafthdev.waclone.R

enum class SendStatus {
	Send,
	Sended,
	Viewed;
	
	val icon: Int
		get() = when (this) {
			Send -> R.drawable.ic_check_send
			Sended -> R.drawable.ic_check_sended
			Viewed -> R.drawable.ic_check_viewed
		}
}