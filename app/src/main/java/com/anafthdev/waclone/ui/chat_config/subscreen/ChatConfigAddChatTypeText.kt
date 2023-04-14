package com.anafthdev.waclone.ui.chat_config.subscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anafthdev.waclone.R
import com.anafthdev.waclone.data.SendStatus
import com.anafthdev.waclone.util.hourMinuteFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatConfigAddChatTypeText(
	text: String,
	hour: Int,
	minute: Int,
	sendStatus: SendStatus,
	onSendStatusChange: (SendStatus) -> Unit,
	onValueChange: (String) -> Unit,
	onTimeClicked: () -> Unit,
	onAdd: () -> Unit
) {
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxWidth()
	) {
		OutlinedTextField(
			value = text,
			onValueChange = onValueChange,
			label = {
				Text(stringResource(id = R.string.text))
			},
			modifier = Modifier
				.fillMaxWidth(0.94f)
		)
		
		Spacer(modifier = Modifier.height(8.dp))
		
		OutlinedTextField(
			value = "${hourMinuteFormatter(hour)}:${hourMinuteFormatter(minute)}",
			singleLine = true,
			readOnly = true,
			maxLines = 8,
			onValueChange = {},
			label = {
				Text(stringResource(id = R.string.time))
			},
			trailingIcon = {
				IconButton(onClick = onTimeClicked) {
					Icon(
						painter = painterResource(id = R.drawable.ic_clock),
						contentDescription = null
					)
				}
			},
			modifier = Modifier
				.fillMaxWidth(0.94f)
		)
		
		Spacer(modifier = Modifier.height(8.dp))
		
		LazyRow(
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {
			items(SendStatus.values()) { status ->
				FilterChip(
					selected = sendStatus == status,
					onClick = {
						onSendStatusChange(status)
					},
					label = {
						Text(status.localizedName)
					}
				)
			}
		}
		
		Spacer(modifier = Modifier.height(8.dp))
		
		Button(
			onClick = onAdd,
			enabled = text.isNotBlank(),
			modifier = Modifier
				.fillMaxWidth(0.94f)
		) {
			Text(stringResource(id = R.string.add))
		}
		
		Spacer(modifier = Modifier.height(8.dp))
	}
	
}
