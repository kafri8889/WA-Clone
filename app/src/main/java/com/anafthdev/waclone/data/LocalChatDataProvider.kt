package com.anafthdev.waclone.data

import com.anafthdev.waclone.model.Chat

object LocalChatDataProvider {
	
	val simple = Chat(
		text = "Simple",
		hour = 23,
		minute = 12,
		sendStatus = SendStatus.Sended
	)
	
	val long = Chat(
		text = "Simple llllllllllllllllllllllllooooooooooooooooooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnngggggggggggggggggggggggggggg mmmmmmmmmmmmmmeeeeeeeeeeeeeeessssssssssseeeeeeeeeeeeejjjjjjjjjjjj",
		hour = 0,
		minute = 1,
		sendStatus = SendStatus.Viewed
	)
	
}