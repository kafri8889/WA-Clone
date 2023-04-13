package com.anafthdev.waclone.uicomponent

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.anafthdev.waclone.data.LocalChatDataProvider
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
				Text(chat.text)
			}
		)
	}
}

@Composable
fun ChatBox(
	chat: Chat,
	modifier: Modifier = Modifier,
	showTriangle: Boolean = true,
	containerColor: Color = MaterialTheme.colorScheme.chatBoxContainer,
	messageContent: @Composable () -> Unit
) {

	Row {
		BoxWithConstraints(
			modifier = modifier
				.weight(1f)
				.clip(
					RoundedCornerShape(
						topStart = 16.dp,
						bottomEnd = 16.dp,
						bottomStart = 16.dp,
						topEnd = if (showTriangle) 0.dp else 16.dp
					)
				)
				.background(containerColor)
				.padding(8.dp)
		) {
			Column(
				modifier = Modifier
					.width(maxWidth)
			) {
				ProvideTextStyle(
					value = MaterialTheme.typography.bodyMedium.copy(
						color = MaterialTheme.colorScheme.chatContentColor
					)
				) {
					messageContent()
				}
				
				Row(
					verticalAlignment = Alignment.Bottom,
					modifier = Modifier
						.align(Alignment.End)
				) {
					Text(
						text = "${hourMinuteFormatter(chat.hour)}:${hourMinuteFormatter(chat.minute)}",
						style = MaterialTheme.typography.labelSmall.copy(
							color = MaterialTheme.colorScheme.chatTextSupport
						)
					)
					
					Spacer(modifier = Modifier.width(4.dp))
					
					Image(
						painter = painterResource(id = chat.sendStatus.icon),
						contentDescription = null,
						modifier = Modifier
							.width(16.dp)
							.aspectRatio(1f)
					)
				}
			}
		}
		
		if (showTriangle) {
			Triangle(
				containerColor = containerColor,
				modifier = Modifier
					.offset(
						x = (-8).dp
					)
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
