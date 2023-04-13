package com.anafthdev.waclone.ui.chat_config

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatConfigTypeItem(
	type: ChatConfigType,
	modifier: Modifier = Modifier,
	onClick: () -> Unit
) {
	
	OutlinedCard(
		onClick = onClick,
		modifier = modifier
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.padding(16.dp)
				.align(Alignment.CenterHorizontally)
		) {
			Text(
				text = type.localizedName,
				style = MaterialTheme.typography.bodyMedium
			)
		}
	}
}
