package com.anafthdev.waclone.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.anafthdev.waclone.data.WACDestination
import com.anafthdev.waclone.runtime.navigation.ChatNavHost
import com.anafthdev.waclone.ui.chat_config.ChatConfigScreen
import com.anafthdev.waclone.ui.chat_config.ChatConfigViewModel
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen() {
	
	val sheetState = rememberModalBottomSheetState(
		initialValue = ModalBottomSheetValue.Hidden,
		skipHalfExpanded = true
	)
	
	val bottomSheetNavigator = remember {
		BottomSheetNavigator(sheetState)
	}
	
	val navController = rememberNavController(bottomSheetNavigator)
	
	Surface(
		color = MaterialTheme.colorScheme.background,
		modifier = Modifier
			.fillMaxSize()
	) {
		ModalBottomSheetLayout(
			bottomSheetNavigator = bottomSheetNavigator,
			sheetShape = MaterialTheme.shapes.large,
			sheetBackgroundColor = MaterialTheme.colorScheme.surface,
			sheetContentColor = MaterialTheme.colorScheme.contentColorFor(MaterialTheme.colorScheme.surface),
			scrimColor = MaterialTheme.colorScheme.scrim.copy(alpha = 0.64f)
		) {
			NavHost(
				navController = navController,
				startDestination = WACDestination.Chat.Root.route
			) {
				ChatNavHost(navController)
				
				bottomSheet(WACDestination.Sheet.ChatConfig.Home.route) { backEntry ->
					val viewModel = hiltViewModel<ChatConfigViewModel>(backEntry)
					
					ChatConfigScreen(
						navController = navController,
						viewModel = viewModel
					)
				}
			}
		}
	}
}
