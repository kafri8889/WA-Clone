package com.anafthdev.waclone.util

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val ColorScheme.chatContentColor: Color
	@Composable
	get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val ColorScheme.chatTextSupport: Color
	@Composable
	get() = if (isSystemInDarkTheme()) Color(0xff8C8C8C) else Color(0xff8C8C8C)

val ColorScheme.chatBoxContainer: Color
	@Composable
	get() = if (isSystemInDarkTheme()) Color(0xff005d4b) else Color(0xffe7ffdb)

val ColorScheme.topBar: Color
	@Composable
	get() = if (isSystemInDarkTheme()) Color(0xff1f2c34) else Color(0xff008069)

val ColorScheme.navBar: Color
	@Composable
	get() = if (isSystemInDarkTheme()) Color(0xff0b141b) else Color(0xfffafafa)

val ColorScheme.fabContainer: Color
	@Composable
	get() = if (isSystemInDarkTheme()) Color(0xff00a884) else Color(0xff00a884)

