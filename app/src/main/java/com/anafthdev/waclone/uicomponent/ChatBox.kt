package com.anafthdev.waclone.uicomponent

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.anafthdev.waclone.data.LocalChatDataProvider
import com.anafthdev.waclone.data.SendStatus
import com.anafthdev.waclone.extension.lineTo
import com.anafthdev.waclone.extension.moveTo
import com.anafthdev.waclone.model.Chat
import com.anafthdev.waclone.theme.WACloneTheme
import com.anafthdev.waclone.util.chatBoxContainer
import com.anafthdev.waclone.util.chatContentColor
import com.anafthdev.waclone.util.chatTextSupport
import com.anafthdev.waclone.util.hourMinuteFormatter

private class ChatParameterProvider: PreviewParameterProvider<Chat> {
	override val count: Int
		get() = super.count
	override val values: Sequence<Chat>
		get() = sequenceOf(
			LocalChatDataProvider.simple,
			LocalChatDataProvider.long,
		)
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ChatBoxPreviewLight(
	@PreviewParameter(ChatParameterProvider::class) chat: Chat
) {
	
	WACloneTheme {
		ChatBox(
			chat = chat,
			messageContent = {
				Text(chat.text)
				false
			}
		)
	}
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ChatBoxPreviewDark(
	@PreviewParameter(ChatParameterProvider::class) chat: Chat
) {
	
	WACloneTheme {
		ChatBox(
			chat = chat,
			messageContent = {
				Text(
					text = chat.text
				)
			}
		)
	}
}

@Composable
fun ChatBox(
	chat: Chat,
	modifier: Modifier = Modifier,
	multiLine: Boolean = false,
	showTriangle: Boolean = true,
	containerColor: Color = MaterialTheme.colorScheme.chatBoxContainer,
	messageContent: @Composable () -> Unit
) {
	
	Row(
		modifier = Modifier
			.offset(x = 12.dp)
			.then(modifier)
	) {
		BoxWithConstraints(
			modifier = Modifier
				.weight(1f, false)
				.clip(
					RoundedCornerShape(
						topStart = 16.dp,
						bottomEnd = 16.dp,
						bottomStart = 16.dp,
						topEnd = if (showTriangle) 0.dp else 16.dp
					)
				)
				.background(containerColor)
				.padding(
					horizontal = 8.dp,
					vertical = 6.dp
				)
		) {
			if (multiLine) {
				Column(
					modifier = Modifier
				) {
					MessageContent(
//						messageContent = { Text("Longn mmmmeeeeesssseejjjjjj mmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjjmmmmeeeeesssseejjjjjj") },
						messageContent = messageContent,
						modifier = Modifier
							.padding(end = 24.dp)
							.align(Alignment.Start)
					)
					
					Spacer(modifier = Modifier.width(8.dp))
					
					Row(
						verticalAlignment = Alignment.Bottom,
						modifier = Modifier
							.padding(horizontal = 2.dp)
							.align(Alignment.End)
					) {
						Text(
							text = "${hourMinuteFormatter(chat.hour)}:${hourMinuteFormatter(chat.minute)}",
							style = MaterialTheme.typography.labelMedium.copy(
								color = MaterialTheme.colorScheme.chatTextSupport,
								platformStyle = PlatformTextStyle(
									includeFontPadding = false
								),
								lineHeightStyle = LineHeightStyle(
									alignment = LineHeightStyle.Alignment.Bottom,
									trim = LineHeightStyle.Trim.LastLineBottom
								)
							)
						)

						Spacer(modifier = Modifier.width(4.dp))

						Image(
							painter = painterResource(id = chat.sendStatus.icon),
							contentDescription = null,
							colorFilter = when (chat.sendStatus) {
								SendStatus.Viewed -> null
								else -> ColorFilter.tint(MaterialTheme.colorScheme.chatTextSupport)
							},
							modifier = Modifier
								.width(18.dp)
								.aspectRatio(1f)
						)
					}
				}
			} else {
				Row(
					horizontalArrangement = Arrangement.End,
					modifier = Modifier
				) {
					MessageContent(
						messageContent = messageContent,
						modifier = Modifier
							.weight(1f, false)
							.align(Alignment.Top)
					)

					Spacer(modifier = Modifier.width(4.dp))

					Row(
						verticalAlignment = Alignment.Bottom,
						modifier = Modifier
							.padding(horizontal = 2.dp)
							.align(Alignment.Bottom)
					) {
						Text(
							text = "${hourMinuteFormatter(chat.hour)}:${hourMinuteFormatter(chat.minute)}",
							style = MaterialTheme.typography.labelMedium.copy(
								color = MaterialTheme.colorScheme.chatTextSupport,
								platformStyle = PlatformTextStyle(
									includeFontPadding = false
								),
								lineHeightStyle = LineHeightStyle(
									alignment = LineHeightStyle.Alignment.Bottom,
									trim = LineHeightStyle.Trim.LastLineBottom
								)
							)
						)

						Spacer(modifier = Modifier.width(4.dp))

						Image(
							painter = painterResource(id = chat.sendStatus.icon),
							contentDescription = null,
							colorFilter = when (chat.sendStatus) {
								SendStatus.Viewed -> null
								else -> ColorFilter.tint(MaterialTheme.colorScheme.chatTextSupport)
							},
							modifier = Modifier
								.width(18.dp)
								.aspectRatio(1f)
						)
					}
				}
			}
		}
		
		if (showTriangle) {
			Triangle(
				containerColor = containerColor,
				modifier = Modifier
					.offset(x = (-8).dp)
			)
		}
	}
}

//@Preview
@Composable
private fun Triangle(
	containerColor: Color,
	modifier: Modifier = Modifier
) {
	Canvas(
		modifier = modifier
			.size(24.dp)
	) {
		val rect = Rect(Offset.Zero, size)
		val trianglePath = Path().apply {
			moveTo(rect.topLeft)
			lineTo(rect.topRight)
			lineTo(rect.bottomLeft)
			close()
		}
		
		drawIntoCanvas { canvas ->
			canvas.drawOutline(
				outline = Outline.Generic(trianglePath),
				paint = Paint().apply {
					color = containerColor
					pathEffect = PathEffect.cornerPathEffect(rect.maxDimension / 3)
				}
			)
		}
	}
}

@Composable
fun MessageContent(
	modifier: Modifier = Modifier,
	messageContent: @Composable () -> Unit
) {
	
	Box(
		modifier = modifier
	) {
		ProvideTextStyle(
			value = MaterialTheme.typography.bodyLarge.copy(
				color = MaterialTheme.colorScheme.chatContentColor
			)
		) {
			messageContent()
		}
	}
}
