package com.anafthdev.waclone.ui.chat

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.anafthdev.waclone.R
import com.anafthdev.waclone.data.WACDestination
import com.anafthdev.waclone.theme.WACloneTheme
import com.anafthdev.waclone.uicomponent.ChatTextField
import com.anafthdev.waclone.util.chatContentColor
import com.anafthdev.waclone.util.fabContainer
import com.anafthdev.waclone.util.navBar
import com.anafthdev.waclone.util.topBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChatScreen(
	navController: NavController,
	viewModel: ChatViewModel,
	darkTheme: Boolean = isSystemInDarkTheme()
) {
	
	val topBarColor = MaterialTheme.colorScheme.topBar
	val navBarColor = MaterialTheme.colorScheme.navBar
	
	val systemUiController = rememberSystemUiController()
	
	SideEffect {
		systemUiController.setStatusBarColor(
			color = topBarColor
		)
		
		systemUiController.setNavigationBarColor(
			color = navBarColor
		)
	}
	
	LaunchedEffect(darkTheme) {
		if (viewModel.backgroundImage == Uri.EMPTY) {
			viewModel.updateBackgroundImage(
				if (darkTheme) "file:///android_asset/wa_chat_bg_dark.png".toUri()
				else "file:///android_asset/wa_chat_bg_light.png".toUri()
			)
		}
		
		if (viewModel.image == Uri.EMPTY) {
			viewModel.updateImage(
				if (darkTheme) "file:///android_asset/profile_picture_dark.png".toUri()
				else "file:///android_asset/profile_picture_light.png".toUri()
			)
		}
	}
	
	ChatScreenContent(
		contactName = viewModel.contactName,
		messageText = viewModel.messageText,
		placeholderText = viewModel.placeholderText,
		image = viewModel.image,
		backgroundImage = viewModel.backgroundImage,
		onValueChange = viewModel::updateMessageText,
		onOptionClicked = {
			navController.navigate(WACDestination.Sheet.ChatConfig.Home.route)
		}
	)
}

@Preview
@Composable
private fun ChatScreenContentPreview() {
	
	WACloneTheme {
		ChatScreenContent(
			contactName = "Hannnii",
			messageText = "",
			placeholderText = "Ketik pesan",
			image = "file:///android_asset/hanni.jpg".toUri(),
			backgroundImage = "file:///android_asset/hanni.jpg".toUri(),
			onOptionClicked = {},
			onValueChange = {}
		)
	}
}

@Composable
private fun ChatScreenContent(
	image: Uri,
	backgroundImage: Uri,
	contactName: String,
	messageText: String,
	placeholderText: String,
	onValueChange: (String) -> Unit,
	onOptionClicked: () -> Unit
) {
	
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		ChatTopAppBar(
			image = image,
			contactName = contactName,
			onOptionClicked = onOptionClicked
		)
		
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
		) {
			AsyncImage(
				model = backgroundImage,
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = Modifier
					.fillMaxSize()
					.zIndex(1f)
			)
			
			Row(
				verticalAlignment = Alignment.Bottom,
				modifier = Modifier
					.padding(4.dp)
					.fillMaxWidth()
					.zIndex(2f)
					.align(Alignment.BottomCenter)
			) {
				ChatTextField(
					value = messageText,
					onValueChange = onValueChange,
					textStyle = MaterialTheme.typography.bodyLarge.copy(
						color = MaterialTheme.colorScheme.chatContentColor,
						fontSize = 18.sp
					),
					placeholder = {
						Text(
							text = placeholderText
						)
					},
					leadingIcon = {
						IconButton(onClick = {}) {
							Icon(
								painter = painterResource(id = R.drawable.ic_grinning_face_with_big_eyes),
								contentDescription = null,
								tint = Color(0xff8596a0),
								modifier = Modifier
									.size(28.dp)
							)
						}
					},
					trailingIcon = {
						Row {
							IconButton(
								onClick = {},
								modifier = Modifier
									.offset(x = 4.dp)
							) {
								Icon(
									painter = painterResource(id = R.drawable.ic_clip),
									contentDescription = null,
									tint = Color(0xff8596a0),
									modifier = Modifier
										.size(28.dp)
								)
							}
							
							AnimatedVisibility(
								visible = messageText.isBlank()
							) {
								IconButton(onClick = {}) {
									Icon(
										painter = painterResource(id = R.drawable.ic_camera_wa),
										contentDescription = null,
										tint = Color(0xff8596a0),
										modifier = Modifier
											.size(28.dp)
									)
								}
							}
						}
					},
					modifier = Modifier
						.heightIn(min = 48.dp)
						.weight(1f)
				)
				
				Spacer(modifier = Modifier.width(4.dp))
				
				FloatingActionButton(
					shape = RoundedCornerShape(100),
					containerColor = MaterialTheme.colorScheme.fabContainer,
					onClick = {
					
					},
					modifier = Modifier
						.sizeIn(maxWidth = 48.dp, maxHeight = 48.dp)
				) {
					Icon(
						painter = painterResource(
							id = if (messageText.isEmpty()) R.drawable.ic_audio_record
							else R.drawable.ic_send
						),
						contentDescription = null,
						tint = Color.White
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopAppBar(
	image: Uri,
	contactName: String,
	modifier: Modifier = Modifier,
	onOptionClicked: () -> Unit
) {
	TopAppBar(
		modifier = modifier,
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.topBar
		),
		title = {
			Row(
				verticalAlignment = Alignment.CenterVertically
			) {
				AsyncImage(
					model = image,
					contentDescription = null,
					contentScale = ContentScale.Crop,
					modifier = Modifier
						.size(36.dp)
						.clip(RoundedCornerShape(100))
				)
				
				Spacer(modifier = Modifier.width(8.dp))
				
				Text(
					text = contactName,
					overflow = TextOverflow.Ellipsis,
					maxLines = 1,
					style = MaterialTheme.typography.titleLarge.copy(
						color = MaterialTheme.colorScheme.chatContentColor,
						fontWeight = FontWeight.Bold,
						fontSize = 20.sp
					)
				)
			}
		},
		navigationIcon = {
			Icon(
				imageVector = Icons.Default.ArrowBack,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.chatContentColor
			)
		},
		actions = {
			IconButton(
				onClick = {},
				modifier = Modifier
					.offset(x = 4.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_phone_add),
					contentDescription = null,
					tint = MaterialTheme.colorScheme.chatContentColor
				)
			}
			
			IconButton(onClick = onOptionClicked) {
				Icon(
					imageVector = Icons.Default.MoreVert,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.chatContentColor
				)
			}
		}
	)
}
