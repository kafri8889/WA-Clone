package com.anafthdev.waclone.common

import android.net.Uri
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SharedData @Inject constructor() {
	
	private val _contactName = MutableStateFlow("Name")
	val contactName: StateFlow<String> = _contactName.asStateFlow()
	
	private val _messageText = MutableStateFlow("")
	val messageText: StateFlow<String> = _messageText.asStateFlow()
	
	private val _placeholderText = MutableStateFlow("Ketik pesan")
	val placeholderText: StateFlow<String> = _placeholderText.asStateFlow()
	
	private val _backgroundImage = MutableStateFlow(Uri.EMPTY)
	val backgroundImage: StateFlow<Uri> = _backgroundImage.asStateFlow()
	
	private val _image = MutableStateFlow(Uri.EMPTY)
	val image: StateFlow<Uri> = _image.asStateFlow()
	
	suspend fun updateContactName(value: String) {
		_contactName.emit(value)
	}
	
	suspend fun updateMessageText(value: String) {
		_messageText.emit(value)
	}
	
	suspend fun updatePlaceholderText(value: String) {
		_placeholderText.emit(value)
	}
	
	suspend fun updateBackgroundImage(value: Uri) {
		_backgroundImage.emit(value)
	}
	
	suspend fun updateImage(value: Uri) {
		_image.emit(value)
	}
	
}