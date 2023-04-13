package com.anafthdev.waclone.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
	
	@Provides
	@Singleton
	fun provideSharedData(): SharedData = SharedData()
	
}