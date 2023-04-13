package com.anafthdev.waclone.ui.chat_config

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.waclone.common.SharedData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatConfigViewModel @Inject constructor(
	private val sharedData: SharedData
): ViewModel() {
	
	var chatConfigScreenType by mutableStateOf(ChatConfigScreenType.ConfigSelector)
		private set
	
	var chatConfigType by mutableStateOf<ChatConfigType?>(null)
		private set
	
	fun updateChatConfigScreenType(type: ChatConfigScreenType) {
		chatConfigScreenType = type
	}
	
	fun updateChatConfigType(type: ChatConfigType?) {
		chatConfigType = type
	}
	
	fun updateImage(uri: Uri) {
		Timber.i("update: $uri | type: $chatConfigType")
		
		viewModelScope.launch {
			when (chatConfigType) {
				ChatConfigType.SetImageBackground -> sharedData.updateBackgroundImage(uri)
				ChatConfigType.SetPhotoProfile -> sharedData.updateImage(uri)
				else -> {}
			}
		}
		
		chatConfigType = null
	}
	
}