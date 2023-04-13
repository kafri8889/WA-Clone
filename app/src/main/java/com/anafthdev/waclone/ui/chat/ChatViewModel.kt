package com.anafthdev.waclone.ui.chat

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.waclone.common.ChatContent
import com.anafthdev.waclone.common.SharedData
import com.anafthdev.waclone.extension.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
	private val sharedData: SharedData
): ViewModel() {
	
	var contactName by mutableStateOf("")
		private set
	
	var messageText by mutableStateOf("")
		private set
	
	var placeholderText by mutableStateOf("Ketik pesan")
		private set
	
	var backgroundImage by mutableStateOf(Uri.EMPTY)
		private set
	
	var image by mutableStateOf(Uri.EMPTY)
		private set
	
	var chatContents = mutableStateListOf<ChatContent>()
	
	init {
		viewModelScope.launch {
			sharedData.contactName.collect { name ->
				Timber.i("update contact name: $name")
				
				contactName = name
			}
		}
		
		viewModelScope.launch {
			sharedData.messageText.collect { text ->
				messageText = text
			}
		}
		
		viewModelScope.launch {
			sharedData.placeholderText.collect { text ->
				placeholderText = text
			}
		}
		
		viewModelScope.launch {
			sharedData.chatContents.collect { contents ->
				chatContents.swap(contents)
			}
		}
		
		viewModelScope.launch {
			sharedData.backgroundImage.collect { uri ->
				backgroundImage = uri
			}
		}
		
		viewModelScope.launch {
			sharedData.image.collect { uri ->
				Timber.i("update image: $uri")
				
				image = uri
			}
		}
	}
	
	fun updateContactName(name: String) {
		contactName = name
	}
	
	fun updateMessageText(text: String) {
		messageText = text
	}
	
	fun updatePlaceholderText(text: String) {
		placeholderText = text
	}
	
	fun updateBackgroundImage(img: Uri) {
		viewModelScope.launch {
			sharedData.updateBackgroundImage(img)
		}
	}
	
	fun updateImage(img: Uri) {
		viewModelScope.launch {
			sharedData.updateImage(img)
		}
	}
	
}