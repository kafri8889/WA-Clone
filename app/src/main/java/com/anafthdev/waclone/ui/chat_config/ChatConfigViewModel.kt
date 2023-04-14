package com.anafthdev.waclone.ui.chat_config

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anafthdev.waclone.common.ChatContent
import com.anafthdev.waclone.common.SharedData
import com.anafthdev.waclone.data.SendStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ChatConfigViewModel @Inject constructor(
	private val sharedData: SharedData
): ViewModel() {
	
	//////////////////////////////// ChatConfigAddChatType ////////////////////////////////
	
	var text by mutableStateOf("")
		private set
	
	var hour by mutableStateOf(0)
		private set
	
	var minute by mutableStateOf(0)
		private set
	
	var sendStatus by mutableStateOf(SendStatus.Send)
		private set
	
	//////////////////////////////// ChatConfigAddChatType ////////////////////////////////
	
	var isDatePickerShowed by mutableStateOf(false)
		private set
	
	var isTimePickerShowed by mutableStateOf(false)
		private set
	
	var chatConfigScreenType by mutableStateOf(ChatConfigScreenType.ConfigSelector)
		private set
	
	var chatConfigType by mutableStateOf<ChatConfigType?>(null)
		private set
	
	//////////////////////////////// ChatConfigAddChatType ////////////////////////////////
	
	fun updateText(txt: String) {
		text = txt
	}
	
	fun updateHour(h: Int) {
		hour = h
	}
	
	fun updateMinute(m: Int) {
		minute = m
	}
	
	fun updateSendStatus(status: SendStatus) {
		sendStatus = status
	}
	
	//////////////////////////////// ChatConfigAddChatType ////////////////////////////////
	
	fun updateChatConfigScreenType(type: ChatConfigScreenType) {
		chatConfigScreenType = type
	}
	
	fun updateChatConfigType(type: ChatConfigType?) {
		chatConfigType = type
		
		chatConfigScreenType = when (type) {
			ChatConfigType.SetContactName -> ChatConfigScreenType.Config
			ChatConfigType.AddChatTypeText -> ChatConfigScreenType.Config
			else -> ChatConfigScreenType.ConfigSelector
		}
	}
	
	fun updateContactName(name: String) {
		Timber.i("update contact name: $name")
		
		viewModelScope.launch {
			sharedData.updateContactName(name)
		}
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
	
	fun addChatContent(content: ChatContent) {
		viewModelScope.launch {
			sharedData.updateChatContents(
				sharedData.chatContents.value.toMutableList().apply {
					add(content)
				}
			)
		}
	}
	
	fun showDatePicker() {
		isDatePickerShowed = true
		
		chatConfigType = null
	}
	
	fun showTimePicker() {
		isTimePickerShowed = true
	}
	
	fun hideDatePicker() {
		isDatePickerShowed = false
		
		chatConfigType = null
	}
	
	fun hideTimePicker() {
		isTimePickerShowed = false
	}
	
}