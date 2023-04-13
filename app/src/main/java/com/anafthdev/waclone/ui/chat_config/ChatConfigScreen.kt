package com.anafthdev.waclone.ui.chat_config

import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.waclone.R
import com.anafthdev.waclone.model.ChatDate
import com.anafthdev.waclone.ui.chat_config.subscreen.ChatConfigSetContactName
import com.anafthdev.waclone.uicomponent.DragHandle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatConfigScreen(
	navController: NavController,
	viewModel: ChatConfigViewModel
) {
	
	val datePickerState = rememberDatePickerState(
		initialSelectedDateMillis = System.currentTimeMillis()
	)
	
	val imageLauncher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent(),
		onResult = { uri ->
			uri?.let {
				viewModel.updateImage(uri)
			}
		}
	)
	
	BackHandler(viewModel.chatConfigScreenType != ChatConfigScreenType.ConfigSelector) {
		viewModel.updateChatConfigScreenType(ChatConfigScreenType.ConfigSelector)
	}
	
	LaunchedEffect(viewModel.chatConfigType) {
		viewModel.chatConfigType?.let {  type ->
			when (type) {
				ChatConfigType.SetImageBackground -> imageLauncher.launch("image/*")
				ChatConfigType.SetPhotoProfile -> imageLauncher.launch("image/*")
				ChatConfigType.AddChatDate -> viewModel.showDatePicker()
				else -> {}
			}
		}
	}
	
	if (viewModel.isDatePickerShowed) {
		DatePickerDialog(
			onDismissRequest = viewModel::hideDatePicker,
			confirmButton = {
				Button(
					onClick = {
						viewModel.hideDatePicker()
						viewModel.addChatContent(
							ChatDate(
								date = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
							)
						)
					}
				) {
					Text(stringResource(id = R.string.ok))
				}
			},
			dismissButton = {
				TextButton(onClick = viewModel::hideDatePicker) {
					Text(stringResource(id = R.string.cancel))
				}
			}
		) {
			DatePicker(
				state = datePickerState
			)
		}
	}
	
	Column(horizontalAlignment = Alignment.CenterHorizontally) {
		DragHandle(modifier = Modifier.padding(16.dp))
		
		when (viewModel.chatConfigScreenType) {
			ChatConfigScreenType.ConfigSelector -> ChatConfigSelector(
				onTypeChange = viewModel::updateChatConfigType
			)
			ChatConfigScreenType.Config -> {
				when (viewModel.chatConfigType) {
					ChatConfigType.SetContactName -> ChatConfigSetContactName(
						onContactNameUpdated = viewModel::updateContactName
					)
					else -> ChatConfigSelector(
						onTypeChange = viewModel::updateChatConfigType
					)
				}
			}
		}
	}
}

@Composable
private fun ChatConfigSelector(
	onTypeChange: (ChatConfigType) -> Unit
) {
	
	LazyColumn(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxWidth()
	) {
		items(ChatConfigType.values()) { type ->
			ChatConfigTypeItem(
				type = type,
				onClick = {
					onTypeChange(type)
				},
				modifier = Modifier
					.fillMaxWidth(0.94f)
			)
		}
		
		item {
			Spacer(
				modifier = Modifier
					.padding(8.dp)
					.navigationBarsPadding()
			)
		}
	}
}

enum class ChatConfigType {
	SetImageBackground,
	SetPhotoProfile,
	SetContactName,
	AddChatDate;
	
	val localizedName: String
		@Composable
		get() = when (this) {
			SetImageBackground -> stringResource(id = R.string.change_background)
			SetPhotoProfile -> stringResource(id = R.string.change_profile_picture)
			SetContactName -> stringResource(id = R.string.change_contact_name)
			AddChatDate -> stringResource(id = R.string.add_chat_date)
		}
}

enum class ChatConfigScreenType {
	ConfigSelector,
	Config
}
