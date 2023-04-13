package com.anafthdev.waclone.ui.chat_config.subscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anafthdev.waclone.R

@Composable
fun ChatConfigSetContactName(
	onContactNameUpdated: (String) -> Unit
) {
	
	var name by remember { mutableStateOf("") }
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxWidth()
	) {
		OutlinedTextField(
			value = name,
			singleLine = true,
			onValueChange = {
				name = it
			},
			trailingIcon = {
				IconButton(
					onClick = {
						onContactNameUpdated(name)
					}
				) {
					Icon(
						painter = painterResource(id = R.drawable.ic_check),
						contentDescription = null
					)
				}
			},
			modifier = Modifier
				.fillMaxWidth(0.94f)
		)
		
		Spacer(modifier = Modifier.height(16.dp))
	}
	
}
