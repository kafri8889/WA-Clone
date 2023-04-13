package com.anafthdev.waclone.data

sealed class WACDestination(val route: String) {

	class Dashboard {
		object Root: WACDestination("dashboard/root")
		object Home: WACDestination("dashboard/home")
	}
	
	class Chat {
		object Root: WACDestination("chat/root")
		object Home: WACDestination("chat/home")
	}
}