package com.anafthdev.waclone.model

import com.anafthdev.waclone.data.SendStatus

data class Chat(
	val text: String,
	val hour: Int,
	val minute: Int,
	val sendStatus: SendStatus
)
