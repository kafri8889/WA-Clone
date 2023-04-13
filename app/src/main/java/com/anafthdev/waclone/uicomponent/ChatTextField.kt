package com.anafthdev.waclone.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.anafthdev.waclone.util.fabContainer
import com.anafthdev.waclone.util.topBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTextField(
	value: String,
	onValueChange: (String) -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	readOnly: Boolean = false,
	textStyle: TextStyle = LocalTextStyle.current,
	containerColor: Color = MaterialTheme.colorScheme.topBar,
	placeholder: @Composable (() -> Unit)? = null,
	leadingIcon: @Composable (() -> Unit)? = null,
	trailingIcon: @Composable (() -> Unit)? = null,
) {
	
	BasicTextField(
		value = value,
		maxLines = 6,
		onValueChange = onValueChange,
		textStyle = textStyle,
		enabled = enabled,
		singleLine = false,
		readOnly = readOnly,
		cursorBrush = Brush.verticalGradient(
			listOf(
				MaterialTheme.colorScheme.fabContainer,
				MaterialTheme.colorScheme.fabContainer
			)
		),
		decorationBox = { innerTextField ->
			Row(
				verticalAlignment = if (value.contains('\n')) Alignment.Bottom
				else Alignment.CenterVertically
			) {
				if (leadingIcon != null) leadingIcon()
				
				Box(Modifier.weight(1f)) {
					if (value.isEmpty() && placeholder != null) {
						ProvideTextStyle(value = MaterialTheme.typography.bodyLarge) {
							placeholder()
						}
					}
					
					Box(
						modifier = Modifier
							.padding(
								vertical = if (value.contains('\n')) 4.dp else 0.dp
							)
					) {
						innerTextField()
					}
				}
				
				if (trailingIcon != null) trailingIcon()
			}
//			TextFieldDefaults.DecorationBox(
//				value = value,
//				innerTextField = it,
//				enabled = enabled,
//				singleLine = false,
//				shape = RoundedCornerShape(100),
//				visualTransformation = VisualTransformation.None,
//				interactionSource = MutableInteractionSource(),
//				placeholder = placeholder,
//				leadingIcon = leadingIcon,
//				trailingIcon = trailingIcon,
//				colors = TextFieldDefaults.colors(
//					focusedContainerColor = MaterialTheme.colorScheme.topBar,
//					unfocusedContainerColor = MaterialTheme.colorScheme.topBar,
//					focusedIndicatorColor = Color.Transparent,
//					unfocusedIndicatorColor = Color.Transparent
//				),
//				contentPadding = TextFieldDefaults.contentPaddingWithoutLabel(
//					top = 0.dp,
//					bottom = 0.dp,
//				),
//			)
		},
		modifier = modifier
			.heightIn(min = 48.dp)
			.background(
				color = containerColor,
				shape = if (value.contains("\n\n")) RoundedCornerShape(16.dp)
				else RoundedCornerShape(100)
			)
	)
}
