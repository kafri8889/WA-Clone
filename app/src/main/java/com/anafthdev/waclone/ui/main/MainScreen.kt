package com.anafthdev.waclone.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anafthdev.waclone.data.WACDestination
import com.anafthdev.waclone.runtime.navigation.ChatNavHost

@Composable
fun MainScreen() {
	
	val navController = rememberNavController()
	
	Surface(
		color = MaterialTheme.colorScheme.background,
		modifier = Modifier
			.fillMaxSize()
	) {
		NavHost(
			navController = navController,
			startDestination = WACDestination.Chat.Root.route
		) {
			ChatNavHost(navController)
		}
	}
}
