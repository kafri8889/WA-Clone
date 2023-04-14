package com.anafthdev.waclone.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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
	
	val localizedName: String
		@Composable
		get() = when (this) {
			Send -> stringResource(id = R.string.send)
			Sended -> stringResource(id = R.string.sended)
			Viewed -> stringResource(id = R.string.viewed)
		}
}