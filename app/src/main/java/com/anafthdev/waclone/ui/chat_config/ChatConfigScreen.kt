package com.anafthdev.waclone.ui.chat_config

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anafthdev.waclone.R
import com.anafthdev.waclone.ui.chat_config.subscreen.ChatConfigSetContactName
import com.anafthdev.waclone.uicomponent.DragHandle

@Composable
fun ChatConfigScreen(
	navController: NavController,
	viewModel: ChatConfigViewModel
) {
	val context = LocalContext.current
	val imageLauncher = rememberLauncherForActivityResult(
		contract = ActivityResultContracts.GetContent(),
		onResult = { uri ->
			uri?.let {
				viewModel.updateImage(uri)
			}
		}
	)
	
	LaunchedEffect(viewModel.chatConfigType) {
		viewModel.chatConfigType?.let {  type ->
			when (type) {
				ChatConfigType.SetImageBackground -> imageLauncher.launch("image/*")
				ChatConfigType.SetPhotoProfile -> imageLauncher.launch("image/*")
				else -> {}
			}
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
					ChatConfigType.SetContactName -> ChatConfigSetContactName()
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
	SetContactName;
	
	val localizedName: String
		@Composable
		get() = when (this) {
			SetImageBackground -> stringResource(id = R.string.change_background)
			SetPhotoProfile -> stringResource(id = R.string.change_profile_picture)
			SetContactName -> stringResource(id = R.string.change_contact_name)
		}
}

enum class ChatConfigScreenType {
	ConfigSelector,
	Config
}
