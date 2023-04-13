package com.anafthdev.waclone.runtime.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anafthdev.waclone.data.WACDestination
import com.anafthdev.waclone.ui.chat.ChatScreen
import com.anafthdev.waclone.ui.chat.ChatViewModel

fun NavGraphBuilder.ChatNavHost(navController: NavController) {
	navigation(
		startDestination = WACDestination.Chat.Home.route,
		route = WACDestination.Chat.Root.route
	) {
		composable(WACDestination.Chat.Home.route) { backEntry ->
			val viewModel = hiltViewModel<ChatViewModel>(backEntry)
			
			ChatScreen(
				navController = navController,
				viewModel = viewModel
			)
		}
	}
}
