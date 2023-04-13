package com.anafthdev.waclone.ui.chat

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(

): ViewModel() {
	
	var contactName by mutableStateOf("")
		private set
	
	var messageText by mutableStateOf("")
		private set
	
	var placeholderText by mutableStateOf("Ketik pesan")
		private set
	
	var backgroundImage by mutableStateOf(Uri.EMPTY)
		private set
	
	var image by mutableStateOf("file:///android_asset/hanni.jpg".toUri())
		private set
	
	fun updateContactName(name: String) {
		contactName
	}
	
	fun updateMessageText(text: String) {
		messageText = text
	}
	
	fun updatePlaceholderText(text: String) {
		placeholderText = text
	}
	
	fun updateBackgroundImage(img: Uri) {
		backgroundImage = img
	}
	
	fun updateImage(img: Uri) {
		image = img
	}
	
}