package com.anafthdev.waclone.uicomponent

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anafthdev.waclone.theme.WACloneTheme
import com.anafthdev.waclone.util.chatDateContainer
import com.anafthdev.waclone.util.chatDateText

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ChatDateItemPreviewDark() {
	WACloneTheme {
		ChatDateItem(date = "12 April 2023")
	}
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
private fun ChatDateItemPreviewLight() {
	WACloneTheme {
		ChatDateItem(date = "12 April 2023")
	}
}

@Composable
fun ChatDateItem(
	date: String,
	modifier: Modifier = Modifier
) {

	Card(
		modifier = modifier,
		shape = RoundedCornerShape(32),
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.chatDateContainer
		)
	) {
		Text(
			text = date,
			style = MaterialTheme.typography.bodySmall.copy(
				color = MaterialTheme.colorScheme.chatDateText,
				fontWeight = FontWeight.Bold
			),
			modifier = Modifier
				.padding(8.dp)
		)
	}
}
